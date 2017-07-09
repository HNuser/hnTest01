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
 * Created by pc on 02.07.2017.
 */
public class MyFirstTest {

    String calcPage = "http://juliemr.github.io/protractor-demo/";
    private WebDriver driver;
    private Select select;



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
     //   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test(priority = 2 )
    public void setFirstValues() {
        WebElement firstNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[1]"));
        firstNumber.sendKeys("1");
        Assert.assertEquals(firstNumber.getAttribute("value"), "1");

        WebElement secondNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[2]"));
        secondNumber.sendKeys("1");
        Assert.assertEquals(secondNumber.getAttribute("value"), "1");


    }


    @Test(priority = 3 )
    public void mathAdditionAction() {
        // WebElement dropdown = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select objectDropdownValue = new Select(driver.findElement(By.xpath("/html/body/div/div/form/select")));
        objectDropdownValue.selectByValue("ADDITION");
        Assert.assertEquals(objectDropdownValue.getFirstSelectedOption().getText(), "+");
    }

/*        @Test(priority = 3)
        public void mathAction() {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            List<WebElement> elementList = driver.findElements(By.xpath("/html/body/div/div/form/select"));
            int listSize = elementList.size();
            for (int i = 0; i < listSize; i++) {
                elementList.get(i).click();
                System.out.println("List = " + elementList);

            }
        }*/

/*    public Select getSelect(WebElement element) {
        select = new Select(element);
        return select;
    }

    @Test(priority = 3)
    public void mathAction() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement listText = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        getSelect(listText);
        List<WebElement> options = select.getOptions();
        int i = 1;
        for (WebElement option : options) {
            System.out.println(i + " Value = " + option.getText());
            if (option.getText().equals("+")) {
                System.out.println(" Equals ");
                option.click();

            }
            i++;

        }
    }*/

    @Test(priority = 4)
    public void mathAdditionResult() {
        WebElement equalElement = driver.findElement(By.id("gobutton"));
        equalElement.click();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[3]")).getText(), "2");


    }


    @Test(priority = 5)
    public void setSecondValues() {
       // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement firstNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[1]"));
        firstNumber.sendKeys("1");
        Assert.assertEquals(firstNumber.getAttribute("value"), "1");

        WebElement secondNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[2]"));
        secondNumber.sendKeys("1");
        Assert.assertEquals(secondNumber.getAttribute("value"), "1");


    }

    @Test(priority = 6)
    public void mathDivisionAction() {
        // WebElement dropdown = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select objectDropdownValue = new Select(driver.findElement(By.xpath("/html/body/div/div/form/select")));
        objectDropdownValue.selectByValue("DIVISION");
        Assert.assertEquals(objectDropdownValue.getFirstSelectedOption().getText(), "/");
    }

    @Test(priority = 7)
    public void mathDivisionResult() {
        WebElement equalElement = driver.findElement(By.id("gobutton"));
        equalElement.click();
        WebDriverWait waitForOne = new WebDriverWait(driver, 10);
        waitForOne.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/table/tbody/tr/td[3]"), "2"));
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[3]")).getText(), "1");


    }

    @Test(dependsOnMethods = "mathDivisionResult", priority = 8)
    public void setThirdValues() {
        WebElement firstNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[1]"));
        firstNumber.sendKeys("1");
        Assert.assertEquals(firstNumber.getAttribute("value"), "1");

        WebElement secondNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[2]"));
        secondNumber.sendKeys("1");
        Assert.assertEquals(secondNumber.getAttribute("value"), "1");


    }

    @Test(dependsOnMethods = "setThirdValues", priority = 9)
    public void mathModuloAction() {
        // WebElement dropdown = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select objectDropdownValue = new Select(driver.findElement(By.xpath("/html/body/div/div/form/select")));
        objectDropdownValue.selectByValue("MODULO");
        Assert.assertEquals(objectDropdownValue.getFirstSelectedOption().getText(), "%");
    }

    @Test(priority = 10)
        public void mathModuloResult() {
        WebElement equalElement = driver.findElement(By.id("gobutton"));
        equalElement.click();
        WebDriverWait waitForOne = new WebDriverWait(driver, 10);
        waitForOne.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/table/tbody/tr/td[3]"), "1"));
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[3]")).getText(), "0");


    }

    @Test(dependsOnMethods = "mathModuloResult", priority = 11)
    public void setForthValues() {
        WebElement firstNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[1]"));
        firstNumber.sendKeys("1");
        Assert.assertEquals(firstNumber.getAttribute("value"), "1");

        WebElement secondNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[2]"));
        secondNumber.sendKeys("1");
        Assert.assertEquals(secondNumber.getAttribute("value"), "1");


    }

    @Test(dependsOnMethods = "setForthValues", priority = 12)
    public void mathMultiplicationAction() {
        // WebElement dropdown = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select objectDropdownValue = new Select(driver.findElement(By.xpath("/html/body/div/div/form/select")));
        objectDropdownValue.selectByValue("MULTIPLICATION");
        Assert.assertEquals(objectDropdownValue.getFirstSelectedOption().getText(), "*");

    }

   @Test(priority = 13)
    public void mathMultiplicationResult() {
        WebElement equalElement = driver.findElement(By.id("gobutton"));
        equalElement.click();
       WebDriverWait waitForOne = new WebDriverWait(driver, 10);
       waitForOne.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/table/tbody/tr/td[3]"), "0"));
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[3]")).getText(), "1");


    }

    @Test(dependsOnMethods = "mathMultiplicationResult", priority = 14)
    public void setFifthValues() {
        WebElement firstNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[1]"));
        firstNumber.sendKeys("1");
        Assert.assertEquals(firstNumber.getAttribute("value"), "1");

        WebElement secondNumber = driver.findElement(By.xpath("/html/body/div/div/form/input[2]"));
        secondNumber.sendKeys("1");
        Assert.assertEquals(secondNumber.getAttribute("value"), "1");


    }

    @Test(dependsOnMethods = "setFifthValues", priority = 15)
    public void mathSubtractionAction() {
        // WebElement dropdown = driver.findElement(By.xpath("/html/body/div/div/form/select"));
        Select objectDropdownValue = new Select(driver.findElement(By.xpath("/html/body/div/div/form/select")));
        objectDropdownValue.selectByValue("SUBTRACTION");
        Assert.assertEquals(objectDropdownValue.getFirstSelectedOption().getText(), "-");

    }
    @Test(priority = 16)
    public void mathSubtractionResult() {
        WebElement equalElement = driver.findElement(By.id("gobutton"));
        equalElement.click();
        WebDriverWait waitForOne = new WebDriverWait(driver, 10);
        waitForOne.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/table/tbody/tr/td[3]"), "1"));
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[3]")).getText(), "0");


    }

}
