package tests.products;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home.HomePage;
import pages.products.Laptobproducts;
import pages.Login.Loginpage;
import pages.products.TVproducts;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class TVTest extends BaseTest {

    public Loginpage login;
    public HomePage home;
    public Laptobproducts laptob;
    public TVproducts Tv;

    @BeforeMethod
    public void beforeMethod(Method method) {
        login = new Loginpage(page);
        login.loginvalid(email, password);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
        home=new HomePage(page);
        home.checkhomepageurl();
        home.gotoelectoronicsshoppingpage();
        Tv = new TVproducts(page);
        Tv.gotoTvpage();
    }

    @Test
    public void checkTVscount(){
        int actualcount=Tv.viewTVproducts();
        Assert.assertEquals(actualcount,5);
    }

    @Test
    public void checkTVdetails(){
        Tv.viewTVproductdetails();
    }

    @Test
    public void checkTVsearching(){
        boolean actualresult=Tv.searchTvproduct("Samsung HD Smart TV");
        Assert.assertTrue(actualresult);

    }

    @Test
    public void checkTvssortingfromAtoZ(){
        Tv.sortTVSfromAtoZ();
    }

    @Test
    public void checkTvssortingfromLowtoHigh(){
        Tv.sortTvsFromLowToHigh();
    }

    @Test
    public void checkTVfilteration(){
        boolean isfilterd=Tv.filterbyprice();
        Assert.assertTrue(isfilterd);
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
