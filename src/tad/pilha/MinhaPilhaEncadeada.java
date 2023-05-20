package tad.pilha;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {
	
	private ListaEncadeadaIF<Integer> listaEncadeada;

	public MinhaPilhaEncadeada() {
		listaEncadeada = new MinhaListaEncadeada<Integer>();
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		listaEncadeada.insere(item);
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException("A pilha está vazia");
		}
		Integer item = topo();
		listaEncadeada.remove();
		return item;
	}

	@Override
	public Integer topo() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException("A pilha está vazia");
		}
		return listaEncadeada.getPrimeiro();
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException("A pilha está vazia");
		}
		PilhaIF<Integer> pilhaMultitop = new MinhaPilhaEncadeada();
		ListaEncadeadaIF<Integer> novaListaEncadeada = listaEncadeada.copiar();
		for (int i = 0; i < k; i++) {
			if (novaListaEncadeada.estaVazia()) {
				break;
			}
			try {
				pilhaMultitop.empilhar(novaListaEncadeada.getPrimeiro());
				novaListaEncadeada.remove();
			} catch (PilhaCheiaException e) {
				// A pilha multitop está cheia, mas ainda é possível retornar os elementos empilhados até agora
				break;
			}
		}
		return pilhaMultitop;
	}

	@Override
	public boolean isEmpty() {
		return listaEncadeada.estaVazia();
	}
}

