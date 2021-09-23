package hcl.hackathon.hcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class HclApplication {

	public static void main(String[] args) {
		SpringApplication.run(HclApplication.class, args);
	}

}
