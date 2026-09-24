package com.davemuirhead.drone_sim_consumer_j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * DroneSimConsumerJApplication is the main entry point for the
 * Drone Simulation Consumer application.
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class DroneSimConsumerJApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneSimConsumerJApplication.class, args);
	}
}
