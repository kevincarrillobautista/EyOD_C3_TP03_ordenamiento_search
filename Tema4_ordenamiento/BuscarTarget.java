/*
* ITESS-TICS Estructura y Organizacion de Datos
* Agosto Diciembre 2025
* Docente: Francisco Javier Montecillo Puente
* Tema 4. Ordenamiento
* Problema: Encontrar un target en un arreglo de 1 000 000 de datos sin ordenar
* Programador: Kevin Carrillo Bautista
* Fecha: 09 diciembre 2025
*
*/
package Tema4_ordenamiento;

public class BuscarTarget {
    public static int buscar(int[] arreglo, int target) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] arreglo = new int[1000000];
        // Llenar arreglo con datos...
        int target = 8;
        
        int resultado = buscar(arreglo, target);
        if (resultado != -1) {
            System.out.println("Elemento encontrado en indice: " + resultado);
        } else {
            System.out.println("Elemento no encontrado");
        }
}
}
