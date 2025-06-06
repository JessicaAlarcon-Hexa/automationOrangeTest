package org.example.Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PIMPage extends BasePage
{
    private WebDriver driver;
    public PIMPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }
    public By btnAddNewEmployee = By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']//parent::button");
    public By boxEmployeeFirstName = By.xpath("//input[@name='firstName']");
    public By boxEmployeeMiddleName = By.xpath("//input[@name='middleName']");
    public By boxEmployeeLastName = By.xpath("//input[@name='lastName']");
    public By boxEmployeeId = By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
    public By btnCreateLoginDetails = By.xpath("//div[@class='oxd-switch-wrapper']//descendant::label");
    public By boxUsername = By.xpath("(//div[@class='oxd-input-group oxd-input-field-bottom-space'])[6]//descendant::input");
    public By boxPassword = By.xpath("(//div[@class='oxd-input-group oxd-input-field-bottom-space'])[9]//descendant::input");
    public By boxConfirmPassword = By.xpath("(//div[@class='oxd-input-group oxd-input-field-bottom-space'])[10]//descendant::input");
    public By btnSaveEmployee = By.xpath("//button[contains(.,'Save')]");

    public WebElement getAddBtn()
    {return driver.findElement(btnAddNewEmployee); }
    public WebElement getFirstNameBox()
    {   return driver.findElement(boxEmployeeFirstName);    }
    public WebElement getMiddleNameBox()
    {   return driver.findElement(boxEmployeeMiddleName);    }
    public WebElement getLastNameBox()
    {   return driver.findElement(boxEmployeeLastName);    }
     public WebElement getEmployeeIdBox()
     {   return driver.findElement(boxEmployeeId);    }
    public WebElement getCreateLoginBtn()
    {   return driver.findElement(btnCreateLoginDetails);    }
    public WebElement getPimUsernameBox()
    {   return driver.findElement(boxUsername);    }
    public WebElement getPimPasswordBox()
    {   return driver.findElement(boxPassword);    }
    public WebElement getPimConfirmPasswordBox()
    {   return driver.findElement(boxConfirmPassword);    }
    public WebElement getBtnSaveNewEmployee()
    { return driver.findElement(btnSaveEmployee); }

    private String idValue;
    private String firstNameValue;
    public void fillFormEmployee(String firstName, String middleName, String lastName, String username, String password, String pass2) throws InterruptedException
    {
        Thread.sleep(4000);
        getAddBtn().click();
        //wait explicito
        WebDriverWait waitForPage= new WebDriverWait(driver, Duration.ofSeconds(10));
       // waitForPage.until(ExpectedConditions.visibilityOfElementLocated(boxEmployeeId));
        Thread.sleep(2000);
        getFirstNameBox().sendKeys(firstName);
        getMiddleNameBox().sendKeys(middleName);
        getLastNameBox().sendKeys(lastName);
        idValue= getEmployeeIdBox().getAttribute("value");
        firstNameValue=getFirstNameBox().getAttribute("value");
        Thread.sleep(2000);
        waitForPage.until(ExpectedConditions.visibilityOfElementLocated(btnCreateLoginDetails));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getCreateLoginBtn());
        Thread.sleep(2000);
        getPimUsernameBox().sendKeys(username);
        getPimPasswordBox().sendKeys(password);
        getPimConfirmPasswordBox().sendKeys(pass2);

       // waitForPage.until(ExpectedConditions.presenceOfElementLocated(boxEmployeeFirstName));
        Thread.sleep(1000);
       // getBtnSaveNewEmployee().click();
        jsExecutor.executeScript("arguments[0].click();", getBtnSaveNewEmployee());

        Thread.sleep(5000);
      //  waitForPage.until(ExpectedConditions.attributeContains(boxEmployeeId, "value", idValue));
     /*
        Así se crea un ExpectedCondition con condiciones que nosotros necesitemos

        waitForPage.until(new ExpectedCondition<Object>() {

            @Override
            public Object apply(WebDriver input) {
                return driver.findElement(btnSaveEmployee).getAttribute("value").contains(idValue);
            }
        });

      */
    }

    public By btnEmployeeList = By.xpath("//a[text()='Employee List']//parent::li");
    public WebElement getPageEmployeeList()
    {return driver.findElement(btnEmployeeList);}

    public By btnSearchEmployee= By.xpath("//button[contains(.,'Search')]");
    public WebElement getBtnSearchId()
    {return driver.findElement(btnSearchEmployee);}
    public void doSearchEmployee() throws InterruptedException
    {
        Thread.sleep(2000);
        getPageEmployeeList().click();
        Thread.sleep(3000);
        getEmployeeIdBox().sendKeys(idValue);
        getBtnSearchId().click();

    }

    public WebElement validateIdSearch()
    {
        String xpathIdValue="//div[contains(text(),'"+idValue+"')]";
        WebElement getTxtIdValue=driver.findElement(By.xpath(xpathIdValue));
        return getTxtIdValue;
    }

    public WebElement validateUserLogin()
    {
       String xpathUserValue="//p[@class='oxd-userdropdown-name' and contains(text(), '"+firstNameValue+"')]";
        WebElement getTxtUserValue= driver.findElement(By.xpath(xpathUserValue));
        return getTxtUserValue;

    }


}
