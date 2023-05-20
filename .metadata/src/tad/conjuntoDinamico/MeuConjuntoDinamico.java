package tad.conjuntoDinamico;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer>{

	private Integer[] meusDados = null;
	private int posInsercao = 0;
	
	@Override
	public void inserir(Integer item) {
		if (meusDados == null) {
			meusDados = new Integer[1];
		} else if (posInsercao >= meusDados.length) {
			meusDados = aumentarArray();
		}
		meusDados[posInsercao++] = item;
	}
	
	private Integer[] aumentarArray() {
		int novoTamanho = meusDados.length * 2;
		Integer[] novoArray = new Integer[novoTamanho];
		System.arraycopy(meusDados, 0, novoArray, 0, meusDados.length);
		return novoArray;
	}

	@Override
	public Integer remover(Integer item) {
		if (meusDados != null) {
			for (int i = 0; i < posInsercao; i++) {
				if (meusDados[i].equals(item)) {
					Integer valorRemovido = meusDados[i];
					System.arraycopy(meusDados, i + 1, meusDados, i, posInsercao - i - 1);
					posInsercao--;
					return valorRemovido;
				}
			}
		}
		return null;
	}

	@Override
	public Integer predecessor(Integer item) {
		Integer predecessor = null;
		if (meusDados != null) {
			for (int i = 0; i < posInsercao; i++) {
				if (meusDados[i].equals(item)) {
					return predecessor;
				}
				predecessor = meusDados[i];
			}
		}
		return null;
	}

	@Override
	public Integer sucessor(Integer item) {
		if (meusDados != null) {
			for (int i = 0; i < posInsercao; i++) {
				if (meusDados[i].equals(item)) {
					if (i + 1 < posInsercao) {
						return meusDados[i + 1];
					}
				}
			}
		}
		return null;
	}

	@Override
	public int tamanho() {
		return posInsercao;
	}

	@Override
	public Integer buscar(Integer item) {
		if (meusDados != null) {
			for (int i = 0; i < posInsercao; i++) {
				if (meusDados[i].equals(item)) {
					return meusDados[i];
				}
			}
		}
		return null;
	}

	@Override
	public Integer minimum() {
		if (meusDados != null && posInsercao > 0) {
			Integer min = meusDados[0];
			for (int i = 1; i < posInsercao; i++) {
				if (meusDados[i].compareTo(min) < 0) {
					min = meusDados[i];
				}
			}
			return min;
		}
		return null;
	}

	@Override
	public Integer maximum() {
		if (meusDados != null && posInsercao > 0) {
			Integer max = meusDados[0];
			for (int i = 1; i < posInsercao; i++) {
				if (meusDados[i].compareTo(max) > 0) {
					max = meusDados[i];
				}
			}
			return max;
		}
		return null;
	}
}

