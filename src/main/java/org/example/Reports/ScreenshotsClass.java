package org.example.Reports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotsClass
{
    public static String takeSS(WebDriver driver, String fileName)
    {
        File srcFile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path= System.getProperty("user.dir")+"\\selenium\\Screenshots\\"+fileName+"_"+timeStamp+".png";

        try
        {
            FileUtils.copyFile(srcFile, new File(path));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return path;
    }
}
