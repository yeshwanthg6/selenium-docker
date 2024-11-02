package org.learn.pages.flightreservation;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "passengers")
    private WebElement passengerDropdown;

    @FindBy(id = "search-flights")
    private WebElement searchFlightButton;

    public boolean isAt(){
        wait.until(ExpectedConditions.visibilityOf(this.passengerDropdown));
        return passengerDropdown.isDisplayed();
    }

    public void selectPassenger(String noOfPassengers){
        Select select=new Select(this.passengerDropdown);
        select.selectByValue(noOfPassengers);
        this.searchFlightButton.click();
    }



}
