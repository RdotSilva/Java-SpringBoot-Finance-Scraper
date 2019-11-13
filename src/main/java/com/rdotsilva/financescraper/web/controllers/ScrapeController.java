package com.rdotsilva.financescraper.web.controllers;

import com.rdotsilva.financescraper.web.models.Stock;
import com.rdotsilva.financescraper.web.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class ScrapeController {
    @Autowired
    private StockRepository stockRepository;

    @RequestMapping(value = "/scrape", method = RequestMethod.GET)
    public String scrape() throws SQLException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String loginUrl = "https://login.yahoo.com/config/login?.src=fpctx&.intl=us&.lang=en-US&.done=https%3A%2F%2Fwww.yahoo.com";

        driver.get(loginUrl);
        System.out.println("Navigated to login URL");

        driver.findElement(By.id("login-username")).sendKeys("ryansilva.student@careerdevs.com" + Keys.RETURN);
        System.out.println("Username entered");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.id("login-passwd")).sendKeys("%x_2*xC98H;" + Keys.RETURN);
        System.out.println("Login Success");

        String portfolioUrl = "https://finance.yahoo.com/portfolio/p_0/view/v1";

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.get(portfolioUrl);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement stockTable = driver.findElement(By.tagName("tbody"));
        System.out.println("Stock table found");

        List<WebElement> stockRows = stockTable.findElements(By.className("simpTblRow"));
        System.out.println("Stock Rows Found: " + stockRows.size());

        ArrayList<Stock> stockList = new ArrayList<Stock>();

        for (WebElement row : stockRows
        ) {

            String[] eachStock = row.getText().split(" ");

            String[] splitSymbol = eachStock[0].split("\\r?\\n");

            String[] splitMarketCap = eachStock[9].split("\\r?\\n");

            java.util.Date scrapeDate = new java.util.Date();

            // Convert timestamp to sql format.
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(scrapeDate.getTime());

            String symbol = splitSymbol[0];
            String lastPrice = splitSymbol[1];
            String changeAmount = eachStock[1];
            String changePercent = eachStock[2];
            String volume = eachStock[6];
            String averageVolume = eachStock[8];
            String marketCap = splitMarketCap[0];

            // JPA insertion
            Stock stock = new Stock();
            stock.setScrapeDate(sqlDate);
            stock.setSymbol(symbol);
            stock.setLastPrice(lastPrice);
            stock.setChangeAmount(changeAmount);
            stock.setChangePercent(changePercent);
            stock.setVolume(volume);
            stock.setAverageVolume(averageVolume);
            stock.setMarketCap(marketCap);

            stockRepository.save(stock);
        }
        driver.close();
        return "redirect:latest";
    }

//    @RequestMapping("/scrape")
//    public String scraper() {
//        return "scrape";
//    }
}
