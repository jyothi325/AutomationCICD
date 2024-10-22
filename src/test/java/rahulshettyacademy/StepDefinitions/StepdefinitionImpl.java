package rahulshettyacademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckOutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalougePage;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepdefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalougePage productCatalouge;
	public ConfirmationPage conform;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String name,String password) 
	{
		productCatalouge = landingPage.loginApplication(name, password);

	}
	
	 @When("^I add product (.+) to Cart$")
	 public void I_add_product_to_Cart(String product) throws InterruptedException 
	 {
		 List<WebElement> products = productCatalouge.getProductList();	
		 
		 productCatalouge.addProductToCart(product);
	
	 }
	 
	 @And("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_the_order(String productName) throws InterruptedException
	 {
		 CartPage cartPage = productCatalouge.goToCartPage();
		 Boolean match = cartPage.verifyProductDisply(productName);
		 Assert.assertTrue(match);

			CheckOutPage checkoutpage = cartPage.goToCheckOut();
			checkoutpage.selectCountry("India");
			conform = checkoutpage.submitOrder();
	 }
	 @Then("{string} message is displayed on ConfirmationPage.")
	 public void message_is_displayed_on_ConformationPage(String string)
	 {
		 	String confirmationMessage = conform.getConfirmationMessage();
			Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
			driver.close();
	 }
	 
	 @Then("{string} message is displayed")
	 	public void message_is_displayed(String string)
	 	{
		 Assert.assertEquals(string, landingPage.getErrorMessage());
		 driver.close();
	 	}
}
