package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/functions",
        glue = {"stepDefination"}
        )

public class runner extends AbstractTestNGCucumberTests{	
}