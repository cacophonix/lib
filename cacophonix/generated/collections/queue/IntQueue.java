package lib.cacophonix.generated.collections.queue;

import lib.cacophonix.generated.collections.IntCollection;

public interface IntQueue extends IntCollection {
    default public int first() {
        return peek();
    }

    public int peek();

    public int poll();
}
