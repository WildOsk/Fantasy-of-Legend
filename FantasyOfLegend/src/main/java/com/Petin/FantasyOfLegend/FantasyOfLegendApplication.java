package com.Petin.FantasyOfLegend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FantasyOfLegendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyOfLegendApplication.class, args);
	}


}
