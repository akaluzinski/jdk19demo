package eu.kaluzinski.sdjpaintro.concurrency_sandbox.lesson1;

import java.util.concurrent.Callable;

public class ApplePickerProvider {
    public static Callable<Void> createApplePicker(AppleTree[] trees, int startIndex, int endIndex, String workerName) {
        return () -> {
            return null;
        };
    }
}
