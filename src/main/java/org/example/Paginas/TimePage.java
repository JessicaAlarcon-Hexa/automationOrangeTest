package org.example.Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimePage extends BasePage
{
    private WebDriver driver;
    public TimePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }

    public By calendar= By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']");
    public By lblDate=  By.xpath("(//div[@class='oxd-calendar-date' and contains(.,'2')])[1]");
    public By btnCreateTimesheet= By.xpath("//button[contains(.,'Create')]");
    public By btnEditTimesheet= By.xpath("//button[text()=' Edit ']");
    public By projectBox= By.xpath("//input[contains(@placeholder,'Type for')]");
    public By lblProject= By.xpath("(//div[@role='listbox']//descendant::div)[1]");
    public By activitySelect= By.xpath("//div[@class='oxd-select-wrapper']");
    public By lblActivity = By.xpath("(//div[@role='option'])[6]");
    public By lblEmployee = By.xpath("(//div[@role='option'])[1]");
    private int inputHoras;
    public By btnSaveTimesheet= By.xpath("//button[text()=' Save ']");
    public  By btnSubmit = By.xpath("//button[text()=' Submit ']");
    public By btnViewEmployee= By.xpath("//form//button[text()=' View ']");
    public By btnApproveTS= By.xpath("//form//button[text()=' Approve ']");
    public By txtApprovedComment= By.xpath("");


    public WebElement getCalendar()
    {
        return driver.findElement(calendar);
    }
    public WebElement getDate(){return driver.findElement(lblDate);}
    public WebElement getBtnCreateTimesheet(){return driver.findElement(btnCreateTimesheet);}
    public WebElement getBtnEditTimesheet(){return driver.findElement(btnEditTimesheet);}
    public WebElement getProjectBox(){return driver.findElement(projectBox);}
    public WebElement getProjectName(){return driver.findElement(lblProject);}
    public WebElement getActSelect(){return driver.findElement(activitySelect);}
    public WebElement getActivity(){return driver.findElement(lblActivity);}
    public WebElement getBtnSaveTimesheet(){return driver.findElement(btnSaveTimesheet);}
    public WebElement getSubmitButton(){return driver.findElement(btnSubmit);}
    public WebElement getEmployeeName(){return driver.findElement(lblEmployee);}
    public WebElement getBtnView(){return driver.findElement(btnViewEmployee);}
    public WebElement getBtnApprove(){return driver.findElement(btnApproveTS);}



    public void selectDate() throws InterruptedException
    {
        getCalendar().click();
        Thread.sleep(2000);
        getDate().click();
    }
    public void createTimesheet() throws InterruptedException
    {
        Thread.sleep(2000);
        getBtnCreateTimesheet().click();
    }

    public void editTimesheet() throws InterruptedException
    {
        Thread.sleep(2000);
        getBtnEditTimesheet().click();
    }

    public void fillTimesheet(String project, String weekDays, String weekends) throws InterruptedException
    {
        //Select actSelect= new Select(getActSelect());
        Thread.sleep(1000);
        getProjectBox().sendKeys(project);
        Thread.sleep(2000);
        getProjectName().click();
        Thread.sleep(1000);
        getActSelect().click();
        Thread.sleep(2000);
        getActivity().click();
        Thread.sleep(1000);
        for (inputHoras=2; inputHoras<9; )
        {
            if(inputHoras >= 3)
            {
                WebElement getHoursBox= driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])["+(inputHoras-1)+"]"));
                if (inputHoras > 6)
                {
                    getHoursBox.sendKeys(weekends);
                }
                else getHoursBox.sendKeys(weekDays);
            }
            else
            {
                WebElement getHoursBox= driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])["+inputHoras+"]"));
                getHoursBox.sendKeys(weekDays);
            }
            inputHoras++;
        }

    }

    public void saveTimesheet() throws InterruptedException
    {
        Thread.sleep(2000);
        getBtnSaveTimesheet().click();
    }

    public WebElement validateStatus(String state)
    {
       String xpathStatus= "//p[contains(.,'"+ state +"')]";
       WebElement getTxtStatus= driver.findElement(By.xpath(xpathStatus));
       return getTxtStatus;
    }

    public void submitSheet() throws InterruptedException
    {
        Thread.sleep(3000);
        getSubmitButton().click();
    }

    public void getEmployee(String employee) throws InterruptedException
    {
        Thread.sleep(2000);
        getProjectBox().sendKeys(employee);
        Thread.sleep(2000);
        getEmployeeName().click();
        Thread.sleep(2000);
        getBtnView().click();
    }

    public WebElement validateHours(String hours)
    {
        String xpathHours= "//tr[last()]//td[last() and contains(text(),'"+hours+"')]";
        WebElement getTotalHours= driver.findElement(By.xpath(xpathHours));
        return getTotalHours;
    }

    public void approveTimesheet() throws InterruptedException
    {
        Thread.sleep(2000);
        //Agregar comentario Approved

        Thread.sleep(2000);
        getBtnApprove().click();
        Thread.sleep(2000);
    }
}
