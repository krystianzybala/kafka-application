package com.example.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableBinding(AnalyticsBinding.class)
@Log4j2
public class KafkaApplication {



	@Component
	public static class PageViewEventSource implements ApplicationRunner {

		private final MessageChannel pageViewsOut;

		public PageViewEventSource(final AnalyticsBinding binding) {
			this.pageViewsOut = binding.pageViewsOut();
		}


		@Override
		public void run(final ApplicationArguments args) throws Exception {

			final List<String> names = Arrays.asList("franek", "stefan", "zdzisiek");

			final List<String> pages = Arrays.asList("blog", "about", "contact");


			Runnable runnable  = () -> {

				final String rPage = pages.get(new Random().nextInt(pages.size()));
				final String rName = names.get(new Random().nextInt(names.size()));


				final PageViewEvent pageViewEvent = new PageViewEvent(rName, rPage, Math.random() > .5 ? 10 : 1000);

				final Message<PageViewEvent> message = MessageBuilder
						.withPayload(pageViewEvent)
						.setHeader(KafkaHeaders.MESSAGE_KEY, pageViewEvent.getName().getBytes())
						.build();


				try {
					this.pageViewsOut.send(message);
					log.info("Sent message {}", message);
				} catch (Exception e) {
					 log.error("Error occurred during publish message.  {}", message, e);
				}
			};


			Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);

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