package com.gorg.homeless;
import org.h2gis.utilities.SFSUtilities;
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
	public DataSource getDataSource() throws SQLException {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:tcp://localhost:5555/bazada");
		dataSourceBuilder.username("SA");
		dataSourceBuilder.password("");
		DataSource dataSource = dataSourceBuilder.build();
		DataSource wrappedDataSource = SFSUtilities.wrapSpatialDataSource(dataSource);
		return wrappedDataSource;
	}

}
