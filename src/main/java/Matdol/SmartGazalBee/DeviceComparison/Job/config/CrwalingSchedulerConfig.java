package Matdol.SmartGazalBee.DeviceComparison.Job.config;

import Matdol.SmartGazalBee.DeviceComparison.Job.CrwalingJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrwalingSchedulerConfig {
    @Bean
    public JobDetail crwalJobDetail(){
        return JobBuilder.newJob(CrwalingJob.class)
                .withIdentity("CrwalingJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger crwalTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("CrwalingJob")
                .startNow() // 애플리케이션 시작 시 즉시 작업 실행
                .withSchedule(createCronSchedule())
                .build();
    }

    public CronScheduleBuilder createCronSchedule() {

        return CronScheduleBuilder.cronSchedule("0 0 6 1 * ?");
    }
}
