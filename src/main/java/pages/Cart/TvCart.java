package pages.Cart;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;

public class TvCart extends Loginpage {


    String Tvscartlist="//table[@id='cartTable']/tbody/tr";
    String TVname="[data-testid^='cart-item-name-']";
    String TVprice="[data-testid^='cart-item-price-']";
    String TVtotalprice="[data-testid^='cart-item-total-']";
    String Tvqunatity="input.qty";;
    boolean isempty;
    int quantity=0;
    String message="//td[@class='align-middle text-center']";
    String emptymessag;
    boolean found;


    public TvCart(Page page) {
        super(page);
    }

    public boolean checkcartlistoneTV(){
        Locator cartist=page.locator(Tvscartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No TV in cart");
        }

        else {
            found=true;
            for(Locator TV:cartist.all()){
                Locator TV_name=TV.locator(TVname);
                System.out.println("the TV name is "+ TV_name.innerText());
                Locator TV_price=TV.locator(TVprice);
                System.out.println("the TV price is "+Integer.parseInt(TV_price.innerText().replaceAll("[^0-9]", "")));
                Locator TV_quantity=TV.locator(Tvqunatity);
                System.out.println("the TV quantity is "+TV_quantity.inputValue());
                Locator TVtotal_price = TV.locator(TVtotalprice);
                System.out.println("the TV total price is "+Integer.parseInt(TVtotal_price.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return found;
    }

    public int checkcartlisttwosameTVs(){
        Locator cartist=page.locator(Tvscartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No TVs in cart");
        }
        else {
            found=true;
            for(Locator TV:cartist.all()){
                Locator TV_name=TV.locator(TVname);
                System.out.println("the TV name is "+ TV_name.innerText());
                Locator TV_price=TV.locator(TVprice);
                System.out.println("the TV price is "+Integer.parseInt(TV_price.innerText().replaceAll("[^0-9]", "")));
                Locator TV_quantity=TV.locator(Tvqunatity);
                quantity=Integer.parseInt(TV_quantity.inputValue());
                System.out.println("the TV quantity is "+quantity);
                Locator TVtotal_price = TV.locator(TVtotalprice);
                System.out.println("the TV total price is "+Integer.parseInt(TVtotal_price.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return quantity;
    }

    public boolean checkcarttwodifferentTVs(){
        Locator cartist=page.locator(Tvscartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No TVs in cart");
        }

        else{
            found=true;
            for(int i=0;i<cartist.count();i++){
                Locator TV=cartist.nth(i);
                Locator TV_name=TV.locator(TVname);
                System.out.println("the TV name is "+ TV_name.innerText());
                Locator TV_price=TV.locator(TVprice);
                System.out.println("the TV price is "+Integer.parseInt(TV_price.innerText().replaceAll("[^0-9]", "")));
                Locator TV_quantity=TV.locator(Tvqunatity);
                System.out.println("the TV quantity is "+TV_quantity.inputValue());
                Locator TVtotal_price = TV.locator(TVtotalprice);
                System.out.println("the TV total price is "+Integer.parseInt(TVtotal_price.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return found;
    }

    public boolean emptyproductscart() {
        Locator cartist = page.locator(Tvscartlist);
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

    public void proceedtocheckout(){
        Locator checkout=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Proceed To Checkout"));
        checkout.click();

    }

    public int getproductcartprice(){
        Locator productcartprice=page.locator(TVprice);
        int cartprice=Integer.parseInt(productcartprice.innerText().replaceAll("[^0-9]", ""));
        return cartprice;
    }

    public int gettotalproductcartprice(){
        Locator productcartprice=page.locator(TVtotalprice);
        int totalcartprice=Integer.parseInt(productcartprice.innerText().replaceAll("[^0-9]", ""));
        return totalcartprice;
    }

}
