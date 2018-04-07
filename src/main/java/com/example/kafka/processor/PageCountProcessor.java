package com.example.kafka.processor;
import com.example.kafka.AnalyticsBinding;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PageCountProcessor {
    @StreamListener
    public void process(@Input(AnalyticsBinding.PAGE_COUNT_IN) KTable<String, Long> counts) {
        counts
                .toStream()
                .foreach((key, value) -> log.info("Key: {} Value: {}", key, value));
    }
}
