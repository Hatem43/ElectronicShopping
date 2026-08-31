package pages.Cart;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;

import java.util.List;

public class MobileCart extends Loginpage {

    String mobilescartlist="//table[@id='cartTable']/tbody/tr";
    String mobilename="[data-testid^='cart-item-name-']";
    String mobileprice="[data-testid^='cart-item-price-']";
    String mobiletotalprice="[data-testid^='cart-item-total-']";
    String mobilequnatity="input.qty";;
    boolean isempty;
    int quantity=0;
    String message="//td[@class='align-middle text-center']";
    boolean found;

    public MobileCart(Page page) {
        super(page);
    }

      public boolean checkcartlistonemobile(){
          Locator cartist=page.locator(mobilescartlist);
          if(cartist.all().isEmpty()){
              found=false;
              System.out.println("No Mobiles in cart");
          }

          else {
              found=true;
              for(Locator mobile:cartist.all()){
                  Locator mobile_name=mobile.locator(mobilename);
                  System.out.println("the mobile name is "+ mobile_name.innerText());
                  Locator mobile_price=mobile.locator(mobileprice);
                  System.out.println("the mobile price is "+Integer.parseInt(mobile_price.innerText().replaceAll("[^0-9]", "")));
                  Locator mobile_quantity=mobile.locator(mobilequnatity);
                  System.out.println("the mobile quantity is "+mobile_quantity.inputValue());
                  Locator mobiletotal_price = mobile.locator(mobiletotalprice);
                  System.out.println("the mobile total price is "+Integer.parseInt(mobiletotal_price.innerText().replaceAll("[^0-9]", "")));
              }
          }
          return found;
    }

    public int addsammobilestwotimes(){
        Locator cartist=page.locator(mobilescartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No Mobiles in cart");
        }
        else {
            found=true;
            for(Locator mobile:cartist.all()){
                Locator mobile_name=mobile.locator(mobilename);
                System.out.println("the mobile name is "+ mobile_name.innerText());
                Locator mobile_price=mobile.locator(mobileprice);
                System.out.println("the mobile price is "+Integer.parseInt(mobile_price.innerText().replaceAll("[^0-9]", "")));
                Locator mobile_quantity=mobile.locator(mobilequnatity);
                quantity=Integer.parseInt(mobile_quantity.inputValue());
                System.out.println("the mobile quantity is "+quantity);
                Locator mobiletotal_price = mobile.locator(mobiletotalprice);
                System.out.println("the mobile total price is "+Integer.parseInt(mobiletotal_price.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return quantity;
    }

    public boolean emptyproductscart() {
        Locator cartist = page.locator(mobilescartlist);
        if(cartist.count()>0) {
            Locator removeButtons = page.locator("[data-testid^='cart-remove-']");
            while (removeButtons.count() > 0) {
                removeButtons.first().click();
            }
            if (page.locator(message).innerText().equalsIgnoreCase("Your Cart is Empty")) {
                isempty = true;
            }
            else {
                isempty = false;
            }
        }
        return isempty;
    }


    public boolean checkcarttwodifferentmobiles(){
        Locator cartist=page.locator(mobilescartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No Mobiles in cart");
        }

        else{
             found=true;
             for(int i=0;i<cartist.count();i++){
                 Locator mobile=cartist.nth(i);
               Locator mobile_name=mobile.locator(mobilename);
               System.out.println("the mobile name is "+ mobile_name.innerText());
               Locator mobile_price=mobile.locator(mobileprice);
               System.out.println("the mobile price is "+Integer.parseInt(mobile_price.innerText().replaceAll("[^0-9]", "")));
               Locator mobile_quantity=mobile.locator(mobilequnatity);
               System.out.println("the mobile quantity is "+mobile_quantity.inputValue());
               Locator mobiletotal_price = mobile.locator(mobiletotalprice);
               System.out.println("the mobile total price is "+Integer.parseInt(mobiletotal_price.innerText().replaceAll("[^0-9]", "")));
             }
        }
        return found;
    }

    public void proceedtocheckout(){
        Locator checkout=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Proceed To Checkout"));
        checkout.click();
    }
    public int getproductcartprice(){
        Locator productcartprice=page.locator(mobileprice);
        int cartprice=Integer.parseInt(productcartprice.innerText().replaceAll("[^0-9]", ""));
        return cartprice;
    }

    public int gettotalproductcartprice(){
        Locator productcartprice=page.locator(mobiletotalprice);
        int totalcartprice=Integer.parseInt(productcartprice.innerText().replaceAll("[^0-9]", ""));
        return totalcartprice;
    }


}