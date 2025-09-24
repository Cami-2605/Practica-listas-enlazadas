package ListaCircularDoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircularDoble<T extends Comparable<T>> implements Iterable<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamanio;

    public ListaCircularDoble() {
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
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            nuevo.anterior = cola;
            cabeza.anterior = nuevo;
            cola.siguiente = nuevo;
            cabeza = nuevo;
        }
        tamanio++;
    }

    public void agregarFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.anterior = cola;
            nuevo.siguiente = cabeza;
            cola.siguiente = nuevo;
            cabeza.anterior = nuevo;
            cola = nuevo;
        }
        tamanio++;
    }

    public void agregar(T dato, int pos) {
        if (pos == 0) {
            agregarInicio(dato);
        } else if (pos == tamanio) {
            agregarFinal(dato);
        } else if (indiceValido(pos)) {
            NodoDoble<T> temp = cabeza;
            for (int i = 0; i < pos; i++) {
                temp = temp.siguiente;
            }
            NodoDoble<T> nuevo = new NodoDoble<>(dato);
            nuevo.siguiente = temp;
            nuevo.anterior = temp.anterior;
            temp.anterior.siguiente = nuevo;
            temp.anterior = nuevo;
            tamanio++;
        } else {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
    }

    public T obtenerValorNodo(int pos) {
        return obtenerNodo(pos).dato;
    }

    public NodoDoble<T> obtenerNodo(int pos) {
        if (!indiceValido(pos)) throw new IndexOutOfBoundsException();
        NodoDoble<T> temp = cabeza;
        for (int i = 0; i < pos; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    public int obtenerPosicionNodo(T dato) {
        if (estaVacia()) return -1;
        NodoDoble<T> temp = cabeza;
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
            cabeza.anterior = cola;
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
            cola = cola.anterior;
            cola.siguiente = cabeza;
            cabeza.anterior = cola;
        }
        tamanio--;
    }

    public void eliminar(T dato) {
        if (estaVacia()) return;

        NodoDoble<T> temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if (temp.dato.equals(dato)) {
                if (temp == cabeza) {
                    eliminarPrimero();
                } else if (temp == cola) {
                    eliminarUltimo();
                } else {
                    temp.anterior.siguiente = temp.siguiente;
                    temp.siguiente.anterior = temp.anterior;
                    tamanio--;
                }
                return;
            }
            temp = temp.siguiente;
        }
    }

    public void modificarNodo(int pos, T nuevoDato) {
        NodoDoble<T> nodo = obtenerNodo(pos);
        nodo.dato = nuevoDato;
    }

    public void ordenarLista() {
        if (tamanio <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            NodoDoble<T> actual = cabeza;
            for (int i = 0; i < tamanio - 1; i++) {
                NodoDoble<T> siguiente = actual.siguiente;
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
        NodoDoble<T> temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            System.out.print(temp.dato + " <-> ");
            temp = temp.siguiente;
        }
        System.out.println("(cabeza)");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = cabeza;
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
