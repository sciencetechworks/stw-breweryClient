package com.stw.breweryclient;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BreweryclientApplication {



	public static void main(String[] args) {

		//SpringApplication.run(BreweryclientApplication.class, args);
                new SpringApplicationBuilder(BreweryclientApplication.class).
                        web(WebApplicationType.NONE).run(args);
	}

}
