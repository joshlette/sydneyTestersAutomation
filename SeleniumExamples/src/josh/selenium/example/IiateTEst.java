package josh.selenium.example;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class IiateTEst {
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
    	//WebDriver driver = new HtmlUnitDriver();
    	
        // Go to the Google Suggest home page
        driver.get("http://www.iiate.asn.au");
        
        // Go to register + login page
        driver.findElement(By.linkText("Login/Register")).click();
        
        Thread.sleep(2000);
        
        //Check content header text
        WebElement ContentHeader1 = driver.findElement(By.xpath(".//*[@id='mainContent']/div/div[1]/div/h2"));
        assertEquals("Register with IIATE", ContentHeader1.getText());
        
        //Check failed login
        	//Identify elements
        WebElement LoginUsernameField = driver.findElement(By.name("username"));
        WebElement LoginPasswordField = driver.findElement(By.name("password"));
        WebElement LoginSumbit = driver.findElement(By.xpath("//fieldset/input[3]"));
        	//Submit Failure
        LoginUsernameField.sendKeys("badUser");
        LoginPasswordField.sendKeys("password");
        LoginSumbit.click();
        
        	//Wait for page to refresh       
        long timeForLoginFailure = System.currentTimeMillis() + 1000;
        while (System.currentTimeMillis() < timeForLoginFailure) {
        	WebElement ErrorMessage = driver.findElement(By.xpath(".//*[@id='mainContent']/div/div[2]/p"));

            // If results have been returned, the results are displayed in a drop down.
            if (ErrorMessage.isDisplayed()) {
              break;
            }
        }
        
        	//Assert Error Message
        WebElement ErrorMessage = driver.findElement(By.xpath(".//*[@id='mainContent']/div/div[2]/p"));
        assertEquals("Incorrect username or password", ErrorMessage.getText());

        
        //Search for something
        	//Identify elements
        //WebElement SearchBox = driver.findElement(By.name("search"));
        WebElement SearchBox = driver.findElement(By.xpath(".//*[@id='mainTop']/div/div/div[2]/div/form/input[1]"));
        SearchBox.sendKeys("misson");
        
        Thread.sleep(5000); 
        SearchBox.sendKeys(Keys.RETURN);
        
        //Wait for page to load
        long timeForResultsPage = System.currentTimeMillis() + 1000;
        while (System.currentTimeMillis() < timeForResultsPage) {
        	WebElement SearchResultTitle = driver.findElement(By.xpath(".//*[@id='mainContent']/div/div[1]/div/h2/a/span"));

            // If results have been returned, the results are displayed in a drop down.
            if (SearchResultTitle.isDisplayed()) {
              break;
            }
        }
        
        //Assert Results
        //TODO
      
        // Close browser
        driver.quit();

	}
}
