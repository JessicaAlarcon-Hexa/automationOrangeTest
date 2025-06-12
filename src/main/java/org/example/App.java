package org.example;

import org.example.Paginas.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Random;

public class App
{
    public static void main( String[] args ) throws InterruptedException
    {
        //Abrir pagina con firefox
        DriverHandler driverHandler=new DriverHandler();
        WebDriver driver= driverHandler.getDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //Ingresar credenciales e iniciar sesion
        String user="Admin";
        String pass="admin123";
        BasePage basePage=new BasePage(driver);
        basePage.doLogIn(user,pass);

        //Ingresar a PIM
        HomePage homePage=new HomePage(driver);
        homePage.getPIM();

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

        //Hacer LogOut
        homePage.doLogOut();

        //Login con el nuevo usuario
       basePage.doLogIn(newUsername,newPassword);

        //Ingresar a Time
        homePage.getTime();
        //Seleccionar fecha del calendario
        TimePage timePage= new TimePage(driver);
        timePage.selectDate();
        //Crear nueva timesheet de la fecha seleccionada
        timePage.createTimesheet();
                timePage.editTimesheet();
        //Ingresar projecto, actividad y horas a la timesheet y submit
        String projectName="Apache";
        String weekDays="8";
        String weekEnds="0";
        timePage.fillTimesheet(projectName,weekDays,weekEnds);
        timePage.saveTimesheet();
        Thread.sleep(3000);
        timePage.submitSheet();
        Thread.sleep(2000);
        homePage.doLogOut();

        //Login de Admin
        basePage.doLogIn(user,pass);
        //Ingresar a Time
        homePage.getTime();
        //Buscar empleado por nombre
        timePage.getEmployee(firstName);
        timePage.selectDate();
        //Validar horas del timesheet
        String horas="40";
        WebElement vldHours= timePage.validateHours(horas);
        Assert.assertNotNull(vldHours.getText());
        Assert.assertTrue(vldHours.getText().contains(horas));
        System.out.println("Se trabajaron " +horas+ "horas a la semana");
        //Aprobar Timesheet
        timePage.approveTimesheet();
        homePage.doLogOut();
        //Login nuevo usuario
        homePage.doLogIn(newUsername,newPassword);
        //Ingresar a Time
        homePage.getTime();
        //Seleccionar la fecha del timesheet
        timePage.selectDate();
        //Validar que esté aprobado el timesheet
        String status="Approved";
        WebElement state= timePage.validateStatus(status);
        Assert.assertNotNull(state.getText());
        Assert.assertTrue(state.getText().contains(status));
        System.out.println("Timesheet is approved");

    }
}
