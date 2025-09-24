package ListaSimple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimple<T extends Comparable<T>> implements Iterable<T> {
    private Nodo<T> cabeza;
    private int size;

    public ListaSimple() {
        this.cabeza = null;
        this.size = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public boolean indiceValido(int index) {
        return index >= 0 && index < size;
    }

    public void agregarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        size++;
    }

    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        size++;
    }

    public void agregar(T dato, int index) {
        if (index < 0 || index > size) {
            System.out.println("Índice inválido");
            return;
        }

        if (index == 0) {
            agregarInicio(dato);
        } else {
            Nodo<T> nuevo = new Nodo<>(dato);
            Nodo<T> aux = cabeza;
            for (int i = 0; i < index - 1; i++) {
                aux = aux.getSiguiente();
            }
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            size++;
        }
    }

    public T obtenerValorNodo(int index) {
        if (!indiceValido(index)) return null;
        Nodo<T> aux = cabeza;
        for (int i = 0; i < index; i++) {
            aux = aux.getSiguiente();
        }
        return aux.getDato();
    }

    public Nodo<T> obtenerNodo(int index) {
        if (!indiceValido(index)) return null;
        Nodo<T> aux = cabeza;
        for (int i = 0; i < index; i++) {
            aux = aux.getSiguiente();
        }
        return aux;
    }

    public int obtenerPosicionNodo(T dato) {
        Nodo<T> aux = cabeza;
        int pos = 0;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return pos;
            }
            aux = aux.getSiguiente();
            pos++;
        }
        return -1;
    }

    public T eliminarPrimero() {
        if (estaVacia()) return null;
        T valor = cabeza.getDato();
        cabeza = cabeza.getSiguiente();
        size--;
        return valor;
    }

    public T eliminarUltimo() {
        if (estaVacia()) return null;

        if (cabeza.getSiguiente() == null) {
            T valor = cabeza.getDato();
            cabeza = null;
            size--;
            return valor;
        }

        Nodo<T> aux = cabeza;
        while (aux.getSiguiente().getSiguiente() != null) {
            aux = aux.getSiguiente();
        }
        T valor = aux.getSiguiente().getDato();
        aux.setSiguiente(null);
        size--;
        return valor;
    }

    public boolean eliminar(T dato) {
        if (estaVacia()) return false;

        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            size--;
            return true;
        }

        Nodo<T> aux = cabeza;
        while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(dato)) {
            aux = aux.getSiguiente();
        }

        if (aux.getSiguiente() != null) {
            aux.setSiguiente(aux.getSiguiente().getSiguiente());
            size--;
            return true;
        }

        return false;
    }

    public boolean modificarNodo(int index, T nuevoDato) {
        Nodo<T> nodo = obtenerNodo(index);
        if (nodo != null) {
            nodo.setDato(nuevoDato);
            return true;
        }
        return false;
    }

    public void ordenarLista() {
        if (size <= 1) return;

        for (int i = 0; i < size - 1; i++) {
            Nodo<T> actual = cabeza;
            Nodo<T> siguiente = cabeza.getSiguiente();

            for (int j = 0; j < size - i - 1; j++) {
                if (actual.getDato().compareTo(siguiente.getDato()) > 0) {
                    T temp = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temp);
                }
                actual = siguiente;
                siguiente = siguiente.getSiguiente();
            }
        }
    }

    public void imprimirLista() {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.getDato() + " -> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }

    public void borrarLista() {
        cabeza = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }
}