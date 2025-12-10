import java.util.Random;
import java.util.Arrays;

public class OrdenamientoBurbuja {
    
    // Método de ordenamiento burbuja
    public static void ordenamientoBurbuja(int[] arr) {
        int n = arr.length;
        boolean intercambiado;
        
        for (int i = 0; i < n - 1; i++) {
            intercambiado = false;
            
            // Los últimos i elementos ya están en su lugar
            for (int j = 0; j < n - i - 1; j++) {
                // Comparar elementos adyacentes
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar si están en el orden incorrecto
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    intercambiado = true;
                }
            }
            
            // Si no hubo intercambios en la pasada, el array está ordenado
            if (!intercambiado) {
                break;
            }
            
            // Mostrar progreso para arrays grandes
            if (n > 10000 && (i + 1) % 100 == 0) {
                double progreso = ((i + 1.0) / n) * 100;
                System.out.printf("Progreso: %.2f%% completado%n", progreso);
            }
        }
    }
    
    // Método optimizado de ordenamiento burbuja
    public static void ordenamientoBurbujaOptimizado(int[] arr) {
        int n = arr.length;
        boolean intercambiado;
        int nuevaN;
        
        do {
            intercambiado = false;
            nuevaN = 0;
            
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    // Intercambiar elementos
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    
                    intercambiado = true;
                    nuevaN = i;
                }
            }
            
            n = nuevaN;
            
        } while (intercambiado);
    }
    
    // Generar array con datos aleatorios
    public static int[] generarDatosAleatorios(int cantidad, int rangoMin, int rangoMax) {
        System.out.printf("Generando %,d numeros aleatorios...%n", cantidad);
        
        int[] datos = new int[cantidad];
        Random random = new Random();
        
        for (int i = 0; i < cantidad; i++) {
            datos[i] = random.nextInt(rangoMax - rangoMin + 1) + rangoMin;
        }
        
        return datos;
    }
    
    // Mostrar una muestra del array
    public static void mostrarMuestra(int[] arr, int numElementos) {
        if (arr.length <= numElementos * 2) {
            System.out.println("Array completo: " + Arrays.toString(arr));
        } else {
            System.out.print("Primeros " + numElementos + " elementos: [");
            for (int i = 0; i < numElementos; i++) {
                System.out.print(arr[i]);
                if (i < numElementos - 1) System.out.print(", ");
            }
            System.out.println("]");
            
            System.out.print("Ultimos " + numElementos + " elementos: [");
            for (int i = arr.length - numElementos; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i < arr.length - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
    
    // Verificar si el array está ordenado
    public static boolean verificarOrdenamiento(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    // Copiar array
    public static int[] copiarArray(int[] original) {
        int[] copia = new int[original.length];
        System.arraycopy(original, 0, copia, 0, original.length);
        return copia;
    }
    
    public static void main(String[] args) {
        // Configuración
        final int CANTIDAD_DATOS = 1000000;
        
        System.out.println("=".repeat(60));
        System.out.println("ORDENAMIENTO DE BURBUJA EN JAVA");
        System.out.println("=".repeat(60));
        
        // Para propósitos de demostración, usaremos menos datos
        // Cambiar esta línea a CANTIDAD_DATOS si realmente quieres intentarlo
        int cantidadAUsar = 10000; // Valor reducido para demostración
        // 1. Generar datos aleatorios
        long inicioGeneracion = System.currentTimeMillis();
        int[] datos = generarDatosAleatorios(cantidadAUsar, 0, 1000000);
        long finGeneracion = System.currentTimeMillis();
        
        System.out.printf("Datos generados en %.2f segundos%n", 
                         (finGeneracion - inicioGeneracion) / 1000.0);
        
        // Mostrar una muestra de los datos originales
        System.out.println("\nMuestra de datos originales (sin ordenar):");
        mostrarMuestra(datos, 10);
        
        // 2. Ordenar usando el método de la burbuja
        System.out.printf("%nOrdenando %,d elementos con el metodo de la burbuja...%n", datos.length);
        System.out.println("Esto puede tardar varios segundos o minutos...");
        
        int[] datosParaOrdenar = copiarArray(datos);
        
        long inicioOrdenamiento = System.currentTimeMillis();
        ordenamientoBurbuja(datosParaOrdenar);
        long finOrdenamiento = System.currentTimeMillis();
        
        double tiempoOrdenamiento = (finOrdenamiento - inicioOrdenamiento) / 1000.0;
        System.out.printf("Tiempo de ordenamiento: %.2f segundos%n", tiempoOrdenamiento);
        
        // 3. Verificar el ordenamiento
        System.out.println("\nVerificando el ordenamiento...");
        if (verificarOrdenamiento(datosParaOrdenar)) {
            System.out.println("✓ El array esta correctamente ordenado");
        } else {
            System.out.println("✗ El array NO esta ordenado correctamente");
        }
        
        // 4. Mostrar una muestra de los datos ordenados
        System.out.println("\nMuestra de datos ordenados:");
        mostrarMuestra(datosParaOrdenar, 10);
        
        // 5. Comparar con Arrays.sort() de Java
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPARACION CON Arrays.sort()");
        System.out.println("=".repeat(60));
        
        int[] datosParaSort = copiarArray(datos);
        long inicioSort = System.currentTimeMillis();
        Arrays.sort(datosParaSort);
        long finSort = System.currentTimeMillis();
        
        double tiempoSort = (finSort - inicioSort) / 1000.0;
        System.out.printf("Tiempo con Arrays.sort(): %.2f segundos%n", tiempoSort);
        
        // Verificar que ambos métodos dan el mismo resultado
        boolean iguales = Arrays.equals(datosParaOrdenar, datosParaSort);
        System.out.println("Ambos metodos producen el mismo resultado? " + 
                          (iguales ? " Sí" : " No"));
        
        // 6. Información adicional
        System.out.println("\n" + "=".repeat(60));
        System.out.println("INFORMACION ADICIONAL");
        System.out.println("=".repeat(60));
        System.out.printf("Numero de elementos: %,d%n", datos.length);
        System.out.printf("Tiempo generación: %.2f segundos%n", 
                         (finGeneracion - inicioGeneracion) / 1000.0);
        System.out.printf("Tiempo burbuja: %.2f segundos%n", tiempoOrdenamiento);
        System.out.printf("Tiempo Arrays.sort(): %.2f segundos%n", tiempoSort);
        
        // Estimación para 1,000,000 de elementos
        if (cantidadAUsar < CANTIDAD_DATOS) {
            System.out.println("\nESTIMACION para 1,000,000 de elementos:");
            double tiempoEstimadoBurbuja = Math.pow((double)CANTIDAD_DATOS / cantidadAUsar, 2) * tiempoOrdenamiento;
            System.out.printf("Tiempo estimado burbuja: %.2f segundos%n", tiempoEstimadoBurbuja);
            System.out.printf("Equivalente a aproximadamente %.2f horas%n", tiempoEstimadoBurbuja / 3600);
            
            double tiempoEstimadoSort = ((double)CANTIDAD_DATOS / cantidadAUsar) * 
                                       Math.log(CANTIDAD_DATOS) / Math.log(cantidadAUsar) * tiempoSort;
            System.out.printf("Tiempo estimado Arrays.sort(): %.2f segundos%n", tiempoEstimadoSort);
        }
    }
    
    // Método adicional para pruebas de rendimiento con diferentes tamaños
    public static void pruebaRendimientoDiferentesTamanos() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PRUEBA DE RENDIMIENTO CON DIFERENTES TAMAÑOS");
        System.out.println("=".repeat(60));
        
        int[] tamanos = {100, 500, 1000, 5000, 10000};
        
        for (int tamano : tamanos) {
            System.out.printf("%nPrueba con %,d elementos:%n", tamano);
            
            // Generar datos
            int[] datos = generarDatosAleatorios(tamano, 0, 1000000);
            int[] copia = copiarArray(datos);
            
            // Burbuja
            long inicioBurbuja = System.currentTimeMillis();
            ordenamientoBurbuja(copia);
            long finBurbuja = System.currentTimeMillis();
            
            // Arrays.sort()
            copia = copiarArray(datos);
            long inicioSort = System.currentTimeMillis();
            Arrays.sort(copia);
            long finSort = System.currentTimeMillis();
            
            System.out.printf("Burbuja: %.3f segundos%n", (finBurbuja - inicioBurbuja) / 1000.0);
            System.out.printf("Arrays.sort(): %.3f segundos%n", (finSort - inicioSort) / 1000.0);
            System.out.printf("Ratio: %.1fx más rápido%n", 
                            (double)(finBurbuja - inicioBurbuja) / (finSort - inicioSort));
        }
    }
}