package lib.cacophonix.generated.collections.queue;

import lib.cacophonix.generated.collections.CharCollection;

public interface CharQueue extends CharCollection {
    default public char first() {
        return peek();
    }

    public char peek();

    public char poll();
}
