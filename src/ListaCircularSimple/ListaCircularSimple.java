package ListaCircularSimple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircularSimple<T extends Comparable<T>> implements Iterable<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamanio;

    public ListaCircularSimple() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int size() {
        return tamanio;
    }

    private boolean indiceValido(int index) {
        return index >= 0 && index < tamanio;
    }

    public void agregarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza; // mantener circularidad
        }
        tamanio++;
    }

    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
        tamanio++;
    }

    public void agregar(T dato, int pos) {
        if (pos == 0) {
            agregarInicio(dato);
        } else if (pos == tamanio) {
            agregarFinal(dato);
        } else if (indiceValido(pos)) {
            Nodo<T> nuevo = new Nodo<>(dato);
            Nodo<T> temp = cabeza;
            for (int i = 0; i < pos - 1; i++) {
                temp = temp.siguiente;
            }
            nuevo.siguiente = temp.siguiente;
            temp.siguiente = nuevo;
            tamanio++;
        } else {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
    }

    public T obtenerValorNodo(int pos) {
        return obtenerNodo(pos).dato;
    }

    public Nodo<T> obtenerNodo(int pos) {
        if (!indiceValido(pos)) throw new IndexOutOfBoundsException();
        Nodo<T> temp = cabeza;
        for (int i = 0; i < pos; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    public int obtenerPosicionNodo(T dato) {
        if (estaVacia()) return -1;
        Nodo<T> temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if (temp.dato.equals(dato)) return i;
            temp = temp.siguiente;
        }
        return -1;
    }

    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza;
        }
        tamanio--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            cabeza = null;
            cola = null;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.siguiente != cola) {
                temp = temp.siguiente;
            }
            temp.siguiente = cabeza;
            cola = temp;
        }
        tamanio--;
    }

    public void eliminar(T dato) {
        if (estaVacia()) return;

        if (cabeza.dato.equals(dato)) {
            eliminarPrimero();
            return;
        }

        Nodo<T> temp = cabeza;
        while (temp.siguiente != cabeza && !temp.siguiente.dato.equals(dato)) {
            temp = temp.siguiente;
        }

        if (temp.siguiente.dato.equals(dato)) {
            if (temp.siguiente == cola) {
                eliminarUltimo();
            } else {
                temp.siguiente = temp.siguiente.siguiente;
                tamanio--;
            }
        }
    }

    public void modificarNodo(int pos, T nuevoDato) {
        Nodo<T> nodo = obtenerNodo(pos);
        nodo.dato = nuevoDato;
    }

    public void ordenarLista() {
        if (tamanio <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;
            for (int i = 0; i < tamanio - 1; i++) {
                Nodo<T> siguiente = actual.siguiente;
                if (actual.dato.compareTo(siguiente.dato) > 0) {
                    T temp = actual.dato;
                    actual.dato = siguiente.dato;
                    siguiente.dato = temp;
                    cambiado = true;
                }
                actual = actual.siguiente;
            }
        } while (cambiado);
    }

    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        Nodo<T> temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            System.out.print(temp.dato + " -> ");
            temp = temp.siguiente;
        }
        System.out.println("(cabeza)");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;
            private int contador = 0;

            @Override
            public boolean hasNext() {
                return contador < tamanio;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                contador++;
                return dato;
            }
        };
    }

    public void borrarLista() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }
}