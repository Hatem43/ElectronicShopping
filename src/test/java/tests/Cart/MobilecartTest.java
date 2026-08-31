package tests.Cart;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart.MobileCart;
import pages.Home.HomePage;
import pages.Login.Loginpage;
import pages.products.mobileproducts;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class MobilecartTest extends BaseTest {
    public Loginpage login;
    public HomePage home;
    public mobileproducts mobile;
    public MobileCart cart;

    @BeforeMethod
    public void beforeMethod(Method method) {
        login = new Loginpage(page);
        login.loginvalid(email, password);
        extent = ExtentManager.getExtent();
        test = extent.createTest(method.getName());
        home=new HomePage(page);
        home.checkhomepageurl();
        home.gotoelectoronicsshoppingpage();
        mobile = new mobileproducts(page);
        mobile.gotomobilespage();
        cart = new MobileCart(page);
    }

    @Test(priority=0)
    public void checkaddonemobiletoCart(){
        int productprice=mobile.getproductprice();
        mobile.addmobiletocart();
        mobile.gotocart();
        int cartproductprice=cart.getproductcartprice();
        Assert.assertEquals(cartproductprice,productprice);
        boolean actual=cart.checkcartlistonemobile();
        Assert.assertTrue(actual);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }



    @Test(priority = 1)
    public void checkaddsamemobiletwotimestocart(){
        int productprice=mobile.getproductprice();
        mobile.addsamemobilestwotimestocart();
        mobile.gotocart();
        int cartproductprice=cart.getproductcartprice();
        Assert.assertEquals(cartproductprice,productprice);
        int actual=cart.checkcartlisttwosamemobiles();
        Assert.assertEquals(actual,2);
        int actaultotalprice=cart.gettotalproductcartprice();
        Assert.assertEquals(actaultotalprice,300);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }

    @Test(priority = 2)
    public void checkaddtwodifferentmobiletocart(){
        mobile.addtwodifferentmobilestocart();
        mobile.gotocart();
        boolean actual=cart.checkcarttwodifferentmobiles();
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
