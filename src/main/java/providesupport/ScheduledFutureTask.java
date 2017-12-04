package providesupport;

import java.util.concurrent.ScheduledFuture;

public class ScheduledFutureTask {

    private final ScheduledFuture scheduledFuture;
    private final Long websiteDataId;

    public ScheduledFutureTask(ScheduledFuture scheduledFuture, Long websiteDataId) {
        this.scheduledFuture = scheduledFuture;
        this.websiteDataId = websiteDataId;
    }


    public Long getWebsiteDataId() {
        return websiteDataId;
    }

    public void stopTask(){
        scheduledFuture.cancel(true);
    }
}

