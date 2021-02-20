package org.incubyte;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(plugin = {"pretty","html:target/cucumber-reports.html"},glue = {"org.incubyte"},features = {"src/test/resources/org/incubyte/features"})
public class RunCucumberTest  extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}