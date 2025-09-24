package ListaCircularSimple;

public class Main {
    public static void main(String[] args) {
        ListaCircularSimple<Integer> lista = new ListaCircularSimple<Integer>();

        lista.agregarInicio(3);
        lista.agregarFinal(5);
        lista.agregarFinal(7);
        lista.agregar(1, 1); // agrega en posición 1
        lista.imprimirLista(); // 3 -> 1 -> 5 -> 7 -> (cabeza)

        lista.eliminar(5);
        lista.imprimirLista(); // 3 -> 1 -> 7 -> (cabeza)

        lista.modificarNodo(1, 10);
        lista.imprimirLista(); // 3 -> 10 -> 7 -> (cabeza)

        lista.ordenarLista();
        lista.imprimirLista(); // 3 -> 7 -> 10 -> (cabeza)

        System.out.println("Recorriendo con iterador:");
        for (Integer val : lista) {
            System.out.println(val);
        }

        lista.borrarLista();
        lista.imprimirLista(); // Lista vacía
    }
}
