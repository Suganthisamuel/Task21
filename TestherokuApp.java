import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class TestherokuApp {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\joesa\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.manage().window().maximize();
			
			driver.get("https://the-internet.herokuapp.com/iframe");
			
			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='mce_0_ifr']")));
			//driver.switchTo().frame(driver.findElement(By.cssSelector("#mce_0_ifr")));
			
			WebElement pTag = driver.findElement(By.xpath("//*[@id='tinymce']/p"));
			//WebElement pTag = driver.findElement(By.cssSelector("#tinymce/p"));
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			pTag.clear();
			pTag.sendKeys("Hello People..!!");
			
			driver.switchTo().defaultContent();
			
		
			
		}finally {
			driver.quit();
		}
	}

}
