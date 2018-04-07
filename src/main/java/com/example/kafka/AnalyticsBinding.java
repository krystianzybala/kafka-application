package com.example.kafka;


import com.example.kafka.event.PageViewEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AnalyticsBinding {

    String PAGE_VIEWS_OUT = "pvout";
    String PAGE_VIEWS_IN = "pvin";

    String PAGE_COUNT_MV = "pcmv";

    String PAGE_COUNT_OUT = "pcout";

    String PAGE_COUNT_IN = "pcin";

    @Output(PAGE_VIEWS_OUT)
    MessageChannel pageViewsOut();


    @Input(PAGE_VIEWS_IN)
    KStream<String, PageViewEvent> pageViewsIn();


    @Output(PAGE_COUNT_OUT)
    KStream<String, Long> pageCountOut();


    @Input(PAGE_COUNT_IN)
    KTable<String, Long> pageCountIn();
}