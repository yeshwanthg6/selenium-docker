package org.learn.tests.flightreservation;

import org.learn.pages.flightreservation.*;
import org.learn.pages.flightreservation.model.FlightReservationTestData;
import org.learn.tests.AbstractTest;
import org.learn.utils.Constants;
import org.learn.utils.JsonReader;
import org.learn.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setUpParameters(String testDataPath){
//        PropertiesReader.initialize();
        testData = JsonReader.getJsonData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(PropertiesReader.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.fillUserDetails(testData.firstName(),testData.lastName());
        registrationPage.fillUserCredential(testData.email(),testData.password());
        registrationPage.fillUserAddress(testData.street(),testData.city(),testData.zip());
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void userConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.clickFlightSearchButton();
    }

    @Test(dependsOnMethods = "userConfirmationTest")
    public void flightSearchPage(){
        FlightSearchPage flightSearchPage =new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassenger(testData.noOfPassengers());
    }

    @Test(dependsOnMethods = "flightSearchPage")
    public void flightSelectPage(){
        FlightSelectPage flightSelectPage = new FlightSelectPage(driver);
        Assert.assertTrue(flightSelectPage.isAt());
        flightSelectPage.selectFlights();
    }

    @Test(dependsOnMethods = "flightSelectPage")
    public void flightConfirmationPage(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getTotalPrice(),testData.totalExpectedPrice());
    }


}
