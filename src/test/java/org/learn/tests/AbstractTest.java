package org.learn.tests;

import org.learn.listener.TestListener;
import org.learn.utils.Constants;
import org.learn.utils.PropertiesReader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Listeners({TestListener.class})
public class AbstractTest {
    protected WebDriver driver;
    private Capabilities cap;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void initProperties(){
        PropertiesReader.initialize();
    }

    @BeforeTest
    public void setUpDriver(ITestContext context) throws MalformedURLException {
        this.driver = Boolean.getBoolean(Constants.SELENIUM_GRID_ENABLED)? remoteDriver() : localDriver();
        context.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver remoteDriver() throws MalformedURLException {
        cap = new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(PropertiesReader.get(Constants.BROWSER))){
            cap = new FirefoxOptions();
        }
        String urlFormat= PropertiesReader.get(Constants.SELENIUM_GRID_URL);
        String urlHost=PropertiesReader.get(Constants.SELENIUM_GRID_HOST);
        String hubUrl = String.format(urlFormat, urlHost);
        log.info("Remote Web driver Url found to be: {}", hubUrl);
        return new RemoteWebDriver(new URL(hubUrl), cap);
    }

    private WebDriver localDriver(){
        if (Constants.FIREFOX.equalsIgnoreCase(PropertiesReader.get(Constants.BROWSER))) {
            // Initialize Firefox with preferences if Firefox is specified
            FirefoxOptions options = new FirefoxOptions();
            return new FirefoxDriver(options);
        }
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
            put("credentials_enable_service", false); // Disable password manager
            put("profile.password_manager_enabled", false); // Disable password prompt
            put("autofill.profile_enabled", false); // Disable address autofill
            put("autofill.credit_card_enabled", false); // Disable credit card autofill
        }});
        return new ChromeDriver();
    }

    @AfterTest
    public void tearDownDriver(){
        this.driver.quit();
    }
}
