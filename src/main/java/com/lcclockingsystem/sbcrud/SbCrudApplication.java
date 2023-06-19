/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SbCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbCrudApplication.class, args);
	}

}
