package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class BlazeDemoAutomation {
    @Test
    public static void main(String[] args) {
        // Configura o caminho para o driver do Chrome
        System.setProperty("webdriver.chrome.driver", "C:/Users/Alex/Desktop/driver/chromedriver.exe");

        // Inicializa o driver do Selenium
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Acessa a página
        driver.get("https://www.blazedemo.com");

        // Seleciona a origem e destino do voo
        Select selectOrigin = new Select(driver.findElement(By.name("fromPort")));
        selectOrigin.selectByVisibleText("Boston");

        Select selectDestination = new Select(driver.findElement(By.name("toPort")));
        selectDestination.selectByVisibleText("New York");

        // Clica no botão para buscar os voos
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // Seleciona um voo aleatório
        int randomIndex = (int) (Math.random() * driver.findElements(By.cssSelector("table tbody tr")).size());
        driver.findElements(By.cssSelector("table tbody tr input[type='submit']")).get(randomIndex).click();

        // Preenche os dados do passageiro
        driver.findElement(By.id("inputName")).sendKeys("John Doe");
        driver.findElement(By.id("address")).sendKeys("123 Main Street");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("state")).sendKeys("NY");
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).sendKeys("1234567890");
        driver.findElement(By.id("nameOnCard")).sendKeys("John Doe");

        // Clica no botão para confirmar a compra
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // Aguarda até que a mensagem de agradecimento seja exibida
        WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Thank you for your purchase today!')]")));


    }
}