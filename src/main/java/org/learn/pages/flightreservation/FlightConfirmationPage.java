package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlightConfirmationPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    public FlightConfirmationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = ".card-body div:nth-child(3) p")
    private WebElement totalPriceText;

    @FindBy(css = ".card-body div:nth-child(1) p")
    private WebElement flightConfirmationId;

    public String getTotalPrice(){
        log.info("Total Price : {}",this.totalPriceText.getText());
        log.info("Flight Confirmation id : #{}",this.flightConfirmationId.getText());
        return this.totalPriceText.getText();
    }

    public boolean isAt(){
        wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationId));
        return this.flightConfirmationId.isDisplayed();
    }
}
