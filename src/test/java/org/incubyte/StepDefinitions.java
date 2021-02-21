package org.incubyte;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.apache.commons.io.FileUtils;
import org.incubyte.driverutils.Browser;
import org.incubyte.driverutils.DriverFactory;
import org.incubyte.driverutils.DriverInitializer;
import org.incubyte.reusablemethods.ReusableMethods;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;


public class StepDefinitions {
    public WebDriver driver;
    private Browser browser = Browser.CHROME;

    @Given("I launch {string} browser")
    public void iLaunchBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome"))
            this.browser = Browser.CHROME;
        else if (browser.equalsIgnoreCase("ie"))
            this.browser = Browser.IE;
        else if (browser.equalsIgnoreCase("firefox"))
            this.browser = Browser.FIREFOX;
        else if (browser.equalsIgnoreCase("edge"))
            this.browser = Browser.EDGE;
        else if (browser.equalsIgnoreCase("remote_chrome"))
            this.browser = Browser.REMOTE_CHROME;
        else {
            System.out.println("Provided incorrect browser value, launching chrome browser as default...");
        }
        DriverInitializer initializer = new DriverInitializer();
        driver = initializer.setDriver(this.browser, DriverFactory.getInstance().getDriver());
        driver = DriverFactory.setDriverInstance(driver).getDriver();
    }

    @Given("I navigate to {string} url")
    public void iNavigateToUrl(String url) {
        driver = DriverFactory.getInstance().getDriver();
        driver.get(url);
        System.out.println("Navigated to Url: " + url);
    }

    public void copyReportToFolder() {
        try {
            File file = new File(System.getProperty("user.dir") + "\\target\\cucumber-reports.html");
            if (file.exists()) {
                FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\Reports\\Cucumber Report" +
                        ReusableMethods.generateNameForReports() + ".html"));
            }
        } catch (Exception ignored) {
        }

    }

    @Before
    public void clearExistingReports() {
        try {
            File file = new File(System.getProperty("user.dir") + "\\target\\cucumber-reports.html");
            if (file.exists()) {
                System.out.println("File deleted: " + file.delete());
            }
        } catch (Exception ignored) {
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", DriverFactory.getInstance().toString());
        }
        copyReportToFolder();
        DriverFactory.getInstance().removeDriver();
    }
}
