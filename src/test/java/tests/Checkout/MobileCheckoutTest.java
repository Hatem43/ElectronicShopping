package tests.Checkout;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart.MobileCart;
import pages.Checkout.MobileCheckout;
import pages.Home.HomePage;
import pages.Login.Loginpage;
import pages.products.mobileproducts;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class MobileCheckoutTest extends BaseTest {

    public Loginpage login;
    public HomePage home;
    public mobileproducts mobile;
    public MobileCart cart;
    public MobileCheckout checkout;

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
        mobile = new mobileproducts(page);
        mobile.gotomobilespage();
        mobile.addmobiletocart();
        mobile.gotocart();
        cart = new MobileCart(page);
        int productcartprice=cart.getproductcartprice();
        cart.proceedtocheckout();
        checkout = new MobileCheckout(page);
        int checkoutprice=checkout.checkproductcheckoutorice();
        Assert.assertEquals(checkoutprice,productcartprice);
    }

    @Test
    public void CheckoutTest() {
        checkout.enterpaymentinfo(FirstName,MiddleName,lastName,Email,Integer.parseInt(Mobilephone),address,state,city,Integer.parseInt(pincode));
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
