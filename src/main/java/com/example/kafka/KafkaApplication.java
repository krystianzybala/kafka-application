package com.example.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding(AnalyticsBinding.class)
public class KafkaApplication {



	@Component
	public static class PageViewEventSource implements ApplicationRunner {

		@Override
		public void run(final ApplicationArguments args) throws Exception {

		}
	}
	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}
}

interface AnalyticsBinding {

    String PAGE_VIEWS_OUT = "pvout";

    @Output(PAGE_VIEWS_OUT)
    MessageChannel pageViewsOut();
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class PageViewEvent {
	private String  name, page;
	private long duration;
}