package cz.utb.duck.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class YahooDataService {

    public void downloadHistoricalData() {
        String symbol = "AAPL"; // Symbol of the stock
        Calendar from = Calendar.getInstance();
        from.add(Calendar.MONTH, -3); // 3 months ago
        Calendar to = Calendar.getInstance();

        try {
            List<HistoricalQuote> quotes = YahooFinance.get(symbol).getHistory(from, to);
            if (quotes != null && !quotes.isEmpty()) {
                writeToCSV(quotes, symbol + "_historical_data.csv");
            }
        } catch (IOException e) {
            log.error("Error happened while getting yahoo data [{}] ", e.getMessage());
        }

    }

    private void writeToCSV(List<HistoricalQuote> quotes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.append("Date,Open,High,Low,Close,Adj Close,Volume\n");

            // Write data rows
            for (HistoricalQuote quote : quotes) {
                writer.append(String.valueOf(quote.getDate().getTimeInMillis())).append(",");
                writer.append(String.valueOf(quote.getOpen())).append(",");
                writer.append(String.valueOf(quote.getHigh())).append(",");
                writer.append(String.valueOf(quote.getLow())).append(",");
                writer.append(String.valueOf(quote.getClose())).append(",");
                writer.append(String.valueOf(quote.getAdjClose())).append(",");
                writer.append(String.valueOf(quote.getVolume())).append("\n");
            }
        } catch (IOException e) {
            log.error("Error happened while writing in csv [{}] ", e.getMessage());
        }
    }
}
