package com.rdotsilva.financescraper.web.controllers;

import com.rdotsilva.financescraper.web.models.Stock;
import com.rdotsilva.financescraper.web.repositories.StockRepository;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class ScrapeController {
    @Autowired
    private StockRepository stockRepository;

    public static void Login(WebDriver driver) {
        String loginUrl = "https://login.yahoo.com/config/login?.src=fpctx&.intl=us&.lang=en-US&.done=https%3A%2F%2Fwww.yahoo.com";
        String email = "ryansilva.student@careerdevs.com";
        String password = "%x_2*xC98H;";

        String loginId = "login-username";
        String passwordId = "login-passwd";

        driver.get(loginUrl);

        driver.findElement(By.id(loginId)).sendKeys(email + Keys.RETURN);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id(passwordId)).sendKeys(password + Keys.RETURN);
    }

    public static List<WebElement> NavigateToStockData(WebDriver driver) {
        String portfolioUrl = "https://finance.yahoo.com/portfolio/p_0/view/v1";

        String stockTableTag = "tbody";
        String stockRowsClass = "simpTblRow";

        driver.get(portfolioUrl);

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement stockTable = driver.findElement(By.tagName(stockTableTag));
        List<WebElement> stockRows = stockTable.findElements(By.className(stockRowsClass));

        return stockRows;
    }

    public void InsertStocksIntoDatabase(List<WebElement> stockRows) {
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
    }

    @RequestMapping(value = "/scrape", method = RequestMethod.GET)
    public String scrape() throws SQLException, IOException {
        // Options for local driver. Uncomment when running locally.
//        String driverType = "webdriver.chrome.driver";
//        String driverLocation = Paths.get(System.getProperty("user.dir")).toRealPath() + "\\src\\main\\resources\\drivers\\chromedriver.exe";
//        System.setProperty(driverType, driverLocation);
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setHeadless(true);

        // Heroku driver options. Use these when deploying to Heroku.
        ChromeOptions chromeOptions = new ChromeOptions();
        String binaryPath = "/app/.apt/usr/bin/google-chrome";
        System.out.println("Path: " + binaryPath);
        chromeOptions.setBinary(binaryPath);

        WebDriver driver = new ChromeDriver(chromeOptions);

        Login(driver);

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> stockRows = NavigateToStockData(driver);

        InsertStocksIntoDatabase(stockRows);

        driver.close();
        return "redirect:latest";
    }
}
