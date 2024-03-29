import com.github.javafaker.Faker;
import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class RegisterTest {


    public static WebDriver driver;
    static Faker faker = new Faker();

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
    }

    @Test(priority = 1)
    public static void testFirstName() {
        if(driver == null) {
            setUp();
        }
        String firstName = faker.name().firstName();
        WebElement firstNameField = driver.findElement(By.name("firstname"));
        firstNameField.sendKeys(firstName);
        assert firstNameField.getAttribute("value").equals(firstName);
    }

    @Test(priority = 2)
    public static void testLastName() {
        Faker faker = new Faker();
        String lastName = faker.name().lastName();

        WebElement lastNameField = driver.findElement(By.name("lastname"));
        lastNameField.sendKeys(lastName);

        assert lastNameField.getAttribute("value").equals(lastName);
    }

    @Test(priority = 3)
    public static void testEmail() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();

        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys(email);

        assert emailField.getAttribute("value").equals(email);
    }

    @Test(priority = 4)
    public static void testTelephone() {
        Faker faker = new Faker();
        String telephone = faker.phoneNumber().cellPhone();

        WebElement telephoneField = driver.findElement(By.name("telephone"));
        telephoneField.sendKeys(telephone);

        assert telephoneField.getAttribute("value").equals(telephone);
    }

    @Test(priority = 5)
    public static void testPassword() {
        Faker faker = new Faker();
        String password = faker.internet().password();

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);

        WebElement confirmPasswordField = driver.findElement(By.name("confirm"));
        confirmPasswordField.sendKeys(password);

        assert passwordField.getAttribute("value").equals(confirmPasswordField.getAttribute("value"));
    }

    @Test(priority = 6)
    public static void testPrivacyPolicyCheckbox() {
        WebElement privacyPolicyCheckbox = driver.findElement(By.xpath("//label[@class=\"custom-control-label\"][@for=\"input-agree\"]"));
        privacyPolicyCheckbox.click();
        assert privacyPolicyCheckbox.isEnabled();

    }

    @Test(priority = 7)
    public static void testSubmitButton() {
        WebElement SubmitButton = driver.findElement(By.xpath("//INPUT[@type='submit']"));
        SubmitButton.click();
        WebElement messageSuc = driver.findElement(By.xpath("//h1[@class=\"page-title my-3\"]"));
        assert messageSuc.getText().equals(messageSuc.getText());



    }


    @AfterClass
    public static void tearDown() {
        driver.close();

    }
}
