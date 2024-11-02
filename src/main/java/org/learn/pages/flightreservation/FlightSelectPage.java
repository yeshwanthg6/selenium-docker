package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectPage extends AbstractPage {

    public FlightSelectPage(WebDriver driver){
        super(driver);
    }

    @FindBy(name = "departure-flight")
    private List<WebElement> departureRadioButton;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalRadioButton;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightButton;

    public void selectFlights()
    {
        int random = ThreadLocalRandom.current().nextInt(0, this.departureRadioButton.size());
        this.departureRadioButton.get(random).click();
        this.arrivalRadioButton.get(random).click();
        this.confirmFlightButton.click();

    }

    public boolean isAt(){
        wait.until(ExpectedConditions.visibilityOf(this.confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }
}
