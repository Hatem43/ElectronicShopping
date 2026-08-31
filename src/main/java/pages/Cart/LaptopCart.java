package pages.Cart;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;

public class LaptopCart extends Loginpage {

    String laptobsscartlist="//table[@id='cartTable']/tbody/tr";
    String laptobname="[data-testid^='cart-item-name-']";
    String laptobprice="[data-testid^='cart-item-price-']";
    String laptobtotalprice="[data-testid^='cart-item-total-']";
    String laptobqunatity="input.qty";;
    boolean isempty;
    int quantity=0;
    String message="//td[@class='align-middle text-center']";
    boolean found;

    public LaptopCart(Page page) {
        super(page);
    }


    public boolean checkcartlistonelaptob(){
        Locator cartist=page.locator(laptobsscartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No Laptobs in cart");
        }

        else {
            found=true;
            for(Locator laptob:cartist.all()){
                Locator laptob_name=laptob.locator(laptobname);
                System.out.println("the labtop name is "+ laptob_name.innerText());
                Locator laptob_price=laptob.locator(laptobprice);
                System.out.println("the labtop price is "+Integer.parseInt(laptob_price.innerText().replaceAll("[^0-9]", "")));
                Locator laptob_quantity=laptob.locator(laptobqunatity);
                System.out.println("the labtop quantity is "+laptob_quantity.inputValue());
                Locator laptobtotal_price = laptob.locator(laptobtotalprice);
                System.out.println("the labtop total price is "+Integer.parseInt(laptobtotal_price.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return found;
    }

    public int addsamelaptobstwotimes(){
        Locator cartist=page.locator(laptobsscartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No Laptobs in cart");
        }
        else {
            found=true;
            for(Locator laptob:cartist.all()){
                Locator laptob_name=laptob.locator(laptobname);
                System.out.println("the laptob name is "+ laptob_name.innerText());
                Locator laptob_price=laptob.locator(laptobprice);
                System.out.println("the laptob price is "+Integer.parseInt(laptob_price.innerText().replaceAll("[^0-9]", "")));
                Locator laptob_quantity=laptob.locator(laptobqunatity);
                quantity=Integer.parseInt(laptob_quantity.inputValue());
                System.out.println("the laptob quantity is "+quantity);
                Locator laptobtotal_price = laptob.locator(laptobtotalprice);
                System.out.println("the laptob total price is "+Integer.parseInt(laptobtotal_price.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return quantity;
    }

    public boolean emptyproductscart() {
        Locator cartist = page.locator(laptobsscartlist);
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

    public boolean checkcarttwodifferentlaptobs(){
        Locator cartist=page.locator(laptobsscartlist);
        if(cartist.all().isEmpty()){
            found=false;
            System.out.println("No Laptobs in cart");
        }

        else{
            found=true;
            for(int i=0;i<cartist.count();i++){
                Locator laptob=cartist.nth(i);
                Locator laptob_name=laptob.locator(laptobname);
                System.out.println("the laptob name is "+ laptob_name.innerText());
                Locator laptob_price=laptob.locator(laptobprice);
                System.out.println("the laptob price is "+Integer.parseInt(laptob_price.innerText().replaceAll("[^0-9]", "")));
                Locator laptob_quantity=laptob.locator(laptobqunatity);
                System.out.println("the laptob quantity is "+laptob_quantity.inputValue());
                Locator laptobtotal_price = laptob.locator(laptobtotalprice);
                System.out.println("the laptob total price is "+Integer.parseInt(laptobtotal_price.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return found;
    }

    public void proceedtocheckout(){
        Locator checkout=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Proceed To Checkout"));
        checkout.click();
    }

    public int getproductcartprice(){
        Locator productcartprice=page.locator(laptobprice);
        int cartprice=Integer.parseInt(productcartprice.innerText().replaceAll("[^0-9]", ""));
        return cartprice;
    }

    public int gettotalproductcartprice(){
        Locator productcartprice=page.locator(laptobtotalprice);
        int totalcartprice=Integer.parseInt(productcartprice.innerText().replaceAll("[^0-9]", ""));
        return totalcartprice;
    }


}
