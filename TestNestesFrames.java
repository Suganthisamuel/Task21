import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNestesFrames {
	private static WebDriver driver;

    public void setUp() {
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

    public void switchToFrameByNameOrId(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToFrameByElement(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public boolean isTextPresent(String text) {
        return driver.getPageSource().contains(text);
    }
    public boolean isTitlePresent(String title) {
        return driver.getTitle().equals(title);
    }

	public static void main(String[] args) {
		TestNestesFrames test = new TestNestesFrames();

        try {
            test.setUp();
            test.navigateToUrl("http://the-internet.herokuapp.com/nested_frames");

            
            test.switchToFrameByNameOrId("frame-top");

            
            List<WebElement> frames = driver.findElements(By.tagName("frame"));
            if (frames.size() == 3) {
                System.out.println("There are three frames on the top frame.");
            } else {
                System.out.println("There are not three frames on the top frame.");
            }

            
            test.switchToFrameByNameOrId("frame-left");
            if (test.isTextPresent("LEFT")) {
                System.out.println("Left frame contains text 'LEFT'.");
            } else {
                System.out.println("Left frame does not contain text 'LEFT'.");
            }

           
            test.switchToDefaultContent();
            test.switchToFrameByNameOrId("frame-top");

         
            test.switchToFrameByNameOrId("frame-middle");

            
            if (test.isTextPresent("MIDDLE")) {
                System.out.println("Middle frame contains text 'MIDDLE'.");
            } else {
                System.out.println("Middle frame does not contain text 'MIDDLE'.");
            }
            test.switchToDefaultContent();
            test.switchToFrameByNameOrId("frame-top");

            
            test.switchToFrameByNameOrId("frame-right");

            
            if (test.isTextPresent("RIGHT")) {
                System.out.println("Right frame contains text 'RIGHT'.");
            } else {
                System.out.println("Right frame does not contain text 'RIGHT'.");
            }

            
            test.switchToDefaultContent();
            test.switchToFrameByNameOrId("frame-top");

            
            test.switchToDefaultContent();
            test.switchToFrameByNameOrId("frame-bottom");
            if (test.isTextPresent("BOTTOM")) {
                System.out.println("Bottom frame contains text 'BOTTOM'.");
            } else {
                System.out.println("Bottom frame does not contain text 'BOTTOM'.");
            }

            
            test.switchToDefaultContent();

           
            if (test.isTitlePresent("Frames")) {
                System.out.println("Page title is 'Frames'.");
            } else {
                System.out.println("Page title is not 'Frames'.");
            }
        } finally {
            test.tearDown();
        }

	}

}
