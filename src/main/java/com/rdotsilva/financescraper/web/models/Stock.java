package com.rdotsilva.financescraper.web.models;

import java.util.Date;

public class Stock {
    private int scrapeId;
    private Date scrapeDate;
    private String symbol;
    private String lastPrice;
    private String changeAmount;
    private String changePercent;
    private String volume;
    private String averageVolume;
    private String marketCap;
}
