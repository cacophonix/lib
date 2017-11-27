package lib.cacophonix.generated.collections;

import lib.cacophonix.generated.collections.iterator.CharIterator;

public interface CharReversableCollection extends CharCollection {
    //abstract
    public CharIterator reverseIterator();

    //base
    default public char last() {
        return reverseIterator().value();
    }

    default CharStream reversed() {
        return () -> reverseIterator();
    }
}
