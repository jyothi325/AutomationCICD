package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckOutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.OrdersPage;
import rahulshettyacademy.PageObjects.ProductCatalougePage;
import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	public String productName = "ZARA COAT 3";

	/*
	 * using Object data provider public void submitOrderTest(String email,String
	 * passwrd) throws InterruptedException, IOException { ProductCatalougePage
	 * productCatalouge = landingPage.loginApplication(email, passwrd);
	 */

	// using HashMap data provider
	@Test(dataProvider = "getData")
	public void submitOrderTest(HashMap<String, String> input) throws InterruptedException, IOException {
		ProductCatalougePage productCatalouge = landingPage.loginApplication(input.get("email"), input.get("passWord"));

		List<WebElement> products = productCatalouge.getProductList();

		productCatalouge.addProductToCart(productName);

		CartPage cartPage = productCatalouge.goToCartPage();

		Boolean match = cartPage.verifyProductDisply(productName);
		Assert.assertTrue(match);

		CheckOutPage checkoutpage = cartPage.goToCheckOut();
		checkoutpage.selectCountry("India");
		ConfirmationPage conform = checkoutpage.submitOrder();
		String confirmationMessage = conform.getConfirmationMessage();

		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * 
		 * js.executeScript("arguments[0].click();", submit);
		 * 
		 * return new ConfirmationPage(driver);
		 *
		 */

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = {"submitOrderTest"})
	public void orderHistoryTest() {
		ProductCatalougePage productCatalouge = landingPage.loginApplication("flowername@gmail.com", "Google#200");
		OrdersPage orderspage = productCatalouge.goToOrdersPage();
		Assert.assertTrue(orderspage.verifyOrderDisply(productName));

	}

	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"flowername@gmail.com","Google#200"},{"jasmine@gmail.com","King@000"}}; }
	 */

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(
				System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\Data\\purchaseorder.json");
		return new Object[][] {{data.get(0) }, {data.get(1)}};
	}

}

/*
 * HashMap<String,String> map =new HashMap<String,String>();
 *  map.put("email", "flowername@gmail.com"); 
 *  map.put("passWord", "Google#200");
 * 
 * HashMap<Object,Object> map1 =new HashMap<Object,Object>();
 *  map1.put("email","jasmine@gmail.com"); 
 *  map1.put("passWord", "King@000"); 
 * return new Object[][] {{map},{map1}};
 */
