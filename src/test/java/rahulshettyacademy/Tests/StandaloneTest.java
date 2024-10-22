package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.LandingPage;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";
		
		
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("flowername@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Google#200");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-star-inserted:last-of-type")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".la-ball-scale-multiple"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartproducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		
		boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		  driver.findElement(By.cssSelector(".totalRow button")).click();

		    Actions a = new Actions(driver);

		    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		    
		    WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		   
		    a.moveToElement(submit).click().build().perform();
		    
		    
		    

		   // JavascriptExecutor js = (JavascriptExecutor) driver;
		    // js.executeScript("window.scrollBy(0,200)");
		    //js.executeScript("arguments[0].click();", submit);

		    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		Thread.sleep(6000);
		
		driver.close();



	}

}
