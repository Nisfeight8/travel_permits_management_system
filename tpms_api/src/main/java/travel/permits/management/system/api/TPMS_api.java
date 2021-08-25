package travel.permits.management.system.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TPMS_api {

	public static void main(String[] args) {
		SpringApplication.run(TPMS_api.class, args);
	}

}
