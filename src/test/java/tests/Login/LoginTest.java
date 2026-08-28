package tests.Login;
import Base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Login.Loginpage;
import utils.ExtentManager;
import java.lang.reflect.Method;


public class LoginTest extends BaseTest {

    public Loginpage login;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void beforeMethod(Method method) {
        page.navigate("https://shop.qaautomationlabs.com/index.php");
        login=new Loginpage(page);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
    }

    @Test
    public void validloginTest(){
        login.loginvalid(email,password);
    }

    @Test
    public void invalidloginTest(){
        login.logininvalid("ssaas@gess.com","sasaas");
    }

@Test
public void remebermetest(){
        boolean checked =login.checkremeber();
        Assert.assertTrue(checked);
}
   @Test
    public void viewpasswordtest() {
       boolean is_visible = login.viewpassord(password);
       Assert.assertTrue(is_visible);
   }
    @AfterMethod
    public void logout(ITestResult result) {
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
