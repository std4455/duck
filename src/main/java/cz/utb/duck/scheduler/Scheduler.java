package cz.utb.duck.scheduler;

import cz.utb.duck.service.DataService;
import cz.utb.duck.service.YahooDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    @Autowired
    private final YahooDataService yahooDataService;

    @Autowired
    private final DataService dataService;

    protected boolean isRunning = false;

    @Scheduled(fixedDelayString = "30000", initialDelayString = "4000")
    public void downloadStockData() {
        if (isRunning)
            log.warn("already running ");
        else {
            try {
                isRunning = true;
                //yahooDataService.downloadHistoricalData();
                dataService.getDate();
            } catch (Exception e) {
                log.error("sth went wrong while calling the yahoo service [{}]", e.getMessage());
            } finally {
                isRunning = false;
            }
        }
    }
}
