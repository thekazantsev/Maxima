package stepdefinitions;

import model.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import model.UsersArchive;
import org.testng.Assert;

import java.util.Optional;

public class AddUserStepDefinitions {
    User user;
    String userName;

    @Given("I am on the main page")
    public void iAmOnTheMainPage() {
        System.out.println("You are on the Main Page...");
    }

    @When("I enter the user name {string}")
    public void iEnterTheUserName(String userName) {
        user = new User(userName);
        System.out.printf("Creating new User with name '%s'\n", userName);
    }

    @And("I click the {string} button")
    public void iClickTheButton(String buttonName) {
        UsersArchive.addUser(user);
        userName = user.getName();
        System.out.printf("Button '%s' is clicked\n", buttonName);
    }

    @Then("I should see the response {string}")
    public void iShouldSeeTheResponse(String expectedResponse) {
        Optional<User> lastAddedUser = UsersArchive.getLastAddedUser();
        if (lastAddedUser.isPresent()) {
            User user = lastAddedUser.get();
            String userName = user.getName();

            Assert.assertEquals(user.getName(), userName);
            System.out.println(expectedResponse);
        } else {
            System.out.println("Archive is empty. User not added.");
        }
    }
}
