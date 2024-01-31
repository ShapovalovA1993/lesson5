package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AutomationPracticeFormTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();
            webDriver = new OperaDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void checkPracticeFormDataSubmit() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 30, 500);
        webDriver.get("https://demoqa.com/automation-practice-form");
        webDriver.manage().window().maximize();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("practice-form-wrapper")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName-wrapper")));
        webDriver.findElement(By.id("firstName")).sendKeys("Alex");
        String firstName = webDriver.findElement(By.id("firstName")).getAttribute("value");

        webDriver.findElement(By.id("lastName")).sendKeys("Shapovalov");
        String lastName = webDriver.findElement(By.id("lastName")).getAttribute("value");
        String studentName = firstName + " " + lastName;

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userEmail-wrapper")));
        webDriver.findElement(By.id("userEmail")).sendKeys("example@gmail.ru");
        String studentEmail = webDriver.findElement(By.id("userEmail")).getAttribute("value");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("genterWrapper")));
        //почему-то не получилось кликнуть по радио-баттону таким способом webDriver.findElement(By.cssSelector("input[name='gender'][value='Male']")).click();
        webDriver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
        String gender = webDriver.findElement(By.cssSelector("label[for='gender-radio-1']")).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNumber-wrapper")));
        webDriver.findElement(By.id("userNumber")).sendKeys("9996435458");
        String mobile = webDriver.findElement(By.id("userNumber")).getAttribute("value");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dateOfBirth-wrapper")));
        webDriver.findElement(By.id("dateOfBirthInput")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("react-datepicker")));

        WebElement selectBirthYear = webDriver.findElement(By.cssSelector("select[class='react-datepicker__year-select']"));
        Select year = new Select(selectBirthYear);
        year.selectByValue("1993");

        WebElement selectBirthMonth = webDriver.findElement(By.cssSelector("select[class='react-datepicker__month-select']"));
        Select month = new Select(selectBirthMonth);
        month.selectByVisibleText("November");

        webDriver.findElement(By.cssSelector("div[class='react-datepicker__day react-datepicker__day--021 react-datepicker__day--weekend']")).click();
        String birthDate = webDriver.findElement(By.id("dateOfBirthInput")).getAttribute("value");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subjectsWrapper")));
        webDriver.findElement(By.id("subjectsInput")).sendKeys("Maths");
        webDriver.findElement(By.id("subjectsInput")).sendKeys(Keys.ENTER);
        String subject = webDriver.findElement(By.xpath("//div[contains(text(), 'Maths')]")).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hobbiesWrapper")));
        //почему-то не получилось кликнуть по чек-боксу таким способом webDriver.findElement(By.cssSelector("input[id='hobbies-checkbox-1'][value='1']")).click();
        webDriver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).click();
        String hobbies = webDriver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']")).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("form-file")));
        webDriver.findElement(By.cssSelector("label[class='form-file-label']")).click();
        //не понял, как сделать через wait
        Thread.sleep(10000);
        String pathForPicture = webDriver.findElement(By.id("uploadPicture")).getAttribute("value");
        String[] array = pathForPicture.split("\\\\");
        String picture = array[array.length - 1];

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("currentAddress-wrapper")));
        webDriver.findElement(By.id("currentAddress")).sendKeys("Novosibirsk");
        String address = webDriver.findElement(By.id("currentAddress")).getAttribute("value");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("stateCity-wrapper")));
        webDriver.findElement(By.id("react-select-3-input")).sendKeys("NCR");
        webDriver.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
        String stateTitle = webDriver.findElement(By.xpath("//div[@id='state']//div[@class=' css-1uccc91-singleValue']")).getText();

        webDriver.findElement(By.id("react-select-4-input")).sendKeys("Delhi");
        webDriver.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);
        String cityTitle = webDriver.findElement(By.xpath("//div[@id='city']//div[@class=' css-1uccc91-singleValue']")).getText();

        String stateAndCity = stateTitle + " " + cityTitle;

        List<String> studentData = new ArrayList<>(10);
        studentData.add(studentName);
        studentData.add(studentEmail);
        studentData.add(gender);
        studentData.add(mobile);
        studentData.add(birthDate);
        studentData.add(subject);
        studentData.add(hobbies);
        studentData.add(picture);
        studentData.add(address);
        studentData.add(stateAndCity);

        //почему-то не получилось нажать на кнопку таким способом webDriver.findElement(By.id("submit")).click();
        webDriver.findElement(By.id("firstName")).sendKeys(Keys.RETURN);

        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='modal-body']//tbody//td[2]"));
        List<String> elementsTextValue = new ArrayList<>(10);

        for (WebElement e : elements) {
            elementsTextValue.add(e.getText());
        }

        String dateAfterSubmit = elementsTextValue.get(4);
        String[] dateParts = dateAfterSubmit.split(",");
        String yearAfterSubmit = dateParts[1];
        String[] dayAndMonth = dateParts[0].split(" ");
        String monthBeforeSubmit = dayAndMonth[1].substring(0, 3);
        String formatDateAfterSubmit = dayAndMonth[0] + " " + monthBeforeSubmit + " " + yearAfterSubmit;
        elementsTextValue.set(4, formatDateAfterSubmit);

        for (int i = 0; i < elementsTextValue.size(); ++i) {
            if (!elementsTextValue.get(i).equals(studentData.get(i))) {
                Assertions.fail("Данные заполнены неверно!");
            }
        }
    }
}
