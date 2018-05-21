package projectgutenberg;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.SystemUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Cherry Rose Semeña
 */
public class Stepdefs {

    private static final int WAIT_MAX = 10;
    static WebDriver driver;

    public Stepdefs() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/projectgutenberg/chromedriver_win.exe");
        } else if (SystemUtils.IS_OS_UNIX) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/projectgutenberg/chromedriver");
        }
        driver = new ChromeDriver(chromeOptions);
    }

    @Given("^The city is '(.*)'$")
    public void the_city(String city) throws Throwable {
        driver.get("http://localhost:8084/dbtest/#!/view1");

    }

    @When("^Enter '(.*)' and choose '(.*)'$")
    public void enter_city_and_choose_db(String city, String database) throws Throwable {
        WebElement select_field = driver.findElement(By.id("select_db_dropdown"));
        WebElement cityfield = driver.findElement(By.id("city"));
        WebElement submit = driver.findElement(By.id("submit"));
        Select dropdown = new Select(select_field);
        dropdown.selectByValue(database);
        String input = city;
        cityfield.sendKeys(input);
        submit.click();
//        TimeUnit.SECONDS.sleep(WAIT_MAX);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Then("^I should get '(.*)'$")
    public void i_should_get_success(String page) throws Throwable {
        WebElement bookstable = driver.findElement(By.id("books"));
        WebElement body = bookstable.findElement(By.tagName("tbody"));
        List<WebElement> rowList = body.findElements(By.tagName("tr"));
        assertThat(rowList.size(), greaterThanOrEqualTo(1));
        driver.close();
    }

}
