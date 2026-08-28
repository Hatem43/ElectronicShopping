package pages.Home;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;

public class HomePage extends Loginpage {

    public HomePage(Page page) {
        super(page);
    }

    public String checkhomepageurl(){
        String currenturl=page.url();
        return currenturl;
    }
    public String gotoelectoronicsshoppingpage(){
        Locator shop=page.getByTestId("breadcrumb-current");
        shop.click();
        Locator electornics=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Shop Electronics"));
        electornics.click();
        String url=page.url();
        return url;
    }
}
