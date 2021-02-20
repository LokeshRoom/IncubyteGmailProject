package org.incubyte.driverutils;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static DriverFactory instance = new DriverFactory();
    private WebDriver webDriver = null;
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
    {
        @Override
        protected WebDriver initialValue() {

            return webDriver;// can be replaced with other browser drivers
        }
    };

    private DriverFactory() {

    }

    public static DriverFactory getInstance(WebDriver... driver) {
        if (driver.length > 0) {
            instance.setDriver(driver[0]);
        }
        return instance;
    }
    public static DriverFactory setDriverInstance(WebDriver driver) {

            instance.setDriver(driver);

        return instance;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }

}
