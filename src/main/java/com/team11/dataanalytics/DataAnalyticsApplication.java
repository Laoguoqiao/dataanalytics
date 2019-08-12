package com.team11.dataanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication

public class DataAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAnalyticsApplication.class, args);
	}



}
