package Matdol.SmartGazalBee.DeviceComparison.Job;

import Matdol.SmartGazalBee.DeviceComparison.Service.CrwalService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@RequiredArgsConstructor
public class CrwalingJob implements Job {
    private final CrwalService crwalService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        crwalService.crwaling();

    }
}
