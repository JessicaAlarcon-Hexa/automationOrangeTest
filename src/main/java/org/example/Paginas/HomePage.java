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

    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }
    public WebElement pimBtn()
    {
        return driver.findElement(btnPIM);
    }
    public void getPIM() throws InterruptedException {
        Thread.sleep(4000);
        pimBtn().click();
    }

    //lista de usuario donde muestra opciones (aqui se encuentra el logout)
    public By userOptionsList = By.xpath("//div[@class='oxd-topbar-header-userarea']//descendant::span");
    public WebElement userDropdown()
    {
       return driver.findElement(userOptionsList);
    }

    //label de Logout dentro de la lista
    public By lblLogOut =By.xpath("//ul[@class='oxd-dropdown-menu']//descendant::a [contains(.,'Logout')]");
    public WebElement btnLogout(){return driver.findElement(lblLogOut);}


    public void doLogOut() throws InterruptedException {
        Thread.sleep(2000);
        userDropdown().click();
        btnLogout().click();

    }

}
