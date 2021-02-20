package org.incubyte.reusablemethods;

import org.incubyte.driverutils.DriverFactory;
import org.incubyte.driverutils.DriverInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ReusableMethods {
    private static WebDriver driver= DriverFactory.getInstance().getDriver();
    public static int getFileSizeInMD(File file) {
        return (int) ((double) file.length() / (1024 * 1024));
    }
    public static void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds* 1000L);
    }
    public static boolean isElementExists(By by){
        boolean isExists=false;
        try{
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
           WebElement element= driver.findElement(by);
           isExists=true;
        }catch (NoSuchElementException e){
            driver.manage().timeouts().implicitlyWait(DriverInitializer.DEFAULT_WAIT, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(DriverInitializer.DEFAULT_WAIT, TimeUnit.SECONDS);
        return isExists;
    }
}
