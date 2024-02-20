package cz.utb.duck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DuckApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuckApplication.class, args);
	}

}
