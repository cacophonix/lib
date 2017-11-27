package lib.cacophonix.generated.collections.set;

import lib.cacophonix.generated.collections.DoubleCollection;

public interface DoubleSet extends DoubleCollection {
    @Override
    default public int count(double value) {
        return contains(value) ? 1 : 0;
    }
}
