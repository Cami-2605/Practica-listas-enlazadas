package ListaCircularDoble;

public class Main {
    public static void main(String[] args) {
        ListaCircularDoble<Integer> lista = new ListaCircularDoble<Integer>();

        lista.agregarInicio(3);
        lista.agregarFinal(7);
        lista.agregar(5, 1); // inserta en el medio
        lista.imprimirLista(); // 3 <-> 5 <-> 7 <-> (cabeza)

        lista.eliminar(5);
        lista.imprimirLista(); // 3 <-> 7 <-> (cabeza)

        lista.modificarNodo(1, 10);
        lista.imprimirLista(); // 3 <-> 10 <-> (cabeza)

        lista.ordenarLista();
        lista.imprimirLista(); // 3 <-> 10 <-> (cabeza)

        System.out.println("Iterador:");
        for (Integer val : lista) {
            System.out.println(val);
        }

        lista.borrarLista();
        lista.imprimirLista(); // Lista vacía
    }
}