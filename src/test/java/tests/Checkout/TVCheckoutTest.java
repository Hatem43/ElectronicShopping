package tests.Checkout;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart.MobileCart;
import pages.Cart.TvCart;
import pages.Checkout.LaptobCheckout;
import pages.Checkout.TVCheckout;
import pages.Home.HomePage;
import pages.Login.Loginpage;
import pages.products.Laptobproducts;
import pages.products.TVproducts;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class TVCheckoutTest extends BaseTest {

    public Loginpage login;
    public HomePage home;
    public TVproducts TV;
    public TvCart cart;
    public TVCheckout checkout;

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
        TV = new TVproducts(page);
        TV.gotoTvpage();
        TV.addTVtocart();
        TV.gotocart();
        cart = new TvCart(page);
        int cartproductprice=cart.getproductcartprice();
        cart.proceedtocheckout();
        checkout = new TVCheckout(page);
        int checkoutproductprice=checkout.checkproductcheckoutprice();
        Assert.assertEquals(checkoutproductprice,cartproductprice);
    }

    @Test
    public void CheckoutTest() {
        checkout.enterpaymentinfo("Hatem","Mahmed","said","hatem1999@gmail.com",Integer.parseInt("1234567890"),"11st hassan seliman cairo egypt","Egypt","Cairo",Integer.parseInt("1212112"));
        String message=checkout.placeorder();
        Assert.assertEquals(message,"Your order has been placed successfully.");
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
