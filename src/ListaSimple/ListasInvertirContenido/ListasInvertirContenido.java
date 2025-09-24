package ListaSimple.ListasInvertirContenido;

public class ListasInvertirContenido {
    public static void main(String[] args) {
        Lista<Integer> lista = new Lista<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(4);
        lista.agregar(5);

        System.out.println("Lista original:");
        lista.imprimir();

        lista.invertir();

        System.out.println("Lista invertida:");
        lista.imprimir();
    }
}
