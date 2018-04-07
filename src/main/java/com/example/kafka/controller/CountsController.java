package com.example.kafka.controller;

import com.example.kafka.AnalyticsBinding;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CountsController {
    private final QueryableStoreRegistry registry;

    public CountsController(QueryableStoreRegistry registry) {
        this.registry = registry;
    }

    @GetMapping("/counts")
    Map<String, Long> counts() {
        ReadOnlyKeyValueStore<String, Long> store = registry.getQueryableStoreType(AnalyticsBinding.PAGE_COUNT_MV, QueryableStoreTypes.keyValueStore());

        Map<String, Long> m = new HashMap<>();
        KeyValueIterator<String, Long> iterator = store.all();
        while (iterator.hasNext()) {
            KeyValue<String, Long> next = iterator.next();
            m.put(next.key, next.value);
        }
        return m;
    }
}
