package tad.listasEncadeadas;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

    // TODO: implementar o nó cabeça
    // TODO: implementar o nó cauda
    // TODO: implementar as sentinelas

    NodoListaDuplamenteEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
    NodoListaDuplamenteEncadeada<T> cauda = null; // Estratégia usando marcação sentinela

    public ListaDuplamenteEncadeadaImpl() { // Estratégia usando marcação sentinela
        cabeca = new NodoListaDuplamenteEncadeada<T>();
        cauda = new NodoListaDuplamenteEncadeada<T>();
        cabeca.setProximo(cauda);

        // lista circular
        cabeca.setAnterior(cauda);
        cauda.setAnterior(cabeca);
    }

    @Override
    public boolean isEmpty() {
        return cabeca.getProximo() == cauda;
    }

    @Override
    public int size() {
        int count = 0;
        NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            count++;
            atual = atual.getProximo();
        }
        return count;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> search(T chave) {
        NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            if (atual.getElemento().equals(chave)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public void insert(T chave) {
        // 1. Criar o novo registro
        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);

        // 2. Inserir o novo nó na lista
        novoNo.setProximo(cabeca.getProximo());
        ((NodoListaDuplamenteEncadeada<T>) cabeca.getProximo()).setAnterior(novoNo);
        novoNo.setAnterior(cabeca);
        cabeca.setProximo(novoNo);
    }

    @Override
    public NodoListaEncadeada<T> remove(T chave) {
        NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            if (atual.getElemento().equals(chave)) {
                atual.getAnterior().setProximo(atual.getProximo());
                ((NodoListaDuplamenteEncadeada<T>) atual.getProximo()).setAnterior(atual.getAnterior());
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public String imprimeEmOrdem() {
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            sb.append(atual.getElemento()).append(" ");
            atual = atual.getProximo();
        }
        return sb.toString().trim();
    }

    @Override
    public String imprimeInverso() {
        StringBuilder sb = new StringBuilder();
        NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();
        while (atual != cabeca) {
            sb.append(atual.getElemento()).append(" ");
            atual = atual.getAnterior();
        }
        return sb.toString().trim();
    }

    @Override
    public NodoListaEncadeada<T> sucessor(T chave) {
        NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
        while (atual != cauda) {
            if (atual.getElemento().equals(chave)) {
                return atual.getProximo();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public NodoListaEncadeada<T> predecessor(T chave) {
        NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();
        while (atual != cabeca) {
            if (atual.getElemento().equals(chave)) {
                return atual.getAnterior();
            }
            atual = atual.getAnterior();
        }
        return null;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        int size = size();
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
        NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
        for (int i = 0; i < size; i++) {
            array[i] = atual.getElemento();
            atual = atual.getProximo();
        }
        return array;
    }

    @Override
    public void inserePrimeiro(T elemento) {
        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(elemento);
        novoNo.setProximo(cabeca.getProximo());
        ((NodoListaDuplamenteEncadeada<T>) cabeca.getProximo()).setAnterior(novoNo);
        novoNo.setAnterior(cabeca);
        cabeca.setProximo(novoNo);
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removeUltimo() {
        NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();
        if (ultimo != cabeca) {
            ultimo.getAnterior().setProximo(cauda);
            cauda.setAnterior(ultimo.getAnterior());
            return ultimo;
        }
        return null;
    }

    @Override
    public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
        NodoListaDuplamenteEncadeada<T> primeiro = cabeca.getProximo();
        if (primeiro != cauda) {
            cabeca.setProximo(primeiro.getProximo());
            ((NodoListaDuplamenteEncadeada<T>) primeiro.getProximo()).setAnterior(cabeca);
            return primeiro;
        }
        return null;
    }

    @Override
    public void insert(T chave, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        NodoListaDuplamenteEncadeada<T> atual = cabeca;
        for (int i = 0; i < index; i++) {
            atual = atual.getProximo();
        }

        NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);
        novoNo.setProximo(atual.getProximo());
        ((NodoListaDuplamenteEncadeada<T>) atual.getProximo()).setAnterior(novoNo);
        novoNo.setAnterior(atual);
        atual.setProximo(novoNo);
    }
}