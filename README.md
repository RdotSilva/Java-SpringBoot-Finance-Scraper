# Yahoo Finance Web Scraper

Full stack web scraper that uses Selenium to log into the Yahoo Finance website, pull stock data and save data to a MySQL database.

Fully secure user registration and login is included with password hashing and authentication.

Registered users have access to scrape data history and can initiate a new scrape.

Live Demo: [Heroku Demo](https://yahoo-finance-scraper.herokuapp.com/)

### Prerequisites

You must create an application.properties file with your database/hibernate configuration options.

1. Create a new file: \src\main\resources\application.properties

```
# Database configurations
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD

# Hibernate configurations
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialet=YOUR_DIALECT
```

## Installation

1. Install Maven dependencies.

```
mvn install
```

## Running the server

1. Start SpringBoot server.

```
mvn spring-boot:run
```

## Changing web driver

If you would like use a different web driver configure these options in the ScrapeController.java file. Chrome is the default web driver being used.

1. Chrome Driver

```
String driverType = "webdriver.chrome.driver";
String driverLocation = Paths.get(System.getProperty("user.dir")).toRealPath() + "\\src\\main\\resources\\drivers\\chromedriver.exe";
```

```
ChromeOptions chromeOptions = new ChromeOptions();
chromeOptions.setHeadless(true);
WebDriver driver = new ChromeDriver(chromeOptions);
```

2. Gecko Driver

```
String driverType = "webdriver.gecko.driver";
String driverLocation = Paths.get(System.getProperty("user.dir")).toRealPath() + "\\src\\main\\resources\\drivers\\geckodriver.exe";
```

```
FirefoxOptions firefoxOptions = new FirefoxOptions();
firefoxOptions.setHeadless(true);
WebDriver driver = new ChromeDriver(firefoxOptions);
```

## Built With

- Java
- Maven
- Spring Boot
- Thymeleaf
- Hibernate
- Spring Security
- Selenium
- MySQL
- IntelliJ IDEA


## Screenshots

![History](https://i.imgur.com/0NNHFIC.png "History")
![Login](https://i.imgur.com/WCA0n8V.png "Login")
![Latest](https://i.imgur.com/SxJepsz.png "Latest")

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
