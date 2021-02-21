package org.incubyte.gmail;

import io.cucumber.java.en.And;
import org.incubyte.driverutils.DriverFactory;
import org.incubyte.reusablemethods.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GmailSentPage {

    private WebDriver driver = DriverFactory.getInstance().getDriver();

    @And("I verify attachments {string} are attached")
    public void iVerifyAttachmentsAreAttached(String files) {
        String[] fileNames = files.split(":");
        for (String fileName : fileNames) {
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Preview attachment')]/following-sibling::div[1]//span[text()='" + fileName + "']")).isDisplayed());
        }
    }

    @And("I verify mail is sent with email id {string} subject {string} and email content as {string}")
    public void iVerifyMailIsSentWithEmailIdAndSubject(String emailID, String subject, String content) {
       /* ReusableMethods.jsClick(By.xpath("//a[text()='Sent']"));
        ReusableMethods.wait(5);
        ReusableMethods.jsClick(By.xpath("//div[@role=\"main\"]//tr[@role=\"row\"][1]//div[contains(text(),'To:')]"));*/
        boolean isDisplayedMailSent = false;
        int i = 1;
        while (i <= 15) {
            try {
                if (ReusableMethods.isElementExists(driver, By.xpath("//span[text()='Message sent.']"))) {
                    ReusableMethods.jsClick(driver, By.xpath("//span[text()='View message']"));
                    isDisplayedMailSent = true;

                    break;
                }
            } catch (Exception ignored) {
            }
            i++;
        }
        Assert.assertTrue(isDisplayedMailSent);
        driver.findElement(By.xpath("//div[@aria-label=\"Show details\"]//img")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@style,'visibility: visible')]//span[text()='" + emailID + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='" + subject + "']")).isDisplayed());
        String emailBody = driver.findElement(By.xpath("//div[@role=\"listitem\"]//div[@dir=\"ltr\"]")).getText();
        Assert.assertTrue(emailBody.contains(content));

    }

    @And("I verify mail is sent")
    public void iVerifyMailIsSent() {
        /*boolean isDisplayedMailSent = false;
        int i = 1;
        while (i <= 15) {
            try {
                if (ReusableMethods.isElementExists(By.xpath("//span[text()='Message sent.']"))) {
                    isDisplayedMailSent = true;
                    break;
                }
            } catch (Exception ignored) {
            }
            i++;
        }
        Assert.assertTrue(isDisplayedMailSent);
        ReusableMethods.wait(1);*/
        driver.findElement(By.linkText("Sent")).click();
        driver.findElement(By.xpath("//div[@role=\"main\"]//tr[@role=\"row\"][1]//div[contains(text(),'To:')]")).click();

    }
}
