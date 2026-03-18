import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.task.BaseTestUI;
import org.task.POJO.CreateUserBodyPojo;
import org.task.POJO.CreateUserResponse;
import org.task.UserServiceActions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserTest extends BaseTestUI {
    UserServiceActions userServiceActions;
    String url = "http://localhost:8080/admin/users";

    public CreateUserTest() {
        this.userServiceActions = new UserServiceActions(this.apiClient);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "validuser, validuser@email.com",
            "nonvaliduser, nonvaliduser@email",
            "duplicateduser, duplicateduser@email.com"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void createUserTestAndDeleteUserTest(String username, String email) {

        CreateUserResponse createUserResponse = getCreateUserResponse(username, email);
        driver.get(url);
        By usersListById = By.id("Userslist");
        WebElement usersList = driver.findElement(usersListById);

        assertTrue(usersList.getText().contains(username));

        deleteUser(createUserResponse);
        driver.navigate().refresh();
        usersList = driver.findElement(usersListById);

        assertFalse(usersList.getText().contains(username));
    }

    private void deleteUser(CreateUserResponse createUserResponse) {
        userServiceActions.deleteUser(createUserResponse.getId())
                .then()
                .statusCode(201);
    }

    private CreateUserResponse getCreateUserResponse(String username, String email) {
        return userServiceActions.createUser(
                        CreateUserBodyPojo
                                .builder()
                                .name(username)
                                .email(email)
                                .build())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CreateUserResponse.class);
    }
}