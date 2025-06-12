package org.example.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.example.Paginas.DriverHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;

public class ExtentReportsClass
{

    public static void main(String[] args) throws IOException, InterruptedException {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\1000062343\\Documents\\selenium\\sparkReport.html");
        extentReports.attachReporter(sparkReporter);


       ExtentTest test1= extentReports.createTest("T-001 Crear nuevo usuario","Validar que se cree un nuevo usuario con credenciales " +
               "de inicio de sesión y que se pueda ingresar a la página con dichas credenciales");

        DriverHandler driverHandler=new DriverHandler();
        WebDriver driver= driverHandler.getDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Thread.sleep(2000);
        String ssPath= ScreenshotsClass.takeSS(driver, "Login");
        test1.info("inicio de prueba", MediaEntityBuilder.createScreenCaptureFromPath(ssPath).build()).addScreenCaptureFromPath(ssPath);
        driver.findElement(By.name("username"));
Thread.sleep(2000);


   /*     TakesScreenshot ts=(TakesScreenshot) driver;
        File fileSS=ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(fileSS,new File("C:\\Users\\1000062343\\Documents\\selenium\\Screenshots\\Login.png"));*/

      // test1.pass("Prueba Exitosa", MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\1000062343\\Documents\\selenium\\Screenshots\\Login.png").build());


        extentReports.flush();
        //Abrir el reporte automáticamente
        Desktop.getDesktop().browse((new File("C:\\Users\\1000062343\\Documents\\selenium\\sparkReport.html").toURI()));
    }
/*
    public static String captureSS()
    {
        TakesScreenshot takeSS= (TakesScreenshot) driver;
        String base64Code= takeSS.getScreenshotAs(OutputType.BASE64);
        System.out.println("Screenshot taken");
        return base64Code;
    }

   public static String captureSS(String fileName)
    {
        TakesScreenshot takeSS= (TakesScreenshot) driver;
        File sourceFile= takeSS.getScreenshotAs(OutputType.FILE);
        File destinationFile= new File("C:\\Users\\1000062343\\Documents\\selenium\\"+ fileName);



        System.out.println("Screenshot taken");
        return destinationFile.getAbsolutePath();
    }*/
}
