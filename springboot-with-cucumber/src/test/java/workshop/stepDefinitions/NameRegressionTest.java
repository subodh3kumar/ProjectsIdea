package workshop.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import workshop.MainApplication;

@Slf4j
//@CucumberContextConfiguration
//@ContextConfiguration
public class NameRegressionTest extends CucumberSpringConfiguration {

    @Given("upload the names text file")
    public void upload() {
        log.info("upload() method start");
        MainApplication.main(new String[]{});
    }

    @When("load the NAMES table from database")
    public void load() {
        log.info("load() method start");
    }

    @Then("validate names with below data grid")
    public void validate(io.cucumber.datatable.DataTable dataTable) {
        log.info("validate() method start");
    }
}
