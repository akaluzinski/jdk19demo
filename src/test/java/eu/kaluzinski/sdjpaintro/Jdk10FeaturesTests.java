package eu.kaluzinski.sdjpaintro;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class Jdk10FeaturesTests {

	@Test
	public void shouldThrowUnsupportedOperationExceptionWhileAddingValueToReadOnlyArray() {
		assertThrows(UnsupportedOperationException.class, () -> {
			List<Integer> copyList = List.copyOf(List.of(1));
			copyList.add(4);
		});
	}
}
