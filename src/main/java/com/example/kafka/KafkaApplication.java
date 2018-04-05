package com.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {

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