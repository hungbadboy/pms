package com.tvo.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfiguration {

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		AwarechedulerBeanFactory jobFactory = new AwarechedulerBeanFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public Scheduler  schedulerFactoryBean(
			DataSource dataSource,
			@Qualifier("jobTrigger") Trigger  jobTrigger,
			@Qualifier("cronJobTrigger") Trigger  cronJobTrigger,
			JobFactory jobFactory) throws Exception {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		// this allows to update triggers in DB when updating settings in config
		// file:
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		factory.setJobFactory(jobFactory);
		factory.setTriggers(jobTrigger, cronJobTrigger);
		factory.setWaitForJobsToCompleteOnShutdown(true);
		factory.setQuartzProperties(quartzProperties());
		factory.afterPropertiesSet();
		Scheduler scheduler = factory.getScheduler();
		scheduler.setJobFactory(jobFactory);
		scheduler.scheduleJob(
				(JobDetail) jobTrigger.getJobDataMap().get("jobDetail"),
				jobTrigger);

		scheduler.start();
		return scheduler;
	}

	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource(
				"/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		return createJobDetail(SampleJob.class);
	}

	@Bean(name = "jobTrigger")
	public SimpleTriggerFactoryBean jobTrigger(
			@Qualifier("jobDetail") JobDetail jobDetail,
			@Value("${jobTrigger.frequency}") long frequency) {
		return createTrigger(jobDetail, frequency);
	}

	@Bean(name = "cronJobTrigger")
	public CronTriggerFactoryBean cronJobTrigger(
			@Qualifier("jobDetail") JobDetail jobDetail,
			@Value("${cronJobTrigger.frequency}") String frequency) {
		
		return createCronTrigger(jobDetail, frequency);
	}

	private static JobDetailFactoryBean createJobDetail(Class jobClass) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		// job has to be durable to be stored in DB:
		factoryBean.setDurability(true);
		return factoryBean;
	}

	private static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail,
			long pollFrequencyMs) {
		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setJobDataMap((JobDataMap) new JobDataMap().put("jobDetail", jobDetail));
		factoryBean.setStartDelay(0L);
		factoryBean.setRepeatInterval(pollFrequencyMs);
		factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		// in case of misfire, ignore all missed triggers and continue :
		factoryBean
				.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
		return factoryBean;
	}

	//
	// // Use this method for creating cron triggers instead of simple triggers:
	private static CronTriggerFactoryBean createCronTrigger(
			JobDetail jobDetail, String cronExpression) {
		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setCronExpression(cronExpression);
		factoryBean
				.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		return factoryBean;
	}
}
