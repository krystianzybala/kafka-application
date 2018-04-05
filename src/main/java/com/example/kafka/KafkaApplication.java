package com.example.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
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


@Data
@AllArgsConstructor
@NoArgsConstructor
class PageViewEvent {
	private String id, name;
	private long duration;
}