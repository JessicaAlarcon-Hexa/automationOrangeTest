package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.Paginas.*;
import org.example.Reports.ExtentReportsClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppTest
{
    ExtentReports extentReport;
    ExtentSparkReporter sparkReporter;
    @BeforeTest
    public void setUp()
    {
        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("C:\\Users\\1000062343\\Documents\\selenium\\TestReport.html");
        extentReport.attachReporter(sparkReporter);
    }

    @Test
    public void TestOrange() throws InterruptedException
    {
        ExtentTest test1= extentReport.createTest("T-001 Crear nuevo usuario","Validar que se cree un nuevo usuario con credenciales "
                + "de inicio de sesión y que se pueda ingresar a la página con dichas credenciales");

        DriverHandler driverHandler=new DriverHandler();
        WebDriver driver= driverHandler.getDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        //Report inicio de prueba
        test1.info("Se inició la prueba");
        String user="Admin";
        String pass="admin123";
        BasePage basePage=new BasePage(driver);
        basePage.doLogIn(user,pass);
        //Report inicio de sesión de admin
        test1.pass("Inicio de sesión de Admin exitoso");
        //Ingresar a PIM
        HomePage homePage=new HomePage(driver);
        homePage.getPIM();

        //Report ingreso a PIM
        test1.pass("Ingreso a pantalla PIM");
        //Crear nuevo usuario con opciones de Login
        PIMPage pimPage= new PIMPage(driver);
        String firstName="Talya";
        String middleName= "";
        String lastName="Rivera";
        Double numAl= Math.random();
        String newUsername="TalyaR"+numAl;
        String newPassword="talya123";
        pimPage.fillFormEmployee(firstName,middleName,lastName,newUsername,newPassword, newPassword);
        //Report creación de nuevo usuario
        test1.pass("Se creó exitosamente el usuario "+newUsername);
        //Buscar usuario
       pimPage.doSearchEmployee();

        //Validar que existe el usuario
        Thread.sleep(1000);
        WebElement idAssert=pimPage.validateIdSearch();
        Assert.assertNotNull(idAssert.getText());
        Assert.assertFalse(idAssert.getText().isEmpty());
        System.out.println("El usuario con id: "+idAssert.getText()+" existe.");
        test1.pass("Existe el usuario con ID: "+idAssert.getText());
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
        test1.pass("El usuario "+newUsername+" ingresó correctamente");

        driverHandler.quit();

        test1.info("Finalizó la prueba de manera exitosa");
    }
    @AfterTest
    public void afterTest() throws IOException {
        extentReport.flush();
        //Abrir el reporte automáticamente
        Desktop.getDesktop().browse((new File("C:\\Users\\1000062343\\Documents\\selenium\\TestReport.html").toURI()));
    }

}