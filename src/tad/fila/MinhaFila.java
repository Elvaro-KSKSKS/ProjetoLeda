package tad.fila;

public class MinhaFila implements FilaIF<Integer> {

    private int tamanho = 10;
    private int cauda = 1;
    private int cabeca = 0;
    private Integer[] meusDados = null;

    public MinhaFila(int tamanhoInicial) {
        tamanho = tamanhoInicial;
        meusDados = new Integer[tamanho];
    }

    public MinhaFila() {
        meusDados = new Integer[tamanho];
    }

    @Override
    public void enfileirar(Integer item) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia");
        }
        meusDados[cauda] = item;
        cauda = (cauda + 1) % tamanho;
    }

    @Override
    public Integer desenfileirar() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        Integer item = meusDados[cabeca];
        meusDados[cabeca] = null;
        cabeca = (cabeca + 1) % tamanho;
        return item;
    }

    @Override
    public Integer verificarCauda() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return meusDados[(cauda - 1 + tamanho) % tamanho];
    }

    @Override
    public Integer verificarCabeca() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return meusDados[cabeca];
    }

    @Override
    public boolean isEmpty() {
        return cabeca == (cauda + 1) % tamanho;
    }

    @Override
    public boolean isFull() {
        return cabeca == (cauda + 2) % tamanho;
    }
}
