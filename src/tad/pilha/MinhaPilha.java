package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {

    private int tamanho;
    private Integer[] meusDados;
    private int topo;

    public MinhaPilha(int tamanho) {
        this.tamanho = tamanho;
        this.meusDados = new Integer[tamanho];
        this.topo = -1;
    }

    public MinhaPilha() {
        this(10); // Chama o construtor com tamanho padrão de 10 elementos
    }

    @Override
    public void empilhar(Integer item) {
        if (topo == tamanho - 1) {
            throw new StackOverflowError("A pilha está cheia");
        }
        topo++;
        meusDados[topo] = item;
    }

    @Override
    public Integer desempilhar() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        Integer item = meusDados[topo];
        meusDados[topo] = null;
        topo--;
        return item;
    }

    @Override
    public Integer topo() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        return meusDados[topo];
    }

    @Override
    public PilhaIF<Integer> multitop(int k) {
        if (k <= 0 || k > tamanho) {
            throw new IllegalArgumentException("O valor de k é inválido");
        }

        PilhaIF<Integer> multitopPilha = new MinhaPilha(k);
        for (int i = topo; i >= topo - k + 1; i--) {
            multitopPilha.empilhar(meusDados[i]);
        }
        return multitopPilha;
    }

    @Override
    public boolean isEmpty() {
        return topo == -1;
    }
}
