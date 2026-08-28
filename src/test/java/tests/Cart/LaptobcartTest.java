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
        cart = new LaptopCart(page);
    }

    @Test(priority=0)
    public void checkCartoneproduct(){
        laptob.addLaptobtocart();
        laptob.gotocart();
        boolean actual=cart.checkcartlistonelaptob();
        Assert.assertTrue(actual);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }

    @Test(priority = 1)
    public void checksameproductmultipletimestocart(){
        int productprice=laptob.getproductprice();
        laptob.addLaptobtwotimestocart();
        laptob.gotocart();
        int cartproductprice=cart.getproductcartprice();
        Assert.assertEquals(cartproductprice,productprice);
        int actual=cart.checkcartlisttwosamelabtops();
        Assert.assertEquals(actual,2);
        boolean actual_result=cart.emptyproductscart();
        Assert.assertTrue(actual_result);
    }


    @Test(priority = 2)
    public void checkcartwodifferentlaptobs(){
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
