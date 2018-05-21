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

    @When("^Enter the city as '(.*)' and choose '(.*)'$")
    public void enter_city_and_choose_db(String city, String database) throws Throwable {
        WebElement select_field = driver.findElement(By.id("select_db_dropdown"));
        WebElement cityfield = driver.findElement(By.id("city"));
        WebElement submit = driver.findElement(By.id("submit"));
        Select dropdown = new Select(select_field);
        dropdown.selectByValue(database);
        String input = city;
        cityfield.sendKeys(input);
        submit.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^I should get S1 result as '(.*)'$")
    public void i_should_get_success(String result) throws Throwable {
        WebElement bookstable = driver.findElement(By.id("books"));
        WebElement body = bookstable.findElement(By.tagName("tbody"));
        List<WebElement> rowList = body.findElements(By.tagName("tr"));
        assertThat(rowList.size(), greaterThanOrEqualTo(1));
        driver.close();
    }
    
    @Given("^The title is '(.*)'$")
    public void the_title(String title) throws Throwable {
        driver.get("http://localhost:8084/dbtest/#!/view2");
    }
    
    @When("^Enter the title as '(.*)' and choose '(.*)'$")
    public void enter_title_and_choose_db(String title, String database) throws Throwable {
        WebElement select_field = driver.findElement(By.id("select_db_dropdown"));
        WebElement cityfield = driver.findElement(By.id("title"));
        WebElement submit = driver.findElement(By.id("submit"));
        Select dropdown = new Select(select_field);
        dropdown.selectByValue(database);
        String input = title;
        cityfield.sendKeys(input);
        submit.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^I should get S2 result as '(.*)'$")
    public void i_should_get_success2(String result) throws Throwable {
        WebElement citiestable = driver.findElement(By.id("cities"));
        WebElement body = citiestable.findElement(By.tagName("tbody"));
        List<WebElement> rowList = body.findElements(By.tagName("tr"));
        assertThat(rowList.size(), greaterThanOrEqualTo(1));
        driver.close();
    }
    
    @Given("^The author is '(.*)'$")
    public void the_author(String author) throws Throwable {
        driver.get("http://localhost:8084/dbtest/#!/view3");
    }
    
    @When("^Enter the author as '(.*)' and choose '(.*)'$")
    public void enter_author_and_choose_db(String author, String database) throws Throwable {
        WebElement select_field = driver.findElement(By.id("select_db_dropdown"));
        WebElement authorfield = driver.findElement(By.id("author"));
        WebElement submit = driver.findElement(By.id("submit"));
        Select dropdown = new Select(select_field);
        dropdown.selectByValue(database);
        String input = author;
        authorfield.sendKeys(input);
        submit.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^I should get S3 result as '(.*)'$")
    public void i_should_get_success3(String result) throws Throwable {
        WebElement summarytable = driver.findElement(By.id("summarytable"));
        WebElement body = summarytable.findElement(By.tagName("tbody"));
        WebElement rowbooks = body.findElement(By.id("books"));
        List<WebElement> data = rowbooks.findElements(By.tagName("td"));
        
        WebElement cityrows = body.findElement(By.id("cities"));
        List<WebElement> data2 = cityrows.findElements(By.tagName("td"));
        
        assertThat(data.size(), greaterThanOrEqualTo(1));
        assertThat(data2.size(), greaterThanOrEqualTo(1));
        driver.close();
    }
    
    
    

}
