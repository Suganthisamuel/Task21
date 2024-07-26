import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWindowHandles {
	private WebDriver driver;
	private String originalWindow;
	
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	public void navigateToUrl(String url) {
		driver.get(url);
		
	}
	public void ClickLinkByText(String LinkText) {
		WebElement link = driver.findElement(By.linkText(LinkText));
		link.click();
		
	}
	public void switchToNewWindow() {
		originalWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String windowHandle : allWindows) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
	}
	public boolean isTextPresent(String tagName, String text) {
		WebElement element = driver.findElement(By.tagName(tagName));
		return element.getText().equals(text);
	}
	public void closeCurrentWindow() {
		driver.close();
	}
	public void switchToOriginalWindow() {
		driver.switchTo().window(originalWindow);
	}
	
    public boolean isOriginalWindowActive() {
    	return driver.getWindowHandle().equals(originalWindow);
    	
    }
  
	public static void main(String[] args) {
		TestWindowHandles test = new TestWindowHandles();
		
		try {
			test.setup();
			test.navigateToUrl("https://the-internet.herokuapp.com/windows");
			test.ClickLinkByText("Click Here");
			test.switchToNewWindow();
			
			if(test.isTextPresent("h3", "New Window")) {
				System.out.println("Text 'New window' is present on the new window page");
			}else{
				System.out.println("Text 'New Window'is not present on the new window page");
				
			}
			test.closeCurrentWindow();
			test.switchToOriginalWindow();
			
			if(test.isOriginalWindowActive()) {
				System.out.println("Original window is active");
			}else {
				System.out.println("Original window is not active");
			}
		}finally {
			test.tearDown();
		}
	}
}
	


