package org.example.Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage extends BasePage
{
    private WebDriver driver;
    public By btnPIM= By.xpath("//span[.='PIM']");

    //lista de usuario donde muestra opciones (aqui se encuentra el logout)
    public By userOptionsList = By.xpath("//div[@class='oxd-topbar-header-userarea']//descendant::span");
    public WebElement userDropdown()
    {
        return driver.findElement(userOptionsList);
    }

    //label de Logout dentro de la lista
    public By lblLogOut =By.xpath("//ul[@class='oxd-dropdown-menu']//descendant::a [contains(.,'Logout')]");
    public WebElement btnLogout(){return driver.findElement(lblLogOut);}

    public By btnTime= By.xpath("//a[.='Time']");

    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }
    public WebElement pimBtn()
    {
        return driver.findElement(btnPIM);
    }
    public WebElement timeBtn(){return driver.findElement(btnTime);}

    public void getPIM() throws InterruptedException
    {
        Thread.sleep(2000);
        pimBtn().click();
        Thread.sleep(2000);
    }

    public void doLogOut() throws InterruptedException
    {
        Thread.sleep(2000);
        userDropdown().click();
        btnLogout().click();
        Thread.sleep(2000);
    }

    public void getTime() throws InterruptedException
    {
        Thread.sleep(2000);
        timeBtn().click();
        Thread.sleep(2000);

    }

}
