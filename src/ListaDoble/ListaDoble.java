package ListaDoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoble<T extends Comparable<T>> implements Iterable<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamaño;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public boolean indiceValido(int index) {
        return index >= 0 && index < tamaño;
    }

    public void agregarInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        tamaño++;
    }

    public void agregarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamaño++;
    }

    public void agregar(int index, T dato) {
        if (index < 0 || index > tamaño) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        if (index == 0) {
            agregarInicio(dato);
        } else if (index == tamaño) {
            agregarFinal(dato);
        } else {
            NodoDoble<T> nuevo = new NodoDoble<>(dato);
            NodoDoble<T> aux = cabeza;

            for (int i = 0; i < index - 1; i++) {
                aux = aux.siguiente;
            }

            nuevo.siguiente = aux.siguiente;
            nuevo.anterior = aux;
            aux.siguiente.anterior = nuevo;
            aux.siguiente = nuevo;
            tamaño++;
        }
    }

    public T obtenerValorNodo(int index) {
        return obtenerNodo(index).dato;
    }

    public NodoDoble<T> obtenerNodo(int index) {
        if (!indiceValido(index)) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        NodoDoble<T> aux = cabeza;
        for (int i = 0; i < index; i++) {
            aux = aux.siguiente;
        }
        return aux;
    }

    public int obtenerPosicionNodo(T dato) {
        NodoDoble<T> aux = cabeza;
        int index = 0;
        while (aux != null) {
            if (aux.dato.equals(dato)) {
                return index;
            }
            aux = aux.siguiente;
            index++;
        }
        return -1;
    }

    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        tamaño--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null;
        }
        tamaño--;
    }

    public void eliminar(T dato) {
        if (estaVacia()) return;

        if (cabeza.dato.equals(dato)) {
            eliminarPrimero();
            return;
        }
        if (cola.dato.equals(dato)) {
            eliminarUltimo();
            return;
        }

        NodoDoble<T> aux = cabeza;
        while (aux != null && !aux.dato.equals(dato)) {
            aux = aux.siguiente;
        }

        if (aux != null) {
            aux.anterior.siguiente = aux.siguiente;
            if (aux.siguiente != null) {
                aux.siguiente.anterior = aux.anterior;
            }
            tamaño--;
        }
    }

    public void modificarNodo(int index, T nuevoDato) {
        NodoDoble<T> nodo = obtenerNodo(index);
        nodo.dato = nuevoDato;
    }

    public void ordenarLista() {
        if (tamaño <= 1) return;

        NodoDoble<T> actual, indice;
        T temp;

        for (actual = cabeza; actual != null; actual = actual.siguiente) {
            for (indice = actual.siguiente; indice != null; indice = indice.siguiente) {
                if (actual.dato.compareTo(indice.dato) > 0) {
                    temp = actual.dato;
                    actual.dato = indice.dato;
                    indice.dato = temp;
                }
            }
        }
    }

    public void imprimirLista() {
        NodoDoble<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.dato + " <-> ");
            aux = aux.siguiente;
        }
        System.out.println("null");
    }

    public void borrarLista() {
        cabeza = cola = null;
        tamaño = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }
}