package tad.fila;

public class MinhaFilaEncadeada implements FilaIF<Integer> {
	
	private Node cabeca; // Referência para o primeiro nó da fila
	private Node cauda; // Referência para o último nó da fila
	private int tamanho; // Variável para acompanhar o tamanho da fila
	
	private class Node {
		private Integer item;
		private Node proximo;
		
		public Node(Integer item) {
			this.item = item;
			this.proximo = null;
		}
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		Node novoNo = new Node(item);
		
		if (isEmpty()) {
			cabeca = novoNo;
			cauda = novoNo;
		} else {
			cauda.proximo = novoNo;
			cauda = novoNo;
		}
		
		tamanho++;
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException("A fila está vazia.");
		}
		
		Integer item = cabeca.item;
		cabeca = cabeca.proximo;
		tamanho--;
		
		if (isEmpty()) {
			cauda = null;
		}
		
		return item;
	}

	@Override
	public Integer verificarCauda() {
		if (isEmpty()) {
			return null;
		}
		
		return cauda.item;
	}

	@Override
	public Integer verificarCabeca() {
		if (isEmpty()) {
			return null;
		}
		
		return cabeca.item;
	}

	@Override
	public boolean isEmpty() {
		return tamanho == 0;
	}

	@Override
	public boolean isFull() {
		// A fila encadeada não tem limite máximo, então não é possível ficar cheia
		return false;
	}

}
