package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class BaseTest {

    public Playwright playwright;
    public  Browser browser;
    public BrowserContext context;
    public  Page page;
    protected ExtentReports extent;
    protected ExtentTest test;
    public String email;
    public String password;

    public String FirstName;
    public String MiddleName;
    public String lastName;
    public String Email;
    public int Mobilephone;
    public String address;
    public String city;
    public String state;
    public int pincode;


    @BeforeSuite
    public void setup() throws IOException{
        playwright=Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1000)
                .setArgs(Arrays.asList(
                "--disable-features=PasswordLeakDetection",
                "--disable-save-password-bubble",
                "--start-maximized")));

        context=browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page=context.newPage();
        page.waitForTimeout(5000);
        page.navigate("https://shop.qaautomationlabs.com/index.php");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode info=mapper.readTree(new File("src/test/resources/info.json"));
        email=info.get("email").asText();
        password=info.get("password").asText();
    }

    @AfterSuite
    public void teardown(){
        if(browser!=null){
            browser.close();
        }

        if(playwright!=null){
            playwright.close();
        }
    }
}
