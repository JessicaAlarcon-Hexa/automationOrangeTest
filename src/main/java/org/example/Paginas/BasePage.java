package org.example.Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage
{
     private WebDriver driver;
     public By usernameBox= By.name("username");
     public By passwordBox= By.name("password");
     public By btnLogIn= By.xpath("//button[@type='submit']");

     public BasePage(WebDriver driver)
     {
         this.driver=driver;
     }

     public WebElement getUsernameBox()
     {
         return driver.findElement(usernameBox);
     }

     public WebElement getPasswordBox()
     {
         return driver.findElement(passwordBox);
     }

     public WebElement getBtnLogin()
     {
         return driver.findElement(btnLogIn);
     }


     public void doLogIn(String user, String pass) throws InterruptedException {
        /* WebDriverWait waitForPage= new WebDriverWait(driver, Duration.ofSeconds(10));
         waitForPage.until(ExpectedConditions.visibilityOfElementLocated(btnLogIn));
         */
         Thread.sleep(1000);
         getUsernameBox().sendKeys(user);
         getPasswordBox().sendKeys(pass);
         Thread.sleep(3000);

         getBtnLogin().click();
         Thread.sleep(3000);
     }



}
