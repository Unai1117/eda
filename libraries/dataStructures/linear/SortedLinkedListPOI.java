package libraries.dataStructures.linear;

import libraries.dataStructures.models.ListPOI;

public class SortedLinkedListPOI<E extends Comparable<E>> extends LinkedListPOI<E> implements ListPOI<E> {
    @Override
    public void add(E var1) { 
        if(this.isEmpty()){
            super.add(var1);
            return;
        } 
        begin();
        while(!isEnd() && get().compareTo(var1) < 0){
            next(); 
        }
        super.add(var1);
        return; 
    }
}
