package pages.Login;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class Loginpage {

    public Page page;

    public Loginpage(Page page) {
        this.page = page;
    }

    public void loginvalid(String email, String password) {
        Locator user_email = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"));
        user_email.fill(email);
        Locator user_password = page.locator("//input[@id='password']");
        user_password.fill(password);
        Locator log=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Login"));
        log.click();
    }

    public String logininvalid(String invalidemail, String invalidpassword) {
        Locator inavliduser_email = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"));
        inavliduser_email.fill(invalidemail);
        Locator user_password = page.locator("//input[@id='password']");
        user_password.fill(invalidpassword);
        Locator log=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Login"));
        log.click();
        Locator errormessage=page.getByText("Invalid email or password!", new Page.GetByTextOptions().setExact(true));
        String message=errormessage.innerText();
        return message;
    }

    public boolean viewpassord(String password) {
        Locator user_password = page.locator("//input[@id='password']");
        user_password.fill(password);
        Locator viewbutton = page.locator("//i[@class='fas fa-eye']");
        viewbutton.click();
        boolean pass = user_password.isVisible();
        return pass;
    }

    public boolean checkremeber() {
        Locator remeberme = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Remember me"));
        remeberme.check();
        boolean remeber = remeberme.isChecked();
        return remeber;
    }

    public void logout(){
      Locator signout=page.locator("//i[@class='fas fa-sign-out-alt']");
      signout.click();
    }
}