package org.example.Paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverHandler
{
    private WebDriver driver;
    public DriverHandler()
    {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    public void quit()
    {
        this.driver.quit();
    }

}
