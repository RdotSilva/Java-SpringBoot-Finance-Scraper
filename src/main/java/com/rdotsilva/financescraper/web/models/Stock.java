package com.rdotsilva.financescraper.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scrapeId;

    private Timestamp scrapeDate;
    private String symbol;
    private String lastPrice;
    private String changeAmount;
    private String changePercent;
    private String volume;
    private String averageVolume;
    private String marketCap;

    public int getScrapeId() {
        return scrapeId;
    }

    public void setScrapeId(int scrapeId) {
        this.scrapeId = scrapeId;
    }

    public Timestamp getScrapeDate() {
        return scrapeDate;
    }

    public void setScrapeDate(Timestamp scrapeDate) {
        this.scrapeDate = scrapeDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(String changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getAverageVolume() {
        return averageVolume;
    }

    public void setAverageVolume(String averageVolume) {
        this.averageVolume = averageVolume;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }
}
