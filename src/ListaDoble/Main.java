package ListaDoble;

public class Main {
    public static void main(String[] args) {
        ListaDoble<Integer> lista = new ListaDoble<Integer>();

        System.out.println("¿La lista está vacía?: " + lista.estaVacia());

        // --- AGREGAR ---
        lista.agregarInicio(30);
        lista.agregarFinal(50);
        lista.agregarInicio(10);
        lista.agregar(1, 20); // Insertar en posición 1 el valor 20

        System.out.println("\nLista después de inserciones:");
        lista.imprimirLista();

        System.out.println("\nValor en posición 2: " + lista.obtenerValorNodo(2));
        System.out.println("Nodo en posición 1: " + lista.obtenerNodo(1).getDato());
        System.out.println("Posición del valor 50: " + lista.obtenerPosicionNodo(50));

        lista.modificarNodo(2, 40);
        System.out.println("\nLista después de modificar posición 2 (ahora vale 40):");
        lista.imprimirLista();

        lista.eliminarPrimero();
        lista.eliminarUltimo();
        lista.eliminar(20); // Eliminar por valor
        System.out.println("\nLista después de eliminaciones:");
        lista.imprimirLista();

        lista.agregarFinal(5);
        lista.agregarFinal(15);
        lista.agregarFinal(25);
        lista.agregarFinal(35);

        System.out.println("\nLista antes de ordenar:");
        lista.imprimirLista();

        lista.ordenarLista();
        System.out.println("\nLista después de ordenar:");
        lista.imprimirLista();

        System.out.println("\nRecorrido con Iterator:");
        for (int valor : lista) {
            System.out.print(valor + " <-> ");
        }
        System.out.println("null");

        lista.borrarLista();
        System.out.println("\nLista después de borrarla:");
        lista.imprimirLista();
        System.out.println("¿La lista está vacía ahora?: " + lista.estaVacia());
    }
}