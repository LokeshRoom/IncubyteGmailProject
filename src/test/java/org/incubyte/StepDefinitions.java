package org.incubyte;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.incubyte.driverutils.Browser;
import org.incubyte.driverutils.DriverFactory;
import org.incubyte.driverutils.DriverInitializer;
import org.openqa.selenium.WebDriver;


public class StepDefinitions {
    public WebDriver driver;
    private Browser browser=Browser.CHROME;

    @Given("I launch {string} browser")
    public void iLaunchBrowser(String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome"))
           this.browser=Browser.CHROME;
        else if (browser.equalsIgnoreCase("ie"))
            this.browser=Browser.IE;
        else if(browser.equalsIgnoreCase("firefox"))
            this.browser=Browser.FIREFOX;
        else if(browser.equalsIgnoreCase("edge"))
            this.browser=Browser.EDGE;
        else if(browser.equalsIgnoreCase("remote_chrome"))
            this.browser=Browser.REMOTE_CHROME;
        else{
            System.out.println("Provided incorrect browser value, launching chrome browser as default...");}
        DriverInitializer initializer=new DriverInitializer();
        driver=initializer.setDriver(this.browser, DriverFactory.getInstance().getDriver());
        driver= DriverFactory.setDriverInstance(driver).getDriver();
    }

    @Given("I navigate to {string} url")
    public void iNavigateToUrl(String url) {
        driver= DriverFactory.getInstance().getDriver();
        driver.get(url);
        System.out.println("Navigated to Url: "+url);
    }
    @After
    public void tearDown(){
        DriverFactory.getInstance().removeDriver();
    }
}
