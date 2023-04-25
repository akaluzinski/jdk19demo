package eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

@AllArgsConstructor
public class PickFruitTask extends RecursiveTask<Integer> {
    private final AppleTree[] trees;
    private final int start;
    private final int end;
    private final int taskThreshold = 4;

    private Integer doCompute() {
        return IntStream.rangeClosed(start, end)
                .map(i -> trees[i].pickApples())
                .sum();
    }

    @Override
    protected Integer compute() {
        if (end - start < taskThreshold) {
            return doCompute();
        }

        int midpoint = start + (end - start) / 2;
        PickFruitTask leftSum = new PickFruitTask(trees, start, midpoint);
        PickFruitTask rightSum = new PickFruitTask(trees, midpoint + 1, end);
        rightSum.fork();
        return leftSum.compute() + rightSum.compute();
    }
}
