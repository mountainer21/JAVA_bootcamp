package ex05;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private TransactionNode first;
    private TransactionNode last;
    private int size;

    private static class TransactionNode {
        Transaction transaction;
        TransactionNode next;
        TransactionNode prev;

        TransactionNode(Transaction transaction, TransactionNode next, TransactionNode prev) {
            this.transaction = transaction;
            this.next = next;
            this.prev = prev;
        }
    }

    public TransactionsLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        TransactionNode node = new TransactionNode(transaction, null, last);
        if (last != null) {
            last.next = node;
        } else {
            first = node;
        }
        last = node;
        size++;
    }

    @Override
    public void removeTransaction(UUID identifier) throws TransactionNotFoundException {
        TransactionNode current = first;
        while (current != null) {
            if (current.transaction.getIdentifier().equals(identifier)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    first = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    last = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
        throw new TransactionNotFoundException("Transaction with ID " + identifier + " does not exist.");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        TransactionNode current = first;
        int i = 0;
        while (current != null) {
            array[i++] = current.transaction;
            current = current.next;
        }
        return array;
    }
}
