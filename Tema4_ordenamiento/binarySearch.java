/*
* ITESS-TICS Estructura y Organizacion de Datos
* Agosto Diciembre 2025
* Docente: Francisco Javier Montecillo Puente
* Tema 4. Ordenamiento
* Problema: dado un arreglo de 1000 000 de enteros 
            los ordene y luego busque un elemento target usando binarySearch
* Programador: Kevin Carrillo Bautista
* Fecha: 09 diciembre 2025
*
*/
package Tema4_ordenamiento;
import java.util.Arrays;
import java.util.Random;

public class binarySearch {
    
    // Método principal
    public static void main(String[] args) {
        // 1. Crear arreglo de 1,000,000 de enteros
        int[] arreglo = new int[1_000_000];
        Random random = new Random();
        
        System.out.println("Generando arreglo de 1,000,000 elementos...");
        
        // Llenar con valores aleatorios
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = random.nextInt(10_000_000); // Valores entre 0 y 9,999,999
        }
        
        // 2. Ordenar el arreglo
        System.out.println("Ordenando arreglo...");
        long inicioOrdenar = System.currentTimeMillis();
        Arrays.sort(arreglo); // Usa Dual-Pivot Quicksort de Java (optimizado)
        long finOrdenar = System.currentTimeMillis();
        System.out.printf("Tiempo de ordenamiento: %,d ms%n", (finOrdenar - inicioOrdenar));
        
        // 3. Verificar que está ordenado (opcional, para debugging)
        if (verificarOrdenado(arreglo)) {
            System.out.println(" El arreglo esta correctamente ordenado");
        }
        
        // 4. Generar un target aleatorio para buscar
        int target = 18;
        System.out.printf("\nBuscando el valor: %,d%n", target);
        
        // 5. Buscar usando binarySearch
        long inicioBusqueda = System.nanoTime();
        int indice = binarySearch(arreglo, target);
        long finBusqueda = System.nanoTime();
        
        // 6. Mostrar resultados
        if (indice >= 0) {
            System.out.printf(" Elemento encontrado en indice: %,d%n", indice);
            System.out.printf("  Valor en esa posicion: %,d%n", arreglo[indice]);
            
            // Verificar que los valores alrededor son correctos
            if (indice > 0) {
                System.out.printf("  Valor anterior: %,d (debe ser ≤ %,d)%n", 
                    arreglo[indice - 1], arreglo[indice]);
            }
            if (indice < arreglo.length - 1) {
                System.out.printf("  Valor siguiente: %,d (debe ser ≥ %,d)%n", 
                    arreglo[indice + 1], arreglo[indice]);
            }
        } else {
            System.out.println("✗ Elemento no encontrado");
            
            // Mostrar dónde se insertaría el elemento
            int puntoInsercion = -indice - 1;
            System.out.printf("  Se insertaria en posicion: %,d%n", puntoInsercion);
            
            if (puntoInsercion > 0 && puntoInsercion < arreglo.length) {
                System.out.printf("  Entre %,d y %,d%n", 
                    arreglo[puntoInsercion - 1], 
                    arreglo[puntoInsercion]);
            } else if (puntoInsercion == 0) {
                System.out.println("  Al inicio del arreglo");
            } else {
                System.out.println("  Al final del arreglo");
            }
        }
        
        System.out.printf("Tiempo de busqueda binaria: %,d nanosegundos%n", 
            (finBusqueda - inicioBusqueda));

    }
    
    /**
     * Implementación de Binary Search (búsqueda binaria)
     * Retorna el índice del elemento si se encuentra,
     * o un valor negativo si no se encuentra
     */
    public static int binarySearch(int[] arreglo, int target) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        
        while (izquierda <= derecha) {
            // Evitar overflow al calcular el punto medio
            int medio = izquierda + (derecha - izquierda) / 2;
            int valorMedio = arreglo[medio];
            
            if (valorMedio == target) {
                return medio; // Elemento encontrado
            } else if (valorMedio < target) {
                izquierda = medio + 1; // Buscar en la mitad derecha
            } else {
                derecha = medio - 1; // Buscar en la mitad izquierda
            }
        }
        
        // Elemento no encontrado, retornar punto de inserción
        return -(izquierda + 1);
    }
    
    /**
     * Binary Search recursivo (versión alternativa)
     */
    public static int binarySearchRecursivo(int[] arreglo, int target) {
        return binarySearchRecursivo(arreglo, target, 0, arreglo.length - 1);
    }
    
    private static int binarySearchRecursivo(int[] arreglo, int target, int izquierda, int derecha) {
        if (izquierda > derecha) {
            return -(izquierda + 1); // No encontrado
        }
        
        int medio = izquierda + (derecha - izquierda) / 2;
        
        if (arreglo[medio] == target) {
            return medio;
        } else if (arreglo[medio] < target) {
            return binarySearchRecursivo(arreglo, target, medio + 1, derecha);
        } else {
            return binarySearchRecursivo(arreglo, target, izquierda, medio - 1);
        }
    }
    
    /**
     * Búsqueda lineal en arreglo ordenado (para comparación)
     * Se detiene cuando encuentra el elemento o cuando pasa el valor buscado
     */
    public static int busquedaLinealOrdenada(int[] arreglo, int target) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == target) {
                return i;
            }
            // Optimización: si pasamos el valor, podemos detenernos
            if (arreglo[i] > target) {
                return -1;
            }
        }
        return -1;
    }
    
    /**
     * Verifica que el arreglo está ordenado
     */
    public static boolean verificarOrdenado(int[] arreglo) {
        for (int i = 1; i < arreglo.length; i++) {
            if (arreglo[i] < arreglo[i - 1]) {
                System.out.printf("Error de orden en indice %,d: %,d > %,d%n", 
                    i, arreglo[i - 1], arreglo[i]);
                return false;
            }
        }
        return true;
    }
    
    /**
     * Versión usando el método built-in de Java Arrays.binarySearch
     */
    public static void busquedaConJavaAPI(int[] arreglo, int target) {
        System.out.println("\n--- Usando Java Arrays.binarySearch ---");
        
        long inicio = System.nanoTime();
        int indice = Arrays.binarySearch(arreglo, target);
        long fin = System.nanoTime();
        
        if (indice >= 0) {
            System.out.printf("Elemento encontrado en indice: %,d%n", indice);
        } else {
            System.out.println("Elemento no encontrado");
        }
        
        System.out.printf("Tiempo: %,d nanosegundos%n", (fin - inicio));
    }
    
    /**
     * Método para buscar múltiples elementos
     */
    public static void buscarMultiplesElementos(int[] arreglo, int[] targets) {
        System.out.println("\n--- Busqueda de múltiples elementos ---");
        
        // Pre-ordenar los targets para mejor locality
        Arrays.sort(targets);
        
        int encontrados = 0;
        long inicio = System.nanoTime();
        
        for (int target : targets) {
            if (binarySearch(arreglo, target) >= 0) {
                encontrados++;
            }
        }
        
        long fin = System.nanoTime();
        
        System.out.printf("Encontrados %,d de %,d elementos%n", 
            encontrados, targets.length);
        System.out.printf("Tiempo total: %,d nanosegundos%n", (fin - inicio));
        System.out.printf("Tiempo promedio por busqueda: %,d nanosegundos%n", 
            (fin - inicio) / targets.length);
    }
}