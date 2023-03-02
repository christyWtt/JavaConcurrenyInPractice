package chapter4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapSafetyTest {

    @Test
    public void unsafeHashMapTest() {
        Map<String, Integer> map = new HashMap<>();

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(()-> put10000RandomValues(map));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(()-> put10000RandomValues(map));

        List<CompletableFuture<Void>> futures = List.of(future1, future2);
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        assertEquals(map.size(), 20000);
    }

    @Test
    public void safeImprovedMapTest() {
        ImprovedMap<String, Integer> map = new ImprovedMap<>(new HashMap<>());

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(()-> put10000RandomValues(map));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(()-> put10000RandomValues(map));

        List<CompletableFuture<Void>> futures = List.of(future1, future2);
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        assertEquals(map.size(), 20000);
    }

    private void put10000RandomValues(Map<String, Integer> map) {
        IntStream.range(0,10000).forEach(number -> map.put(UUID.randomUUID().toString(), number));
    }
}
