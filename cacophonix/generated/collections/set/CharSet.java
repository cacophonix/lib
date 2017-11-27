package lib.cacophonix.generated.collections.set;

import lib.cacophonix.generated.collections.CharCollection;

public interface CharSet extends CharCollection {
    @Override
    default public int count(char value) {
        return contains(value) ? 1 : 0;
    }
}
