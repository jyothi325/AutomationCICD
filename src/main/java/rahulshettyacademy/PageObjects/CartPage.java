package rahulshettyacademy.PageObjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver){		
		super(driver);			
		this.driver=driver;		
		PageFactory.initElements(driver,this);
	}
	
	
	//PageFactory
	
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> productTitles;
	
	public boolean verifyProductDisply(String productname) {
		
		boolean match = productTitles.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return match;
		
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
	
	
}
