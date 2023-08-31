package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class AddUserStepDefinitions {

    @Given("I am on the main page")
    public void iAmOnTheMainPage() {
        // Implement the code to navigate to the main page
    }

    @When("I enter the user name {string}")
    public void iEnterTheUserName(String userName) {
        // Implement the code to enter the user name in the input field
    }

    @When("I click the {string} button")
    public void iClickTheButton(String buttonName) {
        // Implement the code to click the specified button
    }

    @Then("I should see the response {string}")
    public void iShouldSeeTheResponse(String expectedResponse) {
        // Implement the code to verify the response text on the page
    }
}