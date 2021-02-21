package org.incubyte.reusablemethods;

import org.incubyte.driverutils.DriverFactory;
import org.incubyte.driverutils.DriverInitializer;
import org.openqa.selenium.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReusableMethods {

    private WebDriver driver = DriverFactory.getInstance().getDriver();

    public static int getFileSizeInMD(File file) {
        return (int) ((double) file.length() / (1024 * 1024));
    }

    public static void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
    }

    public static boolean isElementExists(WebDriver driver, By by) {
        boolean isExists;
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement element = driver.findElement(by);
            isExists = true;
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(DriverInitializer.DEFAULT_WAIT, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(DriverInitializer.DEFAULT_WAIT, TimeUnit.SECONDS);
        return isExists;
    }

    public static void jsClick(WebDriver driver, By by) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].click()", driver.findElement(by));
    }

    public static String generateNameForReports() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy HHmmss");
        //DateFormatter dateFormatter= new DateFormatter("ddMMyyyy HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
