import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowSwitching {
    public static void main(String[] args) throws InterruptedException {
        //Disable Notifications and launch WebDriver
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);

        //Maximize the Window
        driver.manage().window().maximize();

        //Navigate webpage
        driver.navigate().to("https://the-internet.herokuapp.com/windows");

        //Adding implicit wait----> to hold the window page for demo
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Preserve the parent window's handle
        String parentWindowHandle = driver.getWindowHandle();

        //Click to create new window
        WebElement clickButton= driver.findElement(By.xpath("//a[@href='/windows/new']"));
        clickButton.click();

        //Switch to new window---->Using Set & List function to get the Index access
        Set<String> allWindows=driver.getWindowHandles();
        List<String> WindowStrings = new ArrayList<String >(allWindows);
        String newWindow = WindowStrings.get(1);
        String OrginalWindow=WindowStrings.get(0);
        driver.switchTo().window(newWindow);

        //Verifying the new window presence
        String newWindowPageTitle= driver.getTitle();
        if(newWindowPageTitle.equals("New Window"))
        {
            System.out.println("New Window is present");
        }
        else
            System.out.println("New Window is not present");

        //Sleep method used----> just for Demoing new window
        Thread.sleep(3000);

        //Close the current window
        driver.close();

        //Switch to ParentWindow
        driver.switchTo().window(OrginalWindow);

        //Verifying the Parent window presence
        String ParentWindowTitle= driver.getTitle();

        if(ParentWindowTitle.equals("The Internet"))
        {
            System.out.println("Parent Window is active");
        }
        else
            System.out.println("Parent Window is not active");

        //Sleep method used----> just for demoing active window
        Thread.sleep(3000);

        //Close the Driver
        driver.quit();
    }
}
