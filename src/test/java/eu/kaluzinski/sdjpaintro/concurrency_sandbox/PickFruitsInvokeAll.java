package eu.kaluzinski.sdjpaintro.concurrency_sandbox;

import eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1.AppleTree;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1.ApplePickerProvider.createApplePicker;

public class PickFruitsInvokeAll {

    @Test
    public void shouldPickAll() {
        AppleTree[] appleTrees = AppleTree.newTreeGarden(6);
        Callable<Void> applePicker1 = createApplePicker(appleTrees, 0, 2, "Picker1");
        Callable<Void> applePicker2 = createApplePicker(appleTrees, 2, 4, "Picker2");
        Callable<Void> applePicker3 = createApplePicker(appleTrees, 4, 6, "Picker3");
        System.out.println("All apples are picked");
    }

}
