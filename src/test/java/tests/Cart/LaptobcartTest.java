package tests.Cart;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart.LaptopCart;
import pages.Home.HomePage;
import pages.Login.Loginpage;
import pages.products.Laptobproducts;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class LaptobcartTest extends BaseTest {
    public Loginpage login;
    public HomePage home;
    public Laptobproducts laptob;
    public LaptopCart cart;


    @BeforeMethod
    public void beforeMethod(Method method) {
        login = new Loginpage(page);
        login.loginvalid(email, password);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
        home=new HomePage(page);
        home.checkhomepageurl();
        home.gotoelectoronicsshoppingpage();
        laptob=new Laptobproducts(page);
        laptob.gotolaptobspage();
        cart = new LaptopCart(page);
    }

    @Test(priority=0)
    public void checkaddonelabtobtoCart(){
        laptob.addLaptobtocart();
        laptob.gotocart();
        boolean actual=cart.checkcartlistonelaptob();
        Assert.assertTrue(actual);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }


    @Test(priority = 1)
    public void checkaddsamelaptobtwotimestocart(){
        int productprice=laptob.getproductprice();
        laptob.addLaptobtwotimestocart();
        laptob.gotocart();
        int actual=cart.addsamelaptobstwotimes();
        Assert.assertEquals(actual,2);
        int actaultotalprice=cart.gettotalproductcartprice();
        Assert.assertEquals(actaultotalprice,2*productprice);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }

    @Test(priority = 2)
    public void checkaddcartwodifferentlaptobstocart(){
        laptob.addtwodifferentlaptobstocart();
        laptob.gotocart();
        boolean actual=cart.checkcarttwodifferentlaptobs();
        Assert.assertTrue(actual);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
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
