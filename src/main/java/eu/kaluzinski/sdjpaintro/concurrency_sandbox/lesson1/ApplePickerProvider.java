package eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

public class ApplePickerProvider {
    public static Callable<Integer> createApplePicker(AppleTree[] trees, int startIndex, int endIndex, String workerName) {
        return () -> IntStream.range(startIndex, endIndex).
                map(i -> trees[i].pickApples(workerName))
                .sum();
    }
}
