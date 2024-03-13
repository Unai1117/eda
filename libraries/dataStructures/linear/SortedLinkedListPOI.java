package libraries.dataStructures.linear;

import libraries.dataStructures.models.ListPOI;

public class SortedLinkedListPOI<E extends Comparable<E>> extends LinkedListPOI<E> implements ListPOI<E> {
    @Override
    public void add(E e) {
        if (this.isEmpty()) {
            super.add(e);
            return;
        }
        begin();
        while (!isEnd() && get().compareTo(e) < 0) {
            next();
        }
        super.add(e);
    }
}