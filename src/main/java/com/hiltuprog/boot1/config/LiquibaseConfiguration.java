package com.hiltuprog.boot1.config;

//import io.github.jhipster.config.JHipsterConstants;
//import io.github.jhipster.config.liquibase.SpringLiquibaseUtil;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@Configuration
//@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfiguration {
	@Autowired
	private DataSource dataSource;
	private final Logger log = LoggerFactory.getLogger(LiquibaseConfiguration.class);

	private final Environment env;

	public LiquibaseConfiguration(Environment env) {
        this.env = env;
    }

	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:config/liquibase/master.xml");
		// Configure rest of liquibase here...
		//if (env.acceptsProfiles(Profiles.of("no-liquibase"))) {
            liquibase.setShouldRun(false);
        //} else {
        //    liquibase.setShouldRun(true);
        //}
		return liquibase;
	}
	
}
