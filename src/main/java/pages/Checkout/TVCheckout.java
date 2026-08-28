package pages.Checkout;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;

public class TVCheckout extends Loginpage {

    public TVCheckout(Page page) {
        super(page);
    }
    public int checkproductcheckoutprice(){
        Locator checkoutprice=page.getByTestId("order-total");
        int price=Integer.parseInt(checkoutprice.innerText().replaceAll("[^0-9]", ""));
        return price;
    }

    public void enterpaymentinfo(String Firstname, String Middlename, String Lastname,String Email,int mobbilephone,String Adderss,String State,String city, int pincode) {
        Locator first_name=page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("First Name*"));
        first_name.fill(Firstname);
        Locator Middle_name=page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Middle Name"));
        Middle_name.fill(Middlename);
        Locator Last_name=page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Last Name*"));
        Last_name.fill(Lastname);
        Locator user_email=page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("E-mail*"));
        user_email.fill(Email);
        Locator phone_number=page.getByRole(AriaRole.SPINBUTTON,new Page.GetByRoleOptions().setName("Mobile No.*"));
        phone_number.fill(String.valueOf(mobbilephone));
        Locator user_address=page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Address*"));
        user_address.fill(Adderss);
        Locator user_State=page.getByLabel("State*", new Page.GetByLabelOptions().setExact(true));
        user_State.fill(State);
        Locator user_city=page.getByLabel("City*", new Page.GetByLabelOptions().setExact(true));
        user_city.fill(city);
        Locator user_pincode=page.getByLabel("Pin Code*", new Page.GetByLabelOptions().setExact(true));
        user_pincode.fill(String.valueOf(pincode));
    }

    public String placeorder(){
        Locator submit=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Continue"));
        submit.click();
        Locator placeorder=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Place Order"));
        placeorder.click();
        Locator successmessage=page.getByText("Your order has been placed successfully.", new Page.GetByTextOptions().setExact(true));
        String message=successmessage.innerText();
        return message;
    }
}
