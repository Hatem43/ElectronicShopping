package tests.Home;

import Base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home.HomePage;
import pages.Login.Loginpage;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class HomeTest extends BaseTest {
    protected ExtentReports extent;
    protected ExtentTest test;
    Loginpage loginpage;
    HomePage homepage;

    @BeforeMethod
    public void beforeMethod(Method method) {
        loginpage=new Loginpage(page);
        loginpage.loginvalid(email,password);
        homepage=new HomePage(page);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
    }

    @Test
    public void checkhomeTest(){
        String current_url=homepage.checkhomepageurl();
        Assert.assertEquals(current_url,"https://shop.qaautomationlabs.com/shop.php");
    }

    @Test
    public void checkelectornicsTest(){
        String electronicurl=homepage.gotoelectoronicsshoppingpage();
        Assert.assertEquals(electronicurl,"https://shop.qaautomationlabs.com/electronics.php","https://shop.qaautomationlabs.com/electronics.php");
    }
    @AfterMethod
    public void logout(ITestResult result) {
        loginpage.logout();
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else {
            test.skip("Test Skipped");
        }
        extent.flush();
    }

}
