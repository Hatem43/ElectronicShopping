package tests.products;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home.HomePage;
import pages.Login.Loginpage;
import pages.products.mobileproducts;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class MobilesTest extends BaseTest {

    public Loginpage login;
    public HomePage home;
    public mobileproducts mobile;

    @BeforeMethod
    public void beforeMethod(Method method) {
        login=new Loginpage(page);
        login.loginvalid(email, password);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
        home = new HomePage(page);
        home.checkhomepageurl();
        home.gotoelectoronicsshoppingpage();
        mobile = new mobileproducts(page);
        mobile.gotomobilespage();
    }

    @Test
    public void mobileProductscountTest(){
        int actual_count=mobile.viewmobileproductscount();
        Assert.assertEquals(actual_count,5);
    }

    @Test
    public void mobiledetails(){
        mobile.viewmobileproductsdeatils();
    }

    @Test
    public void checkmobilesearching(){
        boolean actualreult=mobile.searchMobileproduct("Samsung Mobile");
        Assert.assertTrue(actualreult);
    }

    @Test
    public void checkmobilesortingfromAtoZ(){
        boolean issorted=mobile.sortmobilesfromAtoZ();
        Assert.assertTrue(issorted);
    }

     @Test
    public void checkmobilesortingfromZtoA(){
        boolean issorted=mobile.sortmobilesfromZtoA();
        Assert.assertTrue(issorted);
    }

    @Test
    public void checkmobilesortingfromLowtoHigh(){
        boolean issorted=mobile.sortMobilesFromLowToHigh();
        Assert.assertTrue(issorted);
    }


    @Test
    public void checkmobilesortingfromHightoLow(){
        boolean issorted=mobile.sortMobilesFromHighToLow();
        Assert.assertTrue(issorted);
    }



    @Test
    public void checkmobilefilteration(){
        boolean isfiltered=mobile.filterbyprice();
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
