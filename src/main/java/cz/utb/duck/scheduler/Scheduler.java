package cz.utb.duck.scheduler;

import cz.utb.duck.service.YahooDataService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Scheduler {
    @Autowired
    private YahooDataService yahooDataService;

/*
    public Scheduler(YahooDataService yahooDataService) {
        this.yahooDataService = yahooDataService;
    }
*/

    @Scheduled(cron = "0 0 0 * * MON-FRI") // Run every day at midnight
    public void downloadStockData() {
        yahooDataService.downloadHistoricalData();
    }
}
