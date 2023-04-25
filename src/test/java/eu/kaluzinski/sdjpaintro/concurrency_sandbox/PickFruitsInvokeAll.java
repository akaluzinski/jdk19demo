package eu.kaluzinski.sdjpaintro.concurrency_sandbox;

import eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1.AppleTree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import static eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1.ApplePickerProvider.createApplePicker;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PickFruitsInvokeAll {

    @Test
    public void shouldPickAll() {
        AppleTree[] appleTrees = AppleTree.newTreeGarden(6);
        Callable<Integer> applePicker1 = createApplePicker(appleTrees, 0, 2, "Picker1");
        Callable<Integer> applePicker2 = createApplePicker(appleTrees, 2, 4, "Picker2");
        Callable<Integer> applePicker3 = createApplePicker(appleTrees, 4, 6, "Picker3");
        List<Future<Integer>> futures = ForkJoinPool.commonPool()
                .invokeAll(Arrays.asList(applePicker1, applePicker2, applePicker3));

        int result = futures.stream().map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).mapToInt(Integer::intValue).sum();


        assertEquals(result, 18);
    }

}
