package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenSiteTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Alex/Desktop/driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testSearchFlights() throws InterruptedException {
        // CT01: Pesquisar voos disponíveis
        // Given
        driver.get("https://phptravels.net/");

        // When
        WebElement flightsLink = driver.findElement(By.linkText("Flights"));
        flightsLink.click();
        WebElement fromInput = driver.findElement(By.name("BRA - Barreiras Airport - Barreiras"));
        fromInput.sendKeys("São Paulo");
        WebElement toInput = driver.findElement(By.name("BAL - Batman - Batman"));
        toInput.sendKeys("Nova York");
        WebElement departInput = driver.findElement(By.name("departure"));
        departInput.sendKeys("01/01/2023");
        WebElement returnInput = driver.findElement(By.name("return"));
        returnInput.sendKeys("10/01/2023");
        WebElement searchButton = driver.findElement(By.cssSelector(".btn-primary"));
        searchButton.click();

        // Then
        WebElement searchResults = driver.findElement(By.cssSelector(".booking-list"));
        assert searchResults.isDisplayed();
    }

    @Test
    public void testSelectFlights() throws InterruptedException {
        // CT02: Selecionar um voo de ida e um voo de volta
        // Given
        // Assume the search is already performed and on the flight results page

        // When
        WebElement selectButton = driver.findElement(By.cssSelector(".btn-select"));
        selectButton.click();
        WebElement continueButton = driver.findElement(By.cssSelector(".btn-continue"));
        continueButton.click();

        // Then
        WebElement flightDetails = driver.findElement(By.cssSelector(".flight-details"));
        assert flightDetails.isDisplayed();
    }

    @Test
    public void testFillPassengerInformation() throws InterruptedException {
        // CT03: Preencher informações de passageiros corretamente
        // Given
        // Assume the flights are already selected and on the passenger information page

        // When
        WebElement firstNameInput = driver.findElement(By.name("alexandre"));
        firstNameInput.sendKeys("John");
        WebElement lastNameInput = driver.findElement(By.name("pereira"));
        lastNameInput.sendKeys("Doe");
        // ... preencher outras informações de passageiros ...
        WebElement confirmPassengersButton = driver.findElement(By.cssSelector(".btn-continue"));
        confirmPassengersButton.click();

        // Then
        WebElement validationErrors = driver.findElement(By.cssSelector(".validation-errors"));
        assert !validationErrors.isDisplayed();
    }

    @Test
    public void testFillPaymentInformation() throws InterruptedException {
        // CT04: Preencher informações de pagamento corretamente
        // Given
        // Assume the passenger information is already filled and on the payment information page

        // When
        WebElement cardNumberInput = driver.findElement(By.name("alexandre"));
        cardNumberInput.sendKeys("1234567890123419");
        // ... preencher outras informações de pagamento ...
        WebElement confirmPaymentButton = driver.findElement(By.cssSelector(".btn-continue"));
        confirmPaymentButton.click();

        // Then
        WebElement paymentErrors = driver.findElement(By.cssSelector(".payment-errors"));
        assert !paymentErrors.isDisplayed();
    }

    @Test
    public void testConfirmFlightReservation() throws InterruptedException {
        // CT05: Confirmar reserva de voo
        // Given
        // Assume the payment information is already filled and on the confirmation page

        // When
        WebElement confirmReservationButton = driver.findElement(By.cssSelector(".btn-confirm"));
        confirmReservationButton.click();

        // Then
        WebElement reservationDetails = driver.findElement(By.cssSelector(".reservation-details"));
        WebElement reservationNumber = driver.findElement(By.cssSelector(".reservation-number"));
        assert reservationDetails.isDisplayed();
        assert reservationNumber.getText().length() > 0;
    }

  @After
   public void tearDown() throws Exception {
      driver.quit();
   }
}
