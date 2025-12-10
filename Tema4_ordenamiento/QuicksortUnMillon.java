package Tema4_ordenamiento;

import java.util.Random;
import java.util.Arrays;

public class QuicksortUnMillon {
    
    // Método principal de Quicksort (recursivo)
    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            // Particionar el array y obtener el índice del pivote
            int pi = particionar(arr, low, high);
            
            // Ordenar recursivamente los subarrays
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }
    // Versión sobrecargada para facilitar el uso
    public static void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }
    
    // Método de partición (usando último elemento como pivote)
    private static int particionar(int[] arr, int low, int high) {
        int pivote = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivote) {
                i++;
                intercambiar(arr, i, j);
            }
        }
        
        intercambiar(arr, i + 1, high);
        return i + 1;
    }
    
    // Método de partición con selección de pivote aleatorio
    public static void quicksortAleatorio(int[] arr, int low, int high) {
        if (low < high) {
            int pi = particionarAleatorio(arr, low, high);
            quicksortAleatorio(arr, low, pi - 1);
            quicksortAleatorio(arr, pi + 1, high);
        }
    }
    
    private static int particionarAleatorio(int[] arr, int low, int high) {
        // Seleccionar un pivote aleatorio
        int indiceAleatorio = low + new Random().nextInt(high - low + 1);
        intercambiar(arr, indiceAleatorio, high);
        
        return particionar(arr, low, high);
    }
    
    // Método auxiliar para intercambiar elementos
    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Quicksort optimizado con inserción para subarrays pequeños
    public static void quicksortOptimizado(int[] arr, int low, int high) {
        // Usar insertion sort para subarrays pequeños (tamaño <= 10)
        if (high - low <= 10) {
            insertionSort(arr, low, high);
            return;
        }
        
        if (low < high) {
            int pi = particionarMedianaDeTres(arr, low, high);
            quicksortOptimizado(arr, low, pi - 1);
            quicksortOptimizado(arr, pi + 1, high);
        }
    }
    
    // Partición usando la mediana de tres
    private static int particionarMedianaDeTres(int[] arr, int low, int high) {
        int medio = low + (high - low) / 2;
        
        // Ordenar low, medio y high
        if (arr[medio] < arr[low]) intercambiar(arr, low, medio);
        if (arr[high] < arr[low]) intercambiar(arr, low, high);
        if (arr[high] < arr[medio]) intercambiar(arr, medio, high);
        
        // Colocar el pivote (mediana) en high-1
        intercambiar(arr, medio, high - 1);
        return particionar(arr, low + 1, high - 1);
    }
    
    // Insertion sort para subarrays pequeños
    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
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
    
    // Copiar array
    public static int[] copiarArray(int[] original) {
        int[] copia = new int[original.length];
        System.arraycopy(original, 0, copia, 0, original.length);
        return copia;
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
    
    // Calcular estadísticas básicas
    public static void calcularEstadisticas(int[] arr) {
        if (arr.length == 0) return;
        
        int min = arr[0];
        int max = arr[0];
        long suma = 0;
        
        for (int valor : arr) {
            if (valor < min) min = valor;
            if (valor > max) max = valor;
            suma += valor;
        }
        
        double promedio = (double) suma / arr.length;
        
        System.out.printf("Minimo: %,d%n", min);
        System.out.printf("Maximo: %,d%n", max);
        System.out.printf("Promedio: %,.2f%n", promedio);
        System.out.printf("Rango: %,d%n", max - min);
    }
    
    public static void main(String[] args) {
        final int UN_MILLON = 1000000;
        
        System.out.println("=".repeat(70));
        System.out.println("ORDENAMIENTO QUICKSORT CON 1,000,000 DE ELEMENTOS");
        System.out.println("=".repeat(70));
        
        // 1. Generar datos aleatorios
        System.out.println("\n1. GENERANDO DATOS...");
        long inicioGeneracion = System.currentTimeMillis();
        int[] datosOriginales = generarDatosAleatorios(UN_MILLON, 0, 10000000);
        long finGeneracion = System.currentTimeMillis();
        
        System.out.printf("Tiempo de generacion: %.2f segundos%n", 
                         (finGeneracion - inicioGeneracion) / 1000.0);
        
        // Mostrar estadísticas de los datos originales
        System.out.println("\nEstadisticas de datos originales:");
        calcularEstadisticas(datosOriginales);
        
        // Mostrar una pequeña muestra
        System.out.println("\nMuestra de datos originales (10 primeros y ultimos):");
        mostrarMuestra(datosOriginales, 10);
        
        // 2. Ordenar con Quicksort básico
        System.out.println("\n" + "=".repeat(70));
        System.out.println("2. ORDENANDO CON QUICKSORT BASICO");
        System.out.println("=".repeat(70));
        
        int[] datosQuicksort = copiarArray(datosOriginales);
        
        long inicioQuicksort = System.currentTimeMillis();
        quicksort(datosQuicksort);
        long finQuicksort = System.currentTimeMillis();
        
        double tiempoQuicksort = (finQuicksort - inicioQuicksort) / 1000.0;
        System.out.printf("Tiempo de Quicksort basico: %.2f segundos%n", tiempoQuicksort);
        
        // Verificar ordenamiento
        boolean ordenado = verificarOrdenamiento(datosQuicksort);
        System.out.println("Array ordenado correctamente? " + (ordenado ? "SI" : "NO"));
        
        // 3. Ordenar con Quicksort optimizado
        System.out.println("\n" + "=".repeat(70));
        System.out.println("3. ORDENANDO CON QUICKSORT OPTIMIZADO");
        System.out.println("=".repeat(70));
        
        int[] datosOptimizados = copiarArray(datosOriginales);
        
        long inicioOptimizado = System.currentTimeMillis();
        quicksortOptimizado(datosOptimizados, 0, datosOptimizados.length - 1);
        long finOptimizado = System.currentTimeMillis();
        
        double tiempoOptimizado = (finOptimizado - inicioOptimizado) / 1000.0;
        System.out.printf("Tiempo de Quicksort optimizado: %.2f segundos%n", tiempoOptimizado);
        
        // Verificar que ambos métodos dan el mismo resultado
        boolean iguales = Arrays.equals(datosQuicksort, datosOptimizados);
        System.out.println("Resultados identicos? " + (iguales ? "SI" : "NO"));
        
        // 4. Ordenar con Arrays.sort() de Java (comparación)
        System.out.println("\n" + "=".repeat(70));
        System.out.println("4. COMPARACION CON Arrays.sort()");
        System.out.println("=".repeat(70));
        
        int[] datosJavaSort = copiarArray(datosOriginales);
        
        long inicioJavaSort = System.currentTimeMillis();
        Arrays.sort(datosJavaSort);
        long finJavaSort = System.currentTimeMillis();
        
        double tiempoJavaSort = (finJavaSort - inicioJavaSort) / 1000.0;
        System.out.printf("Tiempo de Arrays.sort(): %.2f segundos%n", tiempoJavaSort);
        
        // Verificar que Java sort da el mismo resultado
        boolean igualesConJava = Arrays.equals(datosQuicksort, datosJavaSort);
        System.out.println("Resultado igual a Quicksort? " + (igualesConJava ? "SI" : "NO"));
        
        // 5. Ordenar con Quicksort con pivote aleatorio
        System.out.println("\n" + "=".repeat(70));
        System.out.println("5. QUICKSORT CON PIVOTE ALEATORIO");
        System.out.println("=".repeat(70));
        
        int[] datosAleatorio = copiarArray(datosOriginales);
        
        long inicioAleatorio = System.currentTimeMillis();
        quicksortAleatorio(datosAleatorio, 0, datosAleatorio.length - 1);
        long finAleatorio = System.currentTimeMillis();
        
        double tiempoAleatorio = (finAleatorio - inicioAleatorio) / 1000.0;
        System.out.printf("Tiempo de Quicksort con pivote aleatorio: %.2f segundos%n", tiempoAleatorio);
        
        // 6. Mostrar resultados finales
        System.out.println("\n" + "=".repeat(70));
        System.out.println("RESUMEN DE RESULTADOS");
        System.out.println("=".repeat(70));
        
        System.out.printf("Total de elementos: %,d%n", UN_MILLON);
        System.out.printf("Tiempo Quicksort basico: %.3f segundos%n", tiempoQuicksort);
        System.out.printf("Tiempo Quicksort optimizado: %.3f segundos%n", tiempoOptimizado);
        System.out.printf("Tiempo Quicksort aleatorio: %.3f segundos%n", tiempoAleatorio);
        System.out.printf("Tiempo Arrays.sort(): %.3f segundos%n", tiempoJavaSort);
        
        // Calcular speedup
        System.out.println("\nComparacion de velocidad:");
        System.out.printf("Quicksort optimizado es %.1fx mas rapido que el basico%n", 
                         tiempoQuicksort / tiempoOptimizado);
        System.out.printf("Arrays.sort() es %.1fx mas rapido que Quicksort basico%n", 
                         tiempoQuicksort / tiempoJavaSort);
        
        // Mostrar muestra de datos ordenados
        System.out.println("\nMuestra de datos ordenados (10 primeros y ultimos):");
        mostrarMuestra(datosQuicksort, 10);
        
        // Mostrar estadísticas finales
        System.out.println("\nEstadisticas de datos ordenados:");
        calcularEstadisticas(datosQuicksort);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("!PROCESO COMPLETADO EXITOSAMENTE!");
        System.out.println("=".repeat(70));
    }
    
    // Método para prueba de rendimiento con diferentes tamaños
    public static void pruebaRendimientoCompleta() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("PRUEBA DE RENDIMIENTO COMPARATIVO");
        System.out.println("=".repeat(70));
        
        int[] tamanos = {1000, 10000, 50000, 100000, 500000, 1000000};
        
        System.out.printf("%-10s %-15s %-20s %-15s %-15s%n", 
                         "Tamaño", "Quicksort", "Quicksort Opt", "Quicksort Aleat", "Arrays.sort()");
        System.out.println("-".repeat(75));
        
        for (int tamano : tamanos) {
            // Generar datos
            int[] datos = generarDatosAleatorios(tamano, 0, 1000000);
            
            // Quicksort básico
            int[] copia1 = copiarArray(datos);
            long inicio1 = System.currentTimeMillis();
            quicksort(copia1);
            long fin1 = System.currentTimeMillis();
            double tiempo1 = (fin1 - inicio1) / 1000.0;
            
            // Quicksort optimizado
            int[] copia2 = copiarArray(datos);
            long inicio2 = System.currentTimeMillis();
            quicksortOptimizado(copia2, 0, copia2.length - 1);
            long fin2 = System.currentTimeMillis();
            double tiempo2 = (fin2 - inicio2) / 1000.0;
            
            // Quicksort aleatorio
            int[] copia3 = copiarArray(datos);
            long inicio3 = System.currentTimeMillis();
            quicksortAleatorio(copia3, 0, copia3.length - 1);
            long fin3 = System.currentTimeMillis();
            double tiempo3 = (fin3 - inicio3) / 1000.0;
            
            // Arrays.sort()
            int[] copia4 = copiarArray(datos);
            long inicio4 = System.currentTimeMillis();
            Arrays.sort(copia4);
            long fin4 = System.currentTimeMillis();
            double tiempo4 = (fin4 - inicio4) / 1000.0;
            
            System.out.printf("%,-10d %-15.3f %-20.3f %-15.3f %-15.3f%n", 
                            tamano, tiempo1, tiempo2, tiempo3, tiempo4);
        }
    }
    
    // Método para demostrar el funcionamiento paso a paso (con array pequeño)
    public static void demostracionPasoAPaso() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMOSTRACION PASO A PASO DE QUICKSORT");
        System.out.println("=".repeat(70));
        
        int[] demoArray = {64, 34, 25, 12, 22, 11, 90, 5};
        
        System.out.println("Array original: " + Arrays.toString(demoArray));
        System.out.println("\nAplicando Quicksort...");
        
        quicksort(demoArray, 0, demoArray.length - 1);
        
        System.out.println("Array ordenado: " + Arrays.toString(demoArray));
        
        // Explicación del algoritmo
        System.out.println("\nEXPLICACION DEL ALGORITMO QUICKSORT:");
        System.out.println("1. Selecciona un elemento como pivote");
        System.out.println("2. Reorganiza el array: elementos menores al pivote van a la izquierda,");
        System.out.println("   elementos mayores al pivote van a la derecha");
        System.out.println("3. Aplica recursivamente los pasos 1 y 2 a los subarrays");
        System.out.println("4. Complejidad: O(n log n) en promedio, O(n^2) en peor caso");
    }
}