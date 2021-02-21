package org.incubyte.driverutils;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static DriverFactory instance = new DriverFactory();
    private WebDriver webDriver = null;
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            return webDriver;
        }
    };

    private DriverFactory() {

    }

    public static DriverFactory getInstance() {
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
