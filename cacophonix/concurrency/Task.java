package lib.cacophonix.concurrency;

import lib.cacophonix.utils.io.OutputWriter;
import lib.cacophonix.utils.io.InputReader;

/**
 * @author egor@egork.net
 */
public interface Task {
    public void read(InputReader in);

    public void solve();

    public void write(OutputWriter out, int testNumber);
}
