package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.example.Paginas.*;
import org.example.Reports.ExtentReportsClass;
import org.example.Reports.ScreenshotsClass;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

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
        extentReport.setSystemInfo("Tester","Jessica Alarcón");
    }

    @Test
    public void TestOrange() throws InterruptedException, IOException {
        ExtentTest test1= extentReport.createTest("T-001 Crear nuevo usuario","Validar que se cree un nuevo usuario con credenciales "
                + "de inicio de sesión y que se pueda ingresar a la página con dichas credenciales");

        DriverHandler driverHandler=new DriverHandler();
        WebDriver driver= driverHandler.getDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(2000);
        String ssPath= ScreenshotsClass.takeSS(driver, "Login");
        //Report inicio de prueba
        test1.info("Se inició la prueba",MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build()).addScreenCaptureFromPath(ssPath);
        String user="Admin";
        String pass="admin123";
        BasePage basePage=new BasePage(driver);
        basePage.doLogIn(user,pass);
        ssPath= ScreenshotsClass.takeSS(driver, "Admin");
        //Report inicio de sesión de admin
        test1.pass("Inicio de sesión de Admin exitoso",MediaEntityBuilder.createScreenCaptureFromPath
                (ssPath).build()).addScreenCaptureFromPath(ssPath);

        //Ingresar a PIM
        HomePage homePage=new HomePage(driver);
        homePage.getPIM();
        ssPath= ScreenshotsClass.takeSS(driver, "PIMPage");

        //Report ingreso a PIM
        test1.pass("Ingreso a pantalla PIM",MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build()).addScreenCaptureFromPath(ssPath);
        Thread.sleep(2000);

        //Crear nuevo usuario con opciones de Login
        PIMPage pimPage= new PIMPage(driver);
        String firstName="Jessica";
        String middleName= "Gabriela";
        String lastName="Alarcón";
        Random random= new Random();
        int num= random.nextInt(100)+1;
        String newUsername="jess_"+num;
        String newPassword="jessica0";
        pimPage.fillFormEmployee(firstName,middleName,lastName,newUsername,newPassword, newPassword);
        Thread.sleep(2000);
        ssPath= ScreenshotsClass.takeSS(driver, "newUser");
        //Report creación de nuevo usuario
        test1.pass("Se creó exitosamente el usuario "+firstName+" "+lastName,MediaEntityBuilder.createScreenCaptureFromPath
                (ssPath).build()).addScreenCaptureFromPath(ssPath);

        //Buscar usuario
       pimPage.doSearchEmployee();
        Thread.sleep(2000);
        ssPath= ScreenshotsClass.takeSS(driver, "IDSearch");
        //Validar que existe el usuario
        Thread.sleep(1000);
        WebElement idAssert=pimPage.validateIdSearch();
        Assert.assertNotNull(idAssert.getText());
        Assert.assertFalse(idAssert.getText().isEmpty());
        System.out.println("El usuario con id: "+idAssert.getText()+" existe.");
        test1.pass("Existe el usuario con ID: "+idAssert.getText(),MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build()).addScreenCaptureFromPath(ssPath);
        //Hacer LogOut
        homePage.doLogOut();

        //Login con el nuevo usuario
        basePage.doLogIn(newUsername,newPassword);
        ssPath= ScreenshotsClass.takeSS(driver, "newUserLogin");
        //Validar que se hizo login con nuevo usuario
        Thread.sleep(3000);
        WebElement userAssert=pimPage.validateUserLogin();
        Assert.assertNotNull(userAssert.getText());
        Assert.assertFalse(userAssert.getText().isEmpty());
        System.out.println("Bienvenido(a): "+userAssert.getText());
        test1.pass("El usuario "+newUsername+" ingresó correctamente",MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build()).addScreenCaptureFromPath(ssPath);

       // driverHandler.quit();

        test1.info("Finalizó la prueba de manera exitosa");
    }

    @AfterTest
    public void afterTest() throws IOException {
        extentReport.flush();
        //Abrir el reporte automáticamente
        Desktop.getDesktop().browse((new File("C:\\Users\\1000062343\\Documents\\selenium\\TestReport.html").toURI()));
    }

}