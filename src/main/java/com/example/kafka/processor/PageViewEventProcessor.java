package com.example.kafka.processor;

import com.example.kafka.AnalyticsBinding;
import com.example.kafka.event.PageViewEvent;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.example.kafka.AnalyticsBinding.PAGE_COUNT_MV;

@Log4j2
@Component
public class PageViewEventProcessor {

    @StreamListener
    @SendTo(AnalyticsBinding.PAGE_COUNT_OUT)
    public KStream<String, Long> process(@Input(AnalyticsBinding.PAGE_VIEWS_IN) KStream<String, PageViewEvent> events) {


        log.info("Handle {}", events);

        return events
                .filter((key, value) -> value.getDuration() > 10)
                .map((s, pageViewEvent) -> new KeyValue<>(pageViewEvent.getPage(), "0"))
                .groupByKey()
                .count(Materialized.as(PAGE_COUNT_MV))
                .toStream();

    }
}
