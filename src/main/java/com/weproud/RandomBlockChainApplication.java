package com.weproud;

import com.weproud.blockchain.node.FullIndexNodeRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RandomBlockChainApplication {

	@Autowired
	private FullIndexNodeRunner runner;

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			runner.run();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RandomBlockChainApplication.class, args);
	}
}
