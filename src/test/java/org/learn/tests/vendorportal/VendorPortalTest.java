package org.learn.tests.vendorportal;
import org.learn.pages.vendorportal.DashboardPage;
import org.learn.pages.vendorportal.LoginPage;
import org.learn.pages.vendorportal.model.VendorPortalTestData;
import org.learn.tests.AbstractTest;
import org.learn.utils.Constants;
import org.learn.utils.JsonReader;
import org.learn.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void initiateObjects(String testDataPath){
        this.testData = JsonReader.getJsonData(testDataPath, VendorPortalTestData.class);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
//        PropertiesReader.initialize();
    }

    @Test
    public void userLoginTest()
    {
        loginPage.goTo(PropertiesReader.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.userLogsIn(testData.username(),testData.password());
    }

    @Test(dependsOnMethods = "userLoginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());
        dashboardPage.searchInput(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(),testData.searchResultCount());
        dashboardPage.userLogout();
    }

}
