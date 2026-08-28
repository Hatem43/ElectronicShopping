package pages.products;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Laptobproducts extends Loginpage {

    int laptobsproducts_size;
    boolean sorted;
    boolean checkresult;
    boolean isfiltered;
    String labtopdetails = "//div[@class='text-center py-4']";
    String labtopproduct="//div[@class='col-lg-4 col-md-4 col-sm-6 pb-1']";
    String laptobname = "//a[@class='h6 text-decoration-none text-truncate']";
    String laptobprice = "//div[@class='d-flex align-items-center justify-content-center mt-2']";
    String laptobslist="//div[@id='product-list']";

    public Laptobproducts(Page page) {
        super(page);
    }

    public void gotolaptobspage(){
        Locator laptob=page.locator("label").filter(new Locator.FilterOptions().setHasText("Laptop"));
        laptob.check();
    }
    public int viewlaptobscount() {
        Locator laptobproducts = page.locator(laptobslist);
        boolean is_visible = laptobproducts.isVisible();
        if (is_visible) {
            Locator laptob = laptobproducts.locator(labtopproduct);
            laptobsproducts_size = laptob.count();
        }
        return laptobsproducts_size;
    }

    public void viewlaptobproductsdetails() {
        Locator laptobproducts = page.locator(laptobslist);
        if (laptobproducts.all().isEmpty()) {
            System.out.println("no laptobs found");
        }
        else {
            System.out.println("The avialable Laptobs are \n");
            Locator laptob=laptobproducts.locator(labtopproduct);
            Locator laptobproducts_details = laptob.locator(labtopdetails);
            for (Locator laptob_details : laptobproducts_details.all()) {
                System.out.println("the Laptob name  is "+laptob_details.locator(laptobname).innerText());
                System.out.println("the price of "+laptob_details.locator(laptobname).innerText()+" "+Integer.parseInt(laptob_details.locator(laptobprice).innerText().replaceAll("[^0-9]", "")));
                System.out.println("the Laptob model is "+laptob_details.locator(laptobname).innerText()+" "+laptob_details.locator("small").filter(new Locator.FilterOptions().setHasText("Model:")).innerText());
            }
        }
    }

    public boolean searchLaptobproduct(String productname) {
        Locator search = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Search products"));
        search.fill(productname);

        Locator laptobproducts = page.locator(laptobslist);
        if (laptobproducts.all().isEmpty()) {
            System.out.println("no Laptobs found");
        }

        else {
            Locator resultedlaptob = page.locator(labtopproduct);
            checkresult = resultedlaptob.isVisible();
            if (checkresult) {
                Locator productdname = resultedlaptob.locator(laptobname);
                System.out.println("the Laptob details is \n");
                System.out.println("the Laptob name is " + productdname.innerText());
                Locator productprice = resultedlaptob.locator(laptobprice);
                System.out.println("the Laptob price is " + productprice.innerText().replaceAll("[^0-9]", ""));
            }
        }
        return checkresult;
    }

    public boolean sortlaptobsfromAtoZ() {
        Locator laptobs_list = page.locator(laptobslist);
        boolean isvisbile = laptobs_list.isVisible();

        if (isvisbile) {
            Locator beforesortmobilesproducts = page.locator(laptobname);
            List<String> beforesortmobileproductsList = new ArrayList();
            for (Locator beforesortmobileproduct : beforesortmobilesproducts.all()) {
                beforesortmobileproductsList.add(beforesortmobileproduct.innerText());
            }
            System.out.println("beforesortmobileproductsList is " + beforesortmobileproductsList);
            Locator sortAtoZ = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort products"));
            sortAtoZ.selectOption("Name: A to Z");

            Locator aftersortmobilesproducts = page.locator(laptobname);
            List<String> aftersortmobileproductsList = new ArrayList();
            for (Locator aftersortmobileproduct : aftersortmobilesproducts.all()) {
                aftersortmobileproductsList.add(aftersortmobileproduct.innerText());
            }
            Collections.sort(beforesortmobileproductsList);
            System.out.println("aftersortmobileproductsList is " + aftersortmobileproductsList);
            sorted = beforesortmobileproductsList.equals(aftersortmobileproductsList);
        }

        return sorted;
    }

    public boolean sortlaptobsfroZtoA() {
        Locator laptobs_list = page.locator(laptobslist);
        boolean isvisbile = laptobs_list.isVisible();

        if (isvisbile) {
            Locator beforesortlaptoproducts = page.locator(laptobname);
            List<String> beforesortlaptobproductsList = new ArrayList();
            for (Locator beforesortlaptobproduct : beforesortlaptoproducts.all()) {
                beforesortlaptobproductsList.add(beforesortlaptobproduct.innerText());
            }

            System.out.println("beforesortlaptobsList is " + beforesortlaptobproductsList);
            Locator sortAtoZ = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort products"));
            sortAtoZ.selectOption("Name: Z to A");

            Locator aftersortlaptobproducts = page.locator(laptobname);
            List<String> aftersortlaptobproductsList = new ArrayList();
            for (Locator aftersortlaptobproduct : aftersortlaptobproducts.all()) {
                aftersortlaptobproductsList.add(aftersortlaptobproduct.innerText());
            }

            Collections.sort(beforesortlaptobproductsList,Collections.reverseOrder());
            System.out.println("aftersortlaptobsList is " + aftersortlaptobproductsList);
            sorted = beforesortlaptobproductsList.equals(aftersortlaptobproductsList);
        }
        return sorted;
    }

    public boolean sortLaptopsFromLowToHigh() {

        Locator laptobs_list = page.locator(laptobslist);
        boolean isvisbile = laptobs_list.isVisible();

        if (isvisbile) {

            Locator beforeSortLaptops = page.locator(laptobprice);

            List<Integer> beforeSortLaptopsList = new ArrayList<>();

            for (Locator beforeSortLaptop : beforeSortLaptops.all()) {

                int price = Integer.parseInt(beforeSortLaptop.innerText().replace("$", "").trim());
                beforeSortLaptopsList.add(price);
            }

            System.out.println("beforesortlaptobsList is " + beforeSortLaptopsList);

            Locator sortLowToHigh = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort products"));
            sortLowToHigh.selectOption("Price: Low to High");

            Locator afterSortLaptops = page.locator(laptobprice);
            List<Integer> afterSortLaptopsList = new ArrayList<>();

            for (Locator afterSortLaptop : afterSortLaptops.all()) {
                int price = Integer.parseInt(afterSortLaptop.innerText().replace("$", "").trim());
                afterSortLaptopsList.add(price);
            }

            Collections.sort(beforeSortLaptopsList);
            System.out.println("aftersortlaptobsList is "+afterSortLaptopsList);
            sorted = beforeSortLaptopsList.equals(afterSortLaptopsList);
        }
        return sorted;
    }

    public boolean sortLaptopsFromHighToLow() {

        Locator laptobs_list = page.locator(laptobslist);
        boolean isvisbile = laptobs_list.isVisible();

        if (isvisbile) {

            Locator beforeSortLaptops = page.locator(laptobprice);
            List<Integer> beforeSortLaptopsList = new ArrayList<>();

            for (Locator beforeSortLaptop : beforeSortLaptops.all()) {
                int price = Integer.parseInt(beforeSortLaptop.innerText().replace("$", "").trim());
                beforeSortLaptopsList.add(price);
            }

            System.out.println("beforesortlaptobsList is " + beforeSortLaptopsList);

            Locator sortLowToHigh = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort products"));
            sortLowToHigh.selectOption("Price: High to Low");

            Locator afterSortLaptops = page.locator(laptobprice);
            List<Integer> afterSortLaptopsList = new ArrayList<>();

            for (Locator afterSortLaptop : afterSortLaptops.all()) {
                int price = Integer.parseInt(afterSortLaptop.innerText().replace("$", "").trim());
                afterSortLaptopsList.add(price);
            }

            Collections.sort(beforeSortLaptopsList,Collections.reverseOrder());
            System.out.println("aftersortlaptobsList is "+afterSortLaptopsList);
            sorted = beforeSortLaptopsList.equals(afterSortLaptopsList);
        }
        return sorted;
    }

    public void addLaptobtocart(){

        Locator addlaptobtocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung Laptop to cart"));
        addlaptobtocart.click();
    }

    public void addLaptobtwotimestocart(){

        Locator addlaptobtocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung Laptop to cart"));
        addlaptobtocart.dblclick();
    }

    public void addtwodifferentlaptobstocart(){
        Locator addlaptob1tocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung Laptop to cart"));
        addlaptob1tocart.click();
        Locator addmlaptob2tocart=page.locator("//button[@title='Add LG Laptop to cart']");
        addmlaptob2tocart.click();
    }

    public void gotocart(){
        Locator cartbutton=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Cart"));
        cartbutton.click();
    }

    public boolean filterbyprice(){
        Locator filterbutton=page.locator("//label[normalize-space()='$0 - $100']");
        filterbutton.check();

        Locator resultedlist=page.locator(laptobslist);
        if(resultedlist.all().isEmpty()){
            System.out.println("no laptobs found");
        }

        else {
            Locator resultedlaptob=resultedlist.locator(labtopproduct);
            Locator resultedlaptobdetails=resultedlaptob.locator(labtopdetails);
            Locator resultedmobileprice=resultedlaptobdetails.locator(laptobprice);
            int price=Integer.parseInt(resultedmobileprice.innerText().replace("$", "").trim());
            if(price>0 && price<=100){
                isfiltered=true;
                Locator laptob_name=resultedlaptobdetails.locator(laptobname);
                System.out.println("the laptob name "+ laptob_name.innerText());
                System.out.println("the laptob price "+ Integer.parseInt(resultedmobileprice.innerText().replace("$", "").trim()));
                Locator laptob_model=resultedlaptobdetails.locator("small").filter(new Locator.FilterOptions().setHasText("Model:"));;
                System.out.println("the mobile model of "+laptob_name.innerText()+" "+laptob_model.innerText());
            }
            else {
                isfiltered=false;
                System.out.println("laptobs are not filtered");
            }
        }
        return isfiltered;
    }
    public int getproductprice(){
        Locator productprice=page.getByText("$99", new Page.GetByTextOptions().setExact(true));
        int price=Integer.parseInt(productprice.innerText().replace("$", "").trim());
        return price;
    }
}
