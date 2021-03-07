// The abstract parent test class, use: to centralize logic that is the same for all test classes to avoid double code
// We can make this class abstract because we never need to instantiate it to an object
// We can make the fields protected, because they only need to be accessed by inheriting classes

// While we instantiate our WebDriver in the @Before method for simplicity, common practice for this would be to create
// a WebDriver factory, which you can then use to make the type of WebDriver you launch and its settings
// responsive to properties and/or environment variables.

package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public abstract class ParentTest {

   protected static WebDriver driver;
    protected static String baseUrl = "http://localhost/opleidingen/2018-19";

    @Before
    public void setupDriver() {
        // Remember to update the path in below statement to the location where you have saved your chromedriver.exe file
        // Other webdrivers work exactly the same (or some at least very similar)

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        // You can set certain properties for your browser while instantiating your webdriver (eg. maximize window,
        // set specific window size, disable gpu, run headless, ...)
        // For Chrome you can use ChromeOptions, add the required options and pass them in to the ChromeDriver constructor
        // as shown below:
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.addArguments("--start-maximized");
        cOptions.addArguments("--headless"); // -> Uncomment this statement to run the test without showing the browser
        driver = new ChromeDriver(cOptions);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

