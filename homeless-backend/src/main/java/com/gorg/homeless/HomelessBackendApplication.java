package com.gorg.homeless;
import org.h2gis.utilities.SFSUtilities;
import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class HomelessBackendApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(HomelessBackendApplication.class, args);
	}


	@Bean
	public JtsModule jtsModule() {
		return new JtsModule();
	}
}
