package eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AppleTree {
    public static AppleTree[] newTreeGarden(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> new AppleTree("Apple Tree#%d".formatted(i)))
                .toArray(AppleTree[]::new);
    }

    private final String treeLabel;
    private final int numberOfApples;

    public AppleTree(String treeLabel) {
        this.treeLabel = treeLabel;
        numberOfApples = 3;
    }

    public int pickApples() {
        try {
            TimeUnit.MICROSECONDS.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return numberOfApples;
    }

}
