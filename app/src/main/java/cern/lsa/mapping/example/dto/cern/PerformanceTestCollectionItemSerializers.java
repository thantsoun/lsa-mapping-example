package cern.lsa.mapping.example.dto.cern;

import cern.lsa.mapping.example.domain.MapKey;
import cern.lsa.mapping.example.domain.MapKeyImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PerformanceTestCollectionItemSerializers {

    private static int collectionLength = 100000;
    private static int loopTimes = 1000;

    private static MapKey thanos = new MapKeyImpl("Thanos", 1);
    private static List<Object> thanosList = new ArrayList<>(collectionLength);
    private static Object[] thanosArray = new Object[collectionLength];
    static {
        for (int i = 0; i < collectionLength; i++) {
            thanosList.add(thanos);
            thanosArray[i] = thanos;
        }
    }

    private static ObjectMapper getCustomObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.setSerializerModifier(new MapSerializerModifier());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    private static ObjectMapper getSimpleObjectMapper() {
        return new ObjectMapper();
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper customObjectMapper = getCustomObjectMapper();
        ObjectMapper simpleObjectMapper = getSimpleObjectMapper();
        long start = System.nanoTime();
        long end = start;
        while ((end - start) < TimeUnit.MINUTES.toNanos(5)) {
            customObjectMapper.writeValueAsString(thanosArray);
            customObjectMapper.writeValueAsString(thanosList);
            simpleObjectMapper.writeValueAsString(thanosArray);
            simpleObjectMapper.writeValueAsString(thanosList);
            end = System.nanoTime();
        }
        StopWatch stopWatchCustom = new StopWatch("Custom");
        stopWatchCustom.start();
        for (int i = 0; i < loopTimes; i++) {
            customObjectMapper.writeValueAsString(thanosArray);
            customObjectMapper.writeValueAsString(thanosList);
        }
        stopWatchCustom.stop();
        StopWatch stopWatchVanilla = new StopWatch("Vanilla");
        stopWatchVanilla.start();
        for (int i = 0; i < loopTimes; i++) {
            simpleObjectMapper.writeValueAsString(thanosArray);
            simpleObjectMapper.writeValueAsString(thanosList);
        }
        stopWatchVanilla.stop();
        System.out.println(stopWatchCustom.shortSummary() + ", Seconds: " + stopWatchCustom.getTotalTimeSeconds() + ", per single call: " + (stopWatchCustom.getTotalTimeMillis() / (double) loopTimes) + ", per element call: " + (stopWatchCustom.getTotalTimeNanos() / (double) (loopTimes*collectionLength)));
        System.out.println(stopWatchVanilla.shortSummary() + ", Seconds: " + stopWatchVanilla.getTotalTimeSeconds() + ", per single call: " + (stopWatchVanilla.getTotalTimeMillis() / (double) loopTimes) + ", per element call: " + (stopWatchVanilla.getTotalTimeNanos() / (double) (loopTimes*collectionLength)));
        System.out.println((stopWatchVanilla.getTotalTimeNanos() / (double) stopWatchCustom.getTotalTimeNanos()));
    }



}
