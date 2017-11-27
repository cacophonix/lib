package lib.cacophonix.generated.collections.set;

import lib.cacophonix.generated.collections.LongCollection;

public interface LongSet extends LongCollection {
    @Override
    default public int count(long value) {
        return contains(value) ? 1 : 0;
    }
}
