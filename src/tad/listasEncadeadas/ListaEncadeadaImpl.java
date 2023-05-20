package tad.listasEncadeadas;

public class ListaEncadeada<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

    private NodoListaEncadeada<T> primeiro;
    private NodoListaEncadeada<T> ultimo;
    private int tamanho;

    public ListaEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public NodoListaEncadeada<T> search(T chave) {
        NodoListaEncadeada<T> atual = primeiro;
        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<>(chave);
        if (isEmpty()) {
            primeiro = novoNodo;
            ultimo = novoNodo;
        } else {
            ultimo.setProximo(novoNodo);
            ultimo = novoNodo;
        }
        tamanho++;
    }

    @Override
    public void insert(T chave, int index) {
        if (index < 0 || index > tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        if (index == tamanho) {
            insert(chave);
            return;
        }

        NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<>(chave);
        if (index == 0) {
            novoNodo.setProximo(primeiro);
            primeiro = novoNodo;
        } else {
            NodoListaEncadeada<T> anterior = getNodo(index - 1);
            NodoListaEncadeada<T> atual = anterior.getProximo();
            anterior.setProximo(novoNodo);
            novoNodo.setProximo(atual);
        }
        tamanho++;
    }

    @Override
    public NodoListaEncadeada<T> remove(T chave) {
        if (isEmpty()) {
            return null;
        }

        NodoListaEncadeada<T> nodoRemovido = null;
        if (primeiro.getChave().equals(chave)) {
            nodoRemovido = primeiro;
            primeiro = primeiro.getProximo();
            if (primeiro == null) {
                ultimo = null;
            }
        } else {
            NodoListaEncadeada<T> anterior = primeiro;
            NodoListaEncadeada<T> atual = primeiro.getProximo();
            while (atual != null && !atual.getChave().equals(chave)) {
                anterior = atual;
                atual = atual.getProximo();
            }
            if (atual != null) {
                nodoRemovido = atual;
                anterior.setProximo(atual.getProximo());
                if (atual == ultimo) {
                    ultimo = anterior;
                }
            }
        }

        if (nodoRemovido != null) {
            tamanho--;
            nodoRemovido.setProximo(null);
        }
        return nodoRemovido;
    }

    @Override
    public String imprimeEmOrdem() {
        StringBuilder sb = new StringBuilder();
        NodoListaEncadeada<T> atual = primeiro;
        while (atual != null) {
            sb.append(atual.getChave()).append(" ");
            atual = atual.getProximo();
        }
        return sb.toString().trim();
    }

    @Override
    public String imprimeInverso() {
        StringBuilder sb = new StringBuilder();
        imprimeInversoRecursivo(primeiro, sb);
        return sb.toString().trim();
    }

    private void imprimeInversoRecursivo(NodoListaEncadeada<T> nodo, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        imprimeInversoRecursivo(nodo.getProximo(), sb);
        sb.append(nodo.getChave()).append(" ");
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) {
        NodoListaEncadeada<T> nodo = search(chave);
        if (nodo != null && nodo.getProximo() != null) {
            return nodo.getProximo();
        }
        return null;
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) {
        if (isEmpty()) {
            return null;
        }

        if (primeiro.getChave().equals(chave)) {
            return null;
        }

        NodoListaEncadeada<T> anterior = primeiro;
        NodoListaEncadeada<T> atual = primeiro.getProximo();
        while (atual != null && !atual.getChave().equals(chave)) {
            anterior = atual;
            atual = atual.getProximo();
        }
        if (atual != null) {
            return anterior;
        }
        return null;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, tamanho);
        NodoListaEncadeada<T> atual = primeiro;
        int index = 0;
        while (atual != null) {
            array[index++] = atual.getChave();
            atual = atual.getProximo();
        }
        return array;
    }

    private NodoListaEncadeada<T> getNodo(int index) {
        NodoListaEncadeada<T> atual = primeiro;
        for (int i = 0; i < index; i++) {
            atual = atual.getProximo();
        }
        return atual;
    }
}
