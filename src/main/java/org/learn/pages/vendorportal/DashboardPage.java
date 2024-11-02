package org.learn.pages.vendorportal;

import org.learn.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id = "monthly-earning")
    private WebElement monthlyIncomeText;

    @FindBy(id = "annual-earning")
    private WebElement annualIncomeText;

    @FindBy(id = "profit-margin")
    private WebElement profitMarginText;

    @FindBy(id = "available-inventory")
    private WebElement availableInventoryText;

    @FindBy(css = "#userDropdown")
    private WebElement profileClick;

    @FindBy(css = "[type='search']")
    private WebElement searchInput;

    @FindBy(css = "[data-target='#logoutModal']")
    private WebElement logoutLink;

    @FindBy(css = "#dataTable_info")
    private WebElement searchResultText;

    @FindBy(css="a.btn-primary ")
    private WebElement logoutButton;

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public String getMonthlyEarning(){
        return this.monthlyIncomeText.getText();
    }

    public String getAnnualEarning(){
        return this.annualIncomeText.getText();
    }

    public String getProfitMargin(){
        return this.profitMarginText.getText();
    }

    public String getAvailableInventory(){
        return this.availableInventoryText.getText();
    }

    public void searchInput(String searchText){
        this.searchInput.sendKeys(searchText);
    }

    public int getSearchResultCount(){
        String[] count = this.searchResultText.getText().split(" ");
        log.info("search results found to be {}", count[5]);
        return Integer.parseInt(count[5]);
    }

    public void userLogout(){
        this.profileClick.click();
        this.logoutLink.click();
        wait.until(ExpectedConditions.visibilityOf(this.logoutButton));
        this.logoutButton.click();
    }



    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(this.monthlyIncomeText));
        return monthlyIncomeText.isDisplayed();
    }
}
