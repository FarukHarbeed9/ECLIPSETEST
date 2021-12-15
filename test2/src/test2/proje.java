package test2;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class proje {
	
	Actions action;
	public proje(WebDriver driver) {
		
		action = new Actions(driver);
	}
	
	public void scrollDown(WebDriver driver) {
		JavascriptExecutor executor= (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,2000)", "");
	}
	
	public void perforMouseMover(WebElement element) {
		
		action.moveToElement(element).build().perform();
	}
	public void clickUsingJavaScriptExecutor(WebElement element, WebDriver driver) {
		JavascriptExecutor executor= (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		
	}

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.Chrome.driver", "C:\\Users\\faroo\\OneDrive\\Desktop\\chromedriver_win32");
		WebDriver driver = new ChromeDriver();
		proje objSearch= new proje(driver);
		Actions action = new Actions(driver);
		

		
		driver.get("https://www.lcwaikiki.com/tr-TR/TR");
		driver.manage().window().maximize();
		
		WebElement username = driver.findElement(By.id("LoginEmail"));
		username.sendKeys("ffff@gmail.com");

		WebElement password = driver.findElement(By.id("Password"));
		password.sendKeys("Test123");

		WebElement loginnButton = driver.findElement(By.xpath("//*[@id=\"loginLink\"]"));
		loginnButton.click();
		
				//test login
		WebElement hop = driver.findElement(By.xpath("//*[@id=\"LoginEmail\"]"));
		String text = hop.getText();
		System.out.println(hop.getText());
		if (text.equals("asasdasdasdasdasdd")) {
			System.out.println("Login is correct" + hop.getText());

		/**	
		String at=driver.getTitle();
		String et="lcwaikiki";
		driver.close();
		if(at.equalsIgnoreCase(et)) {
			System.out.println("test success");
		} else {
			System.out.println("test fail");
		}
		*/
			
		//search pane 'pantolon'
		
			WebElement search = driver.findElement(By.xpath("//*[@id=\\\"search_input\\\"]"));
			// search.click();
			search.sendKeys("pantolon");
			
			Thread.sleep(1000);
			WebElement searchButton = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[3]/div/div/div/div[2]/a/span"));
			searchButton.click();
			Thread.sleep(1000);
			//scroldown 
			
			objSearch.scrollDown(driver);
			
			// daha fazla button

			WebElement  ShowMore =driver.findElement(By.xpath("//*[@id=\"divModels\"]/div[7]/div/div[4]/a/p"));
			objSearch.clickUsingJavaScriptExecutor(ShowMore, driver);
		
		
	
		
		// For selecting product
		String imagenum="1";
		WebElement SearchImage=driver.findElement(By.xpath("//*[@id=\"model_2260016_5311447\"]/div[1]/img[1]"));
		objSearch.perforMouseMover(SearchImage);
		
		WebElement productsUL = driver.findElement(By.xpath("[@id=\"divModels\"]/div[7]/div/div[1]"));
		List<WebElement> productList = productsUL.findElements(By.className("row c-items"));
		Random rand = new Random(productList.size());
		// select random product 

		int randomProductNumber = rand.nextInt(productList.size() - 1);
		WebElement luckyProduct = productList.get(randomProductNumber);
		System.out.println(productList.size());
		System.out.println(randomProductNumber);
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", luckyProduct);
		Thread.sleep(1000);
		//luckyProduct.click();
		String price = luckyProduct.findElement(By.className("raw-price")).getText();
		System.out.println(price);
		// add tp basket 
        
        action.moveToElement(luckyProduct);
		action.clickAndHold().perform();
		Thread.sleep(1000);
		WebElement addToBasketButton = luckyProduct.findElement(By.xpath("//*[@id=\\\"pd_add_to_cart\\\"]"));
		addToBasketButton.click();
		//
		
		WebElement basketProduct = driver.findElement(By.className("cart-items"));
		String basketPrice = basketProduct.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[1]/div[5]")).getText();
		System.out.println(basketPrice);
		Thread.sleep(1000);
		WebElement selectBox = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]"));
		selectBox.click();
		Thread.sleep(1000);
		WebElement amount = selectBox.findElement(By.xpath("//*[@id=\"cartalertblock\"]"));
		amount.click();
		System.out.println(selectBox.getAttribute("value"));
		Thread.sleep(1000);
		//btn-delete
		WebElement deleteButton = basketProduct.findElement(By.xpath("//*[@id=\"Cart_ProductDelete_733767961\"]/i"));
		deleteButton.click();

		WebElement deleteButton2 = basketProduct.findElement(By.xpath("[@id=\"Cart_ProductDelete_733767961\"]"));
		deleteButton2.click();
		
		Thread.sleep(2000);
		//check if deleted or not
		List<WebElement> basketProductsList = driver.findElements(By.xpath("/html/body/div[3]/div[2]/div[1]/div[4]/div/div[4]/a/i"));
		System.out.println(basketProductsList.size());
		if(basketProductsList.size()==0) {
			System.out.println("product deleted");


	}

}
	}
}
