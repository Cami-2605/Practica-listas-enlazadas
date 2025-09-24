package ListaSimple.ListasInvertirContenido;

public class Lista<T> {
    private Nodo<T> cabeza;

    public Lista() {
        this.cabeza = null;
    }

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public void imprimir() {
        Nodo<T> temp = cabeza;
        while (temp != null) {
            System.out.print(temp.dato + " -> ");
            temp = temp.siguiente;
        }
        System.out.println("null");
    }

    // --- Método público para invertir ---
    public void invertir() {
        if (cabeza == null || cabeza.siguiente == null) {
            return; // no hace nada si lista vacía o un solo elemento
        }
        cabeza = invertirRecursivo(cabeza, null);
    }

    // --- Método recursivo privado ---
    private Nodo<T> invertirRecursivo(Nodo<T> actual, Nodo<T> anterior) {
        if (actual == null) {
            return anterior; // la nueva cabeza será el último nodo
        }
        Nodo<T> siguiente = actual.siguiente;
        actual.siguiente = anterior; // en la vuelta: redirigimos enlace
        return invertirRecursivo(siguiente, actual);
    }
}