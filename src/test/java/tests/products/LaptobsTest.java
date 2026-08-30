package tests.products;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.Home.HomePage;
import pages.products.Laptobproducts;
import pages.Login.Loginpage;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class LaptobsTest extends BaseTest {
    public Loginpage login;
    public HomePage home;
    public Laptobproducts laptob;


    @BeforeMethod
    public void beforeMethod(Method method) {
        page.navigate("https://shop.qaautomationlabs.com/index.php");
        login = new Loginpage(page);
        login.loginvalid(email, password);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
        home=new HomePage(page);
        home = new HomePage(page);
        home.checkhomepageurl();
        home.gotoelectoronicsshoppingpage();
        laptob=new Laptobproducts(page);
        laptob.gotolaptobspage();
    }

    @Test
    public void checklaptobscount(){
        int actualcount=laptob.viewlaptobscount();
        Assert.assertEquals(actualcount,5);
    }

    @Test
    public void checklaptobsdetails() {
        laptob.viewlaptobproductsdetails();
    }

    @Test
    public void checklaptobsearch() {
        boolean actualresult=laptob.searchLaptobproduct("Samsung Laptop");
        Assert.assertTrue(actualresult);
    }

    @Test
    public void checklaptobsortingfromAtoZ(){
        boolean issorted=laptob.sortlaptobsfromAtoZ();
        Assert.assertTrue(issorted);
    }

    @Test
    public void checkproductsortingfromZtoA(){
        boolean issorted=laptob.sortlaptobsfroZtoA();
        Assert.assertTrue(issorted);
    }

    @Test
    public void checklaptobsortingfromLowtoHigh(){
        boolean issorted=laptob.sortLaptopsFromLowToHigh();
        Assert.assertTrue(issorted);
    }

    @Test
    public void checklaptobsortingfromHightoLow(){
        boolean issorted=laptob.sortLaptopsFromHighToLow();
        Assert.assertTrue(issorted);
    }


    @Test
    public void cehcklaptobsfilteration(){
        boolean isfiltered=laptob.filterbyprice();
        Assert.assertTrue(isfiltered);
    }

    @AfterMethod
    public void logout(ITestResult result) {
        login.logout();
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
