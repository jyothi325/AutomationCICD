package rahulshettyacademy.PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver){
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	

	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();		
		waitForElementToAppear(results);
		selectCountry.click();	
	
	}
	
	
	public ConfirmationPage submitOrder() {
		Actions a = new Actions(driver);
		a.moveToElement(placeOrder).click().build().perform();
		return new ConfirmationPage(driver);
	}

}
