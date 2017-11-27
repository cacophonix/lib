package lib.cacophonix.generated.collections.set;

import lib.cacophonix.generated.collections.IntCollection;

public interface IntSet extends IntCollection {
    @Override
    default public int count(int value) {
        return contains(value) ? 1 : 0;
    }
}
