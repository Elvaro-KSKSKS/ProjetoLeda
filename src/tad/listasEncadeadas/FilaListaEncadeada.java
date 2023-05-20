package tad.listasEncadeadas;

import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;

public class FilaListaEncadeada implements FilaIF<NodoListaEncadeada<Integer>> {

	private ListaEncadeadaIF<Integer> fila;

	public FilaListaEncadeada() {
		fila = new ListaEncadeada<>();
	}

	@Override
	public void enfileirar(NodoListaEncadeada<Integer> item) throws FilaCheiaException {
		fila.adicionar(item);
	}

	@Override
	public NodoListaEncadeada<Integer> desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException("A fila está vazia.");
		}
		NodoListaEncadeada<Integer> cabeca = verificarCabeca();
		fila.remover(0);
		return cabeca;
	}

	@Override
	public NodoListaEncadeada<Integer> verificarCauda() {
		if (isEmpty()) {
			return null;
		}
		return fila.obter(fila.tamanho() - 1);
	}

	@Override
	public NodoListaEncadeada<Integer> verificarCabeca() {
		if (isEmpty()) {
			return null;
		}
		return fila.obter(0);
	}

	@Override
	public boolean isEmpty() {
		return fila.tamanho() == 0;
	}

	@Override
	public boolean isFull() {
		// A lista encadeada não possui limite de capacidade, portanto a fila nunca fica cheia.
		return false;
	}
}

