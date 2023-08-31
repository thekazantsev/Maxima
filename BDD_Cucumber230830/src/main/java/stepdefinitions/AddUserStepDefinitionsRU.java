package stepdefinitions;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import model.User;
import model.UsersArchive;
import org.testng.Assert;

import java.util.Optional;

public class AddUserStepDefinitionsRU {
    User user;
    String userName;

    @Дано("Я нахожусь на главной странице")
    public void iAmOnTheMainPage() {
        System.out.println("Ты на главной странице...");
    }

    @Когда("Я указываю имя пользователя {string}")
    public void iEnterTheUserName(String userName) {
        user = new User(userName);
        System.out.printf("Создаем нового пользователя с именем '%s'\n", userName);
    }

    @И("И нажимаю кнопку добавить {string}")
    public void iClickTheButton(String buttonName) {
        UsersArchive.addUser(user);
        userName = user.getName();
        System.out.printf("Кнопка '%s' нажата\n", buttonName);
    }

    @Тогда("Я должен увидеть сообщение {string}")
    public void iShouldSeeTheResponse(String expectedResponse) {
        Optional<User> lastAddedUser = UsersArchive.getLastAddedUser();
        if (lastAddedUser.isPresent()) {
            User user = lastAddedUser.get();
            String userName = user.getName();

            Assert.assertEquals(user.getName(), userName);
            System.out.println(expectedResponse);
        } else {
            // Handle the case when the Optional is empty
            System.out.println("Архив пуст. Пользователь не добавлен.");
        }
    }
}
