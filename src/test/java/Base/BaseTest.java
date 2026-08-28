package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileReader;
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
    public String Mobilephone;
    public String address;
    public String city;
    public String state;
    public String pincode;


    @BeforeSuite
    public void setup() throws IOException, ParseException {
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
        FileReader f=new FileReader("C:\\Users\\l e n o v o\\IdeaProjects\\Electronicsstore\\src\\test\\resources\\info.json");
        JSONParser j=new JSONParser();
        Object o=j.parse(f);
        JSONObject Info=(JSONObject) o;
        email=(String)Info.get("email");
        password=(String)Info.get("password");

        FileReader mobileinfo=new FileReader("C:\\Users\\l e n o v o\\IdeaProjects\\Electronicsstore\\src\\test\\resources\\Mobilepaymentinfo.json");
        JSONParser jp=new JSONParser();
        Object ob=jp.parse(mobileinfo);
        JSONObject payinfo=(JSONObject) ob;
        FirstName=(String)payinfo.get("name");
        MiddleName=(String)payinfo.get("middlename");
        lastName=(String)payinfo.get("lastname");
        Email=(String)payinfo.get("Email");
        Mobilephone=(String)payinfo.get("MobilePhone");
        address=(String)payinfo.get("address");
        city=(String)payinfo.get("city");
        state=(String)payinfo.get("State");
        pincode=(String)payinfo.get("pincode");

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
