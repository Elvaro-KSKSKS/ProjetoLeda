package tad.conjuntoDinamico;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

    private Node head;
    private int size;

    private class Node {
        Integer item;
        Node next;

        Node(Integer item) {
            this.item = item;
            this.next = null;
        }
    }

    @Override
    public void inserir(Integer item) {
        if (head == null) {
            head = new Node(item);
        } else {
            Node newNode = new Node(item);
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    @Override
    public Integer remover(Integer item) {
        if (head == null) {
            return null;
        }

        if (head.item.equals(item)) {
            Integer removedItem = head.item;
            head = head.next;
            size--;
            return removedItem;
        }

        Node current = head;
        while (current.next != null && !current.next.item.equals(item)) {
            current = current.next;
        }

        if (current.next != null) {
            Integer removedItem = current.next.item;
            current.next = current.next.next;
            size--;
            return removedItem;
        }

        return null;
    }

    @Override
    public Integer predecessor(Integer item) {
        Node current = head;
        Node predecessor = null;

        while (current != null && !current.item.equals(item)) {
            predecessor = current;
            current = current.next;
        }

        if (current != null && predecessor != null) {
            return predecessor.item;
        }

        return null;
    }

    @Override
    public Integer sucessor(Integer item) {
        Node current = head;

        while (current != null && !current.item.equals(item)) {
            current = current.next;
        }

        if (current != null && current.next != null) {
            return current.next.item;
        }

        return null;
    }

    @Override
    public int tamanho() {
        return size;
    }

    @Override
    public Integer buscar(Integer item) {
        Node current = head;

        while (current != null) {
            if (current.item.equals(item)) {
                return current.item;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public Integer minimum() {
        if (head == null) {
            return null;
        }

        Node current = head;
        Integer minValue = current.item;

        while (current != null) {
            if (current.item < minValue) {
                minValue = current.item;
            }
            current = current.next;
        }

        return minValue;
    }

    @Override
    public Integer maximum() {
        if (head == null) {
            return null;
        }

        Node current = head;
        Integer maxValue = current.item;

        while (current != null) {
            if (current.item > maxValue) {
                maxValue = current.item;
            }
            current = current.next;
        }

        return maxValue;
    }
}
