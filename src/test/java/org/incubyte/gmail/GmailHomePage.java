package org.incubyte.gmail;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.incubyte.driverutils.DriverFactory;
import org.incubyte.reusablemethods.ReusableMethods;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class GmailHomePage {
    private WebDriver driver = DriverFactory.getInstance().getDriver();
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    @When("I login to Gmail using username & password")
    public void iLoginToGmailUsingUsernamePassword(Map<String, String> credentials) throws Exception {
        driver.findElement(By.id("identifierId")).sendKeys(credentials.get("username"));
        WebElement nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
        jsExecutor.executeScript("arguments[0].click()", nextButton);
        Thread.sleep(4000);
        driver.findElement(By.name("password")).sendKeys(credentials.get("password"));
        nextButton = driver.findElement(By.xpath("//span[text()='Next']"));
        jsExecutor.executeScript("arguments[0].click()", nextButton);
    }

    @Then("I verify Compose button is displayed in Gmail homepage")
    public void iVerifyComposeButtonIsDisplayedInGmailHomepage() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Compose']")).isDisplayed());
    }

    @And("I Logout from gmail")
    public void iLogoutFromGmail() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(@aria-label,'Google Account')]/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Sign out']")).click();
    }

    @And("I compose email to {string} with subject {string} and email content as {string}")
    public void iComposeEmailToWithSubject(String toAddress, String subject, String content) {
        driver.findElement(By.xpath("//div[text()='Compose']")).click();
        driver.findElement(By.xpath("//span[text()='To']/ancestor::tr[1]//textarea")).sendKeys(toAddress);
        driver.findElement(By.xpath("//span[text()='To']/ancestor::tr[1]//textarea")).sendKeys(Keys.ENTER);
        driver.findElement(By.name("subjectbox")).sendKeys(subject);
        driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys(content);

    }

    @And("I attach file {string} to composed mail")
    public void iAttachFileToComposedMail(String filenames) throws Exception {
        String[] fileNames = filenames.split(":");
        for (String fileName : fileNames) {
            String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\org\\incubyte\\files\\" + fileName;
            File file = new File(filePath);
            boolean isfileExists = file.exists();
            if (!isfileExists) {
                System.out.println("Please provide correct file name with extension or Place file in resources/files folder");
                throw new FileNotFoundException();
            }
            driver.findElement(By.name("Filedata")).sendKeys(file.getAbsolutePath());
            if (ReusableMethods.getFileSizeInMD(file) > 25) {
                int i = 0;
                while (ReusableMethods.isElementExists(driver, By.xpath("//div[contains(text(),'Your file is larger than 25MB.')]"))) {
                    ReusableMethods.wait(5);
                    i++;
                    //increase value if selenium need to wait for more time
                    if (i > 50) {
                        System.out.println("File upload is taking longer time");
                        break;
                    }
                }
            } else {
                int i = 0;
                while (ReusableMethods.isElementExists(driver, By.xpath("//div[contains(@aria-label,'Uploading ') and @role='progressbar']"))) {
                    ReusableMethods.wait(5);
                    i++;
                    if (i > 20)
                        break;
                }
            }
        }
    }

    @Then("I send mail")
    public void iSendMail() throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='Send']")).click();
        ReusableMethods.wait(3);
        if (ReusableMethods.isElementExists(driver, By.xpath("//div[@role='dialog' and @aria-hidden='false']//iframe[@title='Sharing']"))) {
            driver.switchTo().frame(driver.findElement(By.xpath("//div[@role='dialog' and @aria-hidden='false']//iframe[@title='Sharing']")));
            driver.findElement(By.xpath("//span[text()='Send']")).click();
            driver.switchTo().defaultContent();
        }
    }

    @And("I attach files to composed mail")
    public void iAttachFilesToComposedMail(DataTable table) throws Exception {
        List<String> files = table.asList();
        for (String filename : files) {
            String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\org\\incubyte\\files\\" + filename;
            File file = new File(filePath);
            boolean isfileExists = file.exists();
            if (!isfileExists) {
                System.out.println("Please provide correct file name with extension or Place file in resources/files folder");
                throw new FileNotFoundException();
            }

            driver.findElement(By.name("Filedata")).sendKeys(file.getAbsolutePath());

        }
    }


}
