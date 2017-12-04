package providesupport.sheduller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import providesupport.ScheduledFutureTask;
import providesupport.model.WebsiteData;
import providesupport.service.WebsiteDataService;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduleManager {

    @Autowired
    TaskScheduler taskScheduler;

    @Autowired
    WebsiteDataService websiteDataService;

    private LinkedBlockingQueue<ScheduledFutureTask> futureTaskLinkedBlockingQueue = new LinkedBlockingQueue<>();

    private static ScheduleManager scheduleManagerInstance;

    private ScheduleManager() {
        scheduleManagerInstance = this;
    }

    public static ScheduleManager getInstance() {
        return scheduleManagerInstance;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onProjectStart() {


        List<WebsiteData> websiteDataArrayList = websiteDataService.websiteDataList();
        if (websiteDataArrayList != null) {
            for (WebsiteData data : websiteDataArrayList) {


                startNewTask(data);


            }
        } else {
            System.out.println(" No content");
        }
    }


    public void startNewTask(WebsiteData data) {

        ScheduledFuture scheduledFuture = taskScheduler.scheduleAtFixedRate(new TaskRunnable(data.getId(), data.getUrl(), data.getResponseOK()
                , data.getResponseWARNING(), data.getResponseCRITICAL(),
                data.getByteMin(), data.getByteMax(), data.getStatus(), data.getwaitingResponseCode(), websiteDataService), 60000);

        futureTaskLinkedBlockingQueue.add(new ScheduledFutureTask(scheduledFuture, data.getId()));
    }

    public void cancelTask(Long id) {

        for (ScheduledFutureTask scheduledFutureTask : futureTaskLinkedBlockingQueue) {
            if (scheduledFutureTask.getWebsiteDataId().equals(id)) scheduledFutureTask.stopTask();
        }

    }


}
