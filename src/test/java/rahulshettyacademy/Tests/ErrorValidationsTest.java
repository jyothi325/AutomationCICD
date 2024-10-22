package rahulshettyacademy.Tests;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckOutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalougePage;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test
	public void loginErrorValidation() throws InterruptedException, IOException {
		landingPage.loginApplication("flowername@gmail.com", "Gooe#200");
		String err = landingPage.getErrorMessage();
		System.out.println(err);
		System.out.println(landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	@Test(retryAnalyzer=Retry.class)
	public void productErrorValidation() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";	
		ProductCatalougePage productCatalouge = landingPage.loginApplication("flowername@gmail.com", "Google#200");
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
		CartPage cartPage = productCatalouge.goToCartPage();
		boolean match = cartPage.verifyProductDisply("ZARA COAT 3");
		Assert.assertTrue(match);
		
	}
}