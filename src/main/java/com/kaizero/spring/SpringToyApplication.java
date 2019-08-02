package com.kaizero.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

@EntityScan(basePackages={"com.kaizero.spring.entity"})
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringToyApplication {

	private final Map<Integer, IntegrationFlowContext.IntegrationFlowRegistration> registrations = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(SpringToyApplication.class, args);
	}

	//https://stackoverflow.com/questions/48213450/spring-integration-multiple-udp-inbound-outbound-channels
	@Bean
	public PublishSubscribeChannel channel() {
		return new PublishSubscribeChannel();
	}

	@Bean
	public ApplicationRunner runner(PublishSubscribeChannel channel) {
		return args -> {
			makeANewUdpAdapter(1234);
			makeANewUdpAdapter(1235);
			channel.send(MessageBuilder.withPayload("foo\n").build());
			registrations.values().forEach(r -> {
				r.stop();
				r.destroy();
			});
		};
	}

	@Autowired
	private IntegrationFlowContext flowContext;

	public void makeANewUdpAdapter(int port) {
		System.out.println("Creating an adapter to send to port " + port);
		IntegrationFlow flow = IntegrationFlows.from(channel())
				.handle(Udp.outboundAdapter("localhost", port))
				.get();
		IntegrationFlowContext.IntegrationFlowRegistration registration = flowContext.registration(flow).register();
		registrations.put(port, registration);
	}
}
