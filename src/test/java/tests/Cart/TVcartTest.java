package tests.Cart;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart.TvCart;
import pages.Home.HomePage;
import pages.Login.Loginpage;
import pages.products.Laptobproducts;
import pages.products.TVproducts;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class TVcartTest extends BaseTest {
    public Loginpage login;
    public HomePage home;
    public TVproducts TV;
    public TvCart cart;

    @BeforeMethod
    public void beforeMethod(Method method) {
        login = new Loginpage(page);
        login.loginvalid(email, password);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
        home=new HomePage(page);
        home.checkhomepageurl();
        home.gotoelectoronicsshoppingpage();
        TV=new TVproducts(page);
        TV.gotoTvpage();
        cart = new TvCart(page);
    }

    @Test(priority=0)
    public void checkCartoneTV(){
        int productprice= TV.getproductprice();
        TV.addTVtocart();
        TV.gotocart();
        int cartproductprice=cart.getproductcartprice();
        Assert.assertEquals(cartproductprice,productprice);
        boolean actual=cart.checkcartlistoneTV();
        Assert.assertTrue(actual);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }

    @Test(priority = 1)
    public void checkaddsameTvtwotimestocart(){
        int productprice=TV.getproductprice();
        TV.addTvtwotimestocart();
        TV.gotocart();
        int actual=cart.addsameTVstwotimestimes();
        Assert.assertEquals(actual,2);
        int actaultotalprice=cart.gettotalproductcartprice();
        Assert.assertEquals(actaultotalprice,2*productprice);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }

    @Test(priority = 2)
    public void checkaddtwodifferentTVstocart(){
        TV.addtwodifferentTvstocart();
        TV.gotocart();
        boolean actual=cart.checkcarttwodifferentTVs();
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
