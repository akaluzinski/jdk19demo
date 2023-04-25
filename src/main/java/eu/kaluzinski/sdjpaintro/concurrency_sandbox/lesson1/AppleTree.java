package eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AppleTree {
    public static AppleTree[] newTreeGarden(int size) {
        AppleTree[] appleTrees = IntStream.range(0, size)
                .mapToObj(i -> new AppleTree("Apple Tree#%d".formatted(i)))
                .toArray(AppleTree[]::new);
        return appleTrees;
    }

    private final String treeLabel;
    private final int nunberOfApples;

    public AppleTree(String treeLabel) {
        this.treeLabel = treeLabel;
        nunberOfApples = 3;
    }

    public int pickApples(String workerName) {
        try {
            System.out.printf("%s started picking from %s%n", workerName, treeLabel);
            TimeUnit.SECONDS.sleep(1);
            System.out.printf("%s picked from %s%n", workerName, treeLabel);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return nunberOfApples;
    }

}
