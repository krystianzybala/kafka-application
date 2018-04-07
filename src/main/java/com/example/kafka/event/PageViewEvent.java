package com.example.kafka.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class PageViewEvent {
    @Getter
    private String name, page;

    @Getter
    private long duration;
}
