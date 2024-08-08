import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class NestedFrame {
    public static void main(String[] args) {
        //Disable Notifications and launch WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);

        //Maximize the Window
        driver.manage().window().maximize();

        //Open webpage
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        //Adding implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Switching to top frame
        WebElement TopFrame= driver.findElement(By.xpath("//frame[@src='/frame_top']"));
        driver.switchTo().frame(TopFrame);

        // Verify that there are three frames on the page
        int frameCount = driver.findElements(By.cssSelector("frame")).size();
        if (frameCount == 3)
        {
            System.out.println("Number of frames on the page: " + frameCount);
        } else
        {
            System.out.println("Unexpected number of frames on the page!");
        }

        //Switch to left frame
        WebElement leftFrame= driver.findElement(By.xpath("//frame[@src='/frame_left']"));
        driver.switchTo().frame(leftFrame);

        //Verifying left frame has text "LEFT"
        String leftFrameText = driver.findElement(By.cssSelector("body")).getText();
        if (leftFrameText.contains("LEFT"))
        {
            System.out.println("Left frame contains the text 'LEFT'");
        } else
        {
            System.out.println("Left frame does not contain the text 'LEFT'");
        }

        //Switching to top frame
        driver.switchTo().parentFrame();

        //Switching to Middle frame
        WebElement middleFrame= driver.findElement(By.xpath("//frame[@src='/frame_middle']"));
        driver.switchTo().frame(middleFrame);

        //Verifying Middle frame has text "MIDDLE"
        String middleFrameText = driver.findElement(By.xpath("//div[@id='content']")).getText();
        if (middleFrameText.contains("MIDDLE"))
        {
            System.out.println("Middle frame contains the text 'MIDDLE'");
        } else
        {
            System.out.println("Middle frame does not contain the text 'MIDDLE'");
        }

        //Switch to Top frame
        driver.switchTo().parentFrame();

        //Switching to Right frame
        WebElement RightFrame= driver.findElement(By.xpath("//frame[@src='/frame_right']"));
        driver.switchTo().frame(RightFrame);

        //Verifying Right frame has text "RIGHT"
        String RightFrameText = driver.findElement(By.cssSelector("body")).getText();
        if (RightFrameText.contains("RIGHT"))
        {
            System.out.println("Right frame contains the text 'RIGHT'");
        } else
        {
            System.out.println("Right frame does not contain the text 'RIGHT'");
        }

        //Switch to Top Frame
        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();

        //Switch to Bottom Frame
        driver.switchTo().frame("frame-bottom");
        //WebElement BottomFrame = driver.findElement(By.xpath("//frame[@src='/frame_bottom']"));
        //driver.switchTo().frame(BottomFrame);

        //Verifying Bottom Frame has text "BOTTOM
        String BottomFrameText = driver.findElement(By.cssSelector("body")).getText();
        if (BottomFrameText.contains("BOTTOM"))
        {
            System.out.println("Bottom frame contains the text 'BOTTOM'");
        }
        else
        {
            System.out.println("Bottom frame does not contain the text 'BOTTOM'");
        }

        //Switch to Top Frame
        driver.switchTo().parentFrame();

        /*
       //Verify the Page Title
        String titleName=driver.getTitle();
        System.out.println(titleName);
        if(titleName.equals("Frames"))
        {
            System.out.println("The Title Name of the Page: "+titleName);
        }
        else
        {
            System.out.println("The Required page not reached");
        }
        //There is no title for the page hence I commended the above coding*/
        // Verifying with Url
        String url="https://the-internet.herokuapp.com/nested_frames";
        String VerifyURL= driver.getCurrentUrl();
        if(VerifyURL.equals(url))
        {
            System.out.println("Reached on the Correct Page");
        }
        else
            System.out.println("Reached on the Wrong Page");

        //quite driver
        driver.quit();
    }
}
