package com.tvo.configuration;

import java.util.Locale;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import com.jolbox.bonecp.BoneCPDataSource;
import com.tvo.framework.common.util.Constants;
import com.tvo.pms.listener.TrackerHttpSessionListenner;

@Configuration
//For Development
@PropertySources({ 
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:database_dev.properties"),
	@PropertySource("classpath:configuration_dev.properties")
})

//For Testing
//@PropertySources({ 
//	@PropertySource("classpath:application.properties"),
//	@PropertySource("classpath:appconfig_test.properties"),
//	@PropertySource("classpath:configuration_test.properties")
//})
// For Product
//@PropertySources({ 
//	@PropertySource("classpath:application.properties"),
//	@PropertySource("classpath:appconfig.properties"),
//	@PropertySource("classpath:configuration.properties")
//})
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
    private Environment environment;
	
//	@Bean
//	public TrackerHttpSessionListenner trackerHttpSessionListenner(){
//		return new TrackerHttpSessionListenner();
//	}
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        org.springframework.boot.web.servlet.MultipartConfigFactory factory = new org.springframework.boot.web.servlet.MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("5120KB");
        return factory.createMultipartConfig();
    }
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BCryptPasswordEncoder  bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(Constants.LENGHT_AUTHENTICATION);
        
    }
    
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Bean
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(environment.getRequiredProperty("disp.db.driver"));
        dataSource.setJdbcUrl(environment.getRequiredProperty("disp.db.url"));
        dataSource.setUsername(environment.getRequiredProperty("disp.db.user"));
        dataSource.setPassword(environment.getRequiredProperty("disp.db.password"));
//        dataSource.setIdleConnectionTestPeriod(Integer.valueOf(environment
//            .getProperty("disp.db.idleConnectionTestPeriodInMinutes")));
//        dataSource.setIdleMaxAge(Long.valueOf(environment.getRequiredProperty("disp.db.idleMaxAgeInMinutes")));
        dataSource.setMaxConnectionsPerPartition(Integer.valueOf(environment
            .getRequiredProperty("disp.db.maxConnectionsPerPartition")));
        dataSource.setMinConnectionsPerPartition(Integer.valueOf(environment
            .getRequiredProperty("disp.db.minConnectionsPerPartition")));
        dataSource.setPartitionCount(Integer.valueOf(environment.getRequiredProperty("disp.db.partitionCount")));
        dataSource.setAcquireIncrement(Integer.valueOf(environment.getRequiredProperty("disp.db.acquireIncrement")));
//        dataSource.setStatementCacheSize(Integer.valueOf(environment.getRequiredProperty("disp.db.statementsCacheSize")));
//        dataSource.setReleaseHelperThreads(Integer.valueOf(environment.getRequiredProperty("disp.db.releaseHelperThreads")));
        return dataSource;
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
