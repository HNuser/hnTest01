import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementSelectionStateToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by hnikolenko on 24.05.2017.
 */
public class MyFirstTest {

    String calcPage = "http://juliemr.github.io/protractor-demo/";
    private WebDriver driver;


    @BeforeTest
    public void setup() {
        final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.navigate().to(calcPage);


    }

    @Test(priority = 1)
    public void currentPageCheck() {
        Assert.assertEquals(calcPage, driver.getCurrentUrl(), "URL not matching");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test(priority = 2)
    public void setValues() {
        WebElement firstNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[1]"));
        firstNumber.sendKeys("1");
        Assert.assertEquals(firstNumber.getAttribute("value"), "1");

        WebElement secondNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[2]"));
        secondNumber.sendKeys("1");
        Assert.assertEquals(secondNumber.getAttribute("value"), "1");



    }


/*    @Test(priority = 3)
    public void mathAction() {
        // WebElement dropdown = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select objectDropdownValue = new Select(driver.findElement(By.xpath("/html/body/div/div/form/select")));
        objectDropdownValue.selectByValue("ADDITION");
        Assert.assertEquals(objectDropdownValue.getFirstSelectedOption().getText(), "+");

    }*/

    @Test(priority = 3)
    public void mathAction() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<WebElement> elementList = driver.findElements(By.xpath("/html/body/div/div/form/select"));
        int listSize = elementList.size();
        for(int i=0; i<listSize; i++) {
            elementList.get(i).click();
            System.out.println("List = " + elementList);

        }
    }


    @Test(priority = 4)
    public void mathResult() {
        WebElement equalElement = driver.findElement(By.id("gobutton"));
        equalElement.click();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[3]")).getText(), "2");


    }


}
