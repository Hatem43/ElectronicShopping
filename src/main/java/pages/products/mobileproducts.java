package pages.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mobileproducts extends Loginpage {

    public int mobileproducts_size;
    String mobileproduct="//div[@class='col-lg-4 col-md-4 col-sm-6 pb-1']";
    String mobilesdetails="//div[@class='text-center py-4']";
    String Mobilename = "//a[@class='h6 text-decoration-none text-truncate']";
    String Mobileprice="//div[@class='d-flex align-items-center justify-content-center mt-2']";
    String mobileslist="//div[@id='product-list']";
    boolean sorted;
    boolean isfiltered;
    boolean checkresult;


    public mobileproducts(Page page) {
        super(page);
    }

    public void gotomobilespage(){
        Locator mobile=page.locator("label").filter(new Locator.FilterOptions().setHasText("Mobile"));
        mobile.check();
    }
    public int viewmobileproductscount(){
        Locator mobile_list= page.locator(mobileslist);
        boolean isvisbile=mobile_list.isVisible();
        if(isvisbile){
            Locator mobile=mobile_list.locator(mobileproduct);
             mobileproducts_size= mobile.count();
        }
        return mobileproducts_size;
    }

    public void viewmobileproductsdeatils() {
        Locator mobileproducts = page.locator(mobileslist);
        if (mobileproducts.all().isEmpty()) {
            System.out.println("no mobiles found");
        }
        else {
            System.out.println("The avialable Mobiles are \n");
            Locator mobile=mobileproducts.locator(mobileproduct);
            Locator mobilesproducts_details = mobile.locator(mobilesdetails);
            for (Locator mobile_details : mobilesproducts_details.all()) {
                System.out.println("the mobile name is "+mobile_details.locator(Mobilename).innerText());
                System.out.println("the price of "+mobile_details.locator(Mobilename).innerText()+" "+Integer.parseInt(mobile_details.locator(Mobileprice).innerText().replaceAll("[^0-9]", "")));
                System.out.println("the mobile model is "+mobile_details.locator(Mobilename).innerText()+" "+mobile_details.locator("small").filter(new Locator.FilterOptions().setHasText("Model:")).innerText());
            }
        }
    }

    public boolean searchMobileproduct(String productname) {
        Locator search = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Search products"));
        search.fill(productname);
        Locator mobileproducts = page.locator(mobileslist);
        if (mobileproducts.all().isEmpty()) {
            System.out.println("no mobiles found");
        }
        else {
            Locator resultedmobile = mobileproducts.locator(mobileproduct);
            checkresult = resultedmobile.isVisible();
            if(checkresult) {
                Locator productdetails = resultedmobile.locator(mobilesdetails);
                System.out.println("the mobile details is \n");
                System.out.println("the mobile name is " + productdetails.locator(Mobilename).innerText());
                Locator productprice = productdetails.locator(Mobileprice);
                System.out.println("the mobile price is " + productprice.innerText().replaceAll("[^0-9]", ""));
            }
        }
        return checkresult;
    }

    public boolean sortmobilesfromAtoZ(){
        Locator mobile_list= page.locator(mobileslist);
        boolean isvisbile=mobile_list.isVisible();

        if(isvisbile){
            Locator beforesortmobilesproducts = page.locator(Mobilename);
            List<String> beforesortmobileproductsList=new ArrayList();

            for(Locator beforesortmobileproduct:beforesortmobilesproducts.all()) {
                beforesortmobileproductsList.add(beforesortmobileproduct.innerText());
            }
            System.out.println("beforesortmobilesList is " + beforesortmobileproductsList);
            Locator sortAtoZ=page.getByRole(AriaRole.COMBOBOX,new Page.GetByRoleOptions().setName("Sort products"));
            sortAtoZ.selectOption("Name: A to Z");

            Locator aftersortmobilesproducts = page.locator(Mobilename);
            List<String> aftersortmobileproductsList=new ArrayList();

            for (Locator aftersortmobileproduct:aftersortmobilesproducts.all()) {
                aftersortmobileproductsList.add(aftersortmobileproduct.innerText());
            }
            Collections.sort(beforesortmobileproductsList);
            System.out.println("aftersortmobilesList is "+aftersortmobileproductsList);
            sorted=beforesortmobileproductsList.equals(aftersortmobileproductsList);
        }

        return sorted;
    }

    public boolean sortmobilesfromZtoA(){

        Locator mobile_list= page.locator(mobileslist);
        boolean isvisbile=mobile_list.isVisible();

        if(isvisbile){
            Locator beforesortmobilesproducts = page.locator(Mobilename);
            List<String> beforesortmobileproductsList=new ArrayList();
            for(Locator beforesortmobileproduct:beforesortmobilesproducts.all()) {
                beforesortmobileproductsList.add(beforesortmobileproduct.innerText());
            }
            System.out.println("beforesortmobilesList is " + beforesortmobileproductsList);
            Locator sortAtoZ=page.getByRole(AriaRole.COMBOBOX,new Page.GetByRoleOptions().setName("Sort products"));
            sortAtoZ.selectOption("Name: Z to A");

            Locator aftersortmobilesproducts = page.locator(Mobilename);
            List<String> aftersortmobileproductsList=new ArrayList();
            for (Locator aftersortmobileproduct:aftersortmobilesproducts.all()) {
                aftersortmobileproductsList.add(aftersortmobileproduct.innerText());
            }
            Collections.sort(beforesortmobileproductsList,Collections.reverseOrder());
            System.out.println("aftersortmobilesList is "+aftersortmobileproductsList);
            sorted=beforesortmobileproductsList.equals(aftersortmobileproductsList);
        }

        return sorted;
    }

    public boolean sortMobilesFromLowToHigh() {

        Locator mobile_list= page.locator(mobileslist);
        boolean isvisbile=mobile_list.isVisible();

        if (isvisbile) {

            Locator beforeSortMobiles = page.locator(Mobileprice);
            List<Integer> beforeSortMobilesList = new ArrayList<>();

            for (Locator beforeSortMobile : beforeSortMobiles.all()) {

                int price = Integer.parseInt(beforeSortMobile.innerText().replace("$", "").trim());
                beforeSortMobilesList.add(price);
            }

            System.out.println("beforesortmobilesList is " + beforeSortMobilesList);

            Locator sortLowToHigh = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort products"));
            sortLowToHigh.selectOption("Price: Low to High");

            Locator afterSortMobiles = page.locator(Mobileprice);
            List<Integer> afterSortMobilesList = new ArrayList<>();

            for (Locator afterSortMobile : afterSortMobiles.all()) {
                int price = Integer.parseInt(afterSortMobile.innerText().replace("$", "").trim());
                afterSortMobilesList.add(price);
            }

            Collections.sort(beforeSortMobilesList);
            System.out.println("aftersortmobilesList is "+afterSortMobilesList);
            sorted = beforeSortMobilesList.equals(afterSortMobilesList);
        }
        return sorted;
    }

    public boolean sortMobilesFromHighToLow() {

        Locator mobile_list= page.locator(mobileslist);
        boolean isvisbile=mobile_list.isVisible();

        if (isvisbile) {

            Locator beforeSortMobiles = page.locator(Mobileprice);
            List<Integer> beforeSortMobilesList = new ArrayList<>();

            for (Locator beforeSortMobile : beforeSortMobiles.all()) {

                int price = Integer.parseInt(beforeSortMobile.innerText().replace("$", "").trim());
                beforeSortMobilesList.add(price);
            }

            System.out.println("beforesortmobilesList is " + beforeSortMobilesList);

            Locator sortLowToHigh = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort products"));
            sortLowToHigh.selectOption("Price: High to Low");

            Locator afterSortMobiles = page.locator(Mobileprice);
            List<Integer> afterSortMobilesList = new ArrayList<>();

            for (Locator afterSortMobile : afterSortMobiles.all()) {
                int price = Integer.parseInt(afterSortMobile.innerText().replace("$", "").trim());
                afterSortMobilesList.add(price);
            }

            Collections.sort(beforeSortMobilesList,Collections.reverseOrder());
            System.out.println("aftersortmobilesList is "+afterSortMobilesList);
            sorted = beforeSortMobilesList.equals(afterSortMobilesList);
        }
        return sorted;
    }


    public void addmobiletocart(){

        Locator addtocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung Mobile to cart"));
        addtocart.click();
    }

    public void addsamemobilestwotimestocart(){

        Locator addmobiletocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung Mobile to cart"));
        addmobiletocart.dblclick();
    }

    public void addtwodifferentmobilestocart(){
        Locator addmobile1tocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung Mobile to cart"));
        addmobile1tocart.click();
        Locator addmobile2tocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add LG Mobile to cart"));
        addmobile2tocart.click();
    }

    public void gotocart(){
        Locator cartbutton=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Cart"));
        cartbutton.click();
    }

    public boolean filterbyprice(){
        Locator filterbutton=page.locator("//label[normalize-space()='$0 - $100']");
        filterbutton.check();

        Locator resultedlist=page.locator(mobileslist);
        if(resultedlist.all().isEmpty()){
            System.out.println("no mobiles found");
        }

        else {
            Locator resultedmobile=resultedlist.locator(mobileproduct);
            Locator resultedmobiledetails=resultedmobile.locator(mobilesdetails);
            Locator resultedmobileprice=resultedmobiledetails.locator(Mobileprice);
            int price=Integer.parseInt(resultedmobileprice.innerText().replace("$", "").trim());
            if(price>0 && price<=100){
                isfiltered=true;
                Locator mobile_name=resultedmobiledetails.locator(Mobilename);
                System.out.println("the mobile name "+ mobile_name.innerText());
                System.out.println("the mobile price "+ Integer.parseInt(resultedmobileprice.innerText().replace("$", "").trim()));
                Locator mobile_model=resultedmobiledetails.locator("small").filter(new Locator.FilterOptions().setHasText("Model:"));;
                System.out.println("the mobile model of "+mobile_name.innerText()+" "+mobile_model.innerText());

            }
            else {
                isfiltered=false;
                System.out.println("mobiles are not filtered");
            }
        }
        return isfiltered;
    }
    public int getproductprice(){
        Locator productprice=page.getByText("$150", new Page.GetByTextOptions().setExact(true));
        int price=Integer.parseInt(productprice.innerText().replace("$", "").trim());
        return price;
    }
}