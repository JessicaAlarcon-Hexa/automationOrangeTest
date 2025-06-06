package org.example;

import org.example.Paginas.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AppTest
{

    @Test
    public void TestOrange() throws InterruptedException {
        DriverHandler driverHandler=new DriverHandler();
        WebDriver driver= driverHandler.getDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String user="Admin";
        String pass="admin123";
        BasePage basePage=new BasePage(driver);
        basePage.doLogIn(user,pass);

        //Ingresar a PIM
        HomePage homePage=new HomePage(driver);
        homePage.getPIM();

        //Crear nuevo usuario con opciones de Login
        PIMPage pimPage= new PIMPage(driver);
        String firstName="Talya";
        String middleName= "";
        String lastName="Rivera";
      //  String employeeId=pimPage.getEmployeeIdBox().getText();
        Double numAl= Math.random();
        String newUsername="TalyaR"+numAl;
        String newPassword="talya123";
        pimPage.fillFormEmployee(firstName,middleName,lastName,newUsername,newPassword, newPassword);
       // WebElement userAssert= pimPage.getPimConfirmPasswordBox();
        //Assert.assertNotNull(userAssert.getText());

        //Buscar usuario
       pimPage.doSearchEmployee();

        //Validar que existe el usuario
        Thread.sleep(1000);
        WebElement idAssert=pimPage.validateIdSearch();
        Assert.assertNotNull(idAssert.getText());
        Assert.assertFalse(idAssert.getText().isEmpty());
        System.out.println("El usuario con id: "+idAssert.getText()+" existe.");

        //Hacer LogOut
        homePage.doLogOut();

        //Login con el nuevo usuario
        basePage.doLogIn(newUsername,newPassword);

        //Validar que se hizo login con nuevo usuario
        Thread.sleep(3000);
        WebElement userAssert=pimPage.validateUserLogin();
        Assert.assertNotNull(userAssert.getText());
        Assert.assertFalse(userAssert.getText().isEmpty());
        System.out.println("Bienvenido(a): "+userAssert.getText());

        driverHandler.quit();

    }

}