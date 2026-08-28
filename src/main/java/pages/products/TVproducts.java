package pages.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pages.Login.Loginpage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TVproducts extends Loginpage {

    int TVproducts_size;
    String TVpproduct="//div[@class='col-lg-4 col-md-4 col-sm-6 pb-1']";
    String TVsdetails="//div[@class='text-center py-4']";
    String TVname = "//a[@class='h6 text-decoration-none text-truncate']";
    String TVprice="//div[@class='d-flex align-items-center justify-content-center mt-2']";
    String TVlist="//div[@id='product-list']";
    boolean sorted;
    boolean isfiltered;
    boolean checkresult;

    public TVproducts(Page page) {
        super(page);
    }

    public void gotoTvpage(){
        Locator tv= page.locator("label").filter(new Locator.FilterOptions().setHasText("TV"));
        tv.check();
    }
    public int viewTVproducts(){
        Locator TV_list=page.locator(TVlist);
        boolean isvisbile=TV_list.isVisible();
        if (isvisbile){
            Locator TV=TV_list.locator(TVpproduct);
            TVproducts_size= TV.count();
        }
        return TVproducts_size;
    }
    public void viewTVproductdetails(){
        Locator Tvproducts = page.locator(TVlist);
        if (Tvproducts.all().isEmpty()) {
            System.out.println("no TVs found");
        }
        else {
            System.out.println("The avialable TVS are \n");
            Locator TV=Tvproducts.locator(TVpproduct);
            Locator TVproducts_details = TV.locator(TVsdetails);
            for (Locator tv_details : TVproducts_details.all()) {
                System.out.println("the TV name is "+tv_details.locator(TVname).innerText());
                System.out.println("the price of "+tv_details.locator(TVname).innerText()+" "+Integer.parseInt(tv_details.locator(TVprice).innerText().replaceAll("[^0-9]", "")));
                System.out.println("the TV model is "+tv_details.locator(TVname).innerText()+" "+tv_details.locator("small").filter(new Locator.FilterOptions().setHasText("Model:")).innerText());
            }
        }

    }

    public boolean searchTvproduct(String productname) {
        Locator search = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Search products"));
        search.fill(productname);
        Locator TVproducts = page.locator(TVlist);
        if (TVproducts.all().isEmpty()) {
            System.out.println("no mobiles found");
        }
        else {
            Locator resultedTV = page.locator(TVpproduct);
            checkresult = resultedTV.isVisible();
            if (checkresult) {
                Locator productdetails = resultedTV.locator(TVsdetails);
                System.out.println("the TV details are \n");
                System.out.println("the TV name is " + productdetails.locator(TVname).innerText());
                Locator productprice = productdetails.locator(TVprice);
                System.out.println("the TV price is " + Integer.parseInt(productprice.innerText().replaceAll("[^0-9]", "")));
            }
        }
        return checkresult;
    }

    public boolean sortTVSfromAtoZ(){
        Locator TV_list= page.locator(TVlist);
        boolean isvisbile=TV_list.isVisible();

        if(isvisbile){
            Locator beforesortTVproducts = page.locator(TVname);
            List<String> beforesortTVproductsList=new ArrayList();

            for(Locator beforesortTVproduct:beforesortTVproducts.all()) {
                beforesortTVproductsList.add(beforesortTVproduct.innerText());
            }

            System.out.println("beforesortTVList is " + beforesortTVproductsList);
            Locator sortAtoZ=page.getByRole(AriaRole.COMBOBOX,new Page.GetByRoleOptions().setName("Sort products"));
            sortAtoZ.selectOption("Name: A to Z");

            Locator aftersortTVsproducts = page.locator(TVname);
            List<String> aftersortTVproductsList=new ArrayList();

            for (Locator aftersortmobileproduct:aftersortTVsproducts.all()) {
                aftersortTVproductsList.add(aftersortmobileproduct.innerText());
            }

            Collections.sort(beforesortTVproductsList);
            System.out.println("aftersortTVList is "+aftersortTVproductsList);
            sorted=beforesortTVproductsList.equals(aftersortTVproductsList);
        }

        return sorted;
    }

    public boolean sortTvsFromLowToHigh() {

        Locator TV_list= page.locator(TVlist);
        boolean isvisbile=TV_list.isVisible();

        if (isvisbile) {

            Locator beforeSortTVS = page.locator(TVprice);
            List<Integer> beforeSortTvsList = new ArrayList<>();

            for (Locator beforeSortTV : beforeSortTVS.all()) {

                int price = Integer.parseInt(beforeSortTV.innerText().replace("$", "").trim());
                beforeSortTvsList.add(price);
            }

            System.out.println("beforesortmobilesList is " + beforeSortTvsList);

            Locator sortLowToHigh = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Sort products"));
            sortLowToHigh.selectOption("Price: Low to High");

            Locator afterSortTVs = page.locator(TVprice);
            List<Integer> afterSortTVSList = new ArrayList<>();

            for (Locator afterSortTV : afterSortTVs.all()) {
                int price = Integer.parseInt(afterSortTV.innerText().replace("$", "").trim());
                afterSortTVSList.add(price);
            }

            Collections.sort(beforeSortTvsList);
            System.out.println("aftersortmobilesList is "+afterSortTVSList);
            sorted = beforeSortTvsList.equals(afterSortTVSList);
        }
        return sorted;
    }

    public void addTVtocart(){

        Locator addlaptob1tocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung HD Smart TV to cart"));
        addlaptob1tocart.click();
    }

    public void addTvtwotimestocart(){

        Locator addlaptob1tocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung HD Smart TV to cart"));
        addlaptob1tocart.dblclick();
    }

    public void addtwodifferentTvstocart(){
        Locator addlaptob1tocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add Samsung HD Smart TV to cart"));
        addlaptob1tocart.click();
        Locator addmlaptob2tocart=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Add LG UHD AI TV to cart"));
        addmlaptob2tocart.click();
    }

    public void gotocart(){
        Locator cartbutton=page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Cart"));
        cartbutton.click();
    }

    public boolean filterbyprice(){
        Locator filterbutton=page.locator("//label[normalize-space()='$101 - $200']");
        filterbutton.check();

        Locator resultedlist=page.locator(TVlist);
        if(resultedlist.all().isEmpty()){
            System.out.println("no laptobs found");
        }

        else {
            Locator resultedTV=resultedlist.locator(TVpproduct);
            Locator resultedTVdetails=resultedTV.locator(TVsdetails);
            Locator resultedTVprice=resultedTVdetails.locator(TVprice);
            int price=Integer.parseInt(resultedTVprice.innerText().replace("$", "").trim());
            if(price>100 && price<=200){
                isfiltered=true;
                Locator TV_name=resultedTVdetails.locator(TVname);
                System.out.println("the TV name "+ TV_name.innerText());
                System.out.println("the Tv price "+ Integer.parseInt(resultedTVprice.innerText().replace("$", "").trim()));
                Locator TV_model=resultedTVdetails.locator("small").filter(new Locator.FilterOptions().setHasText("Model:"));;
                System.out.println("the TV model of "+TV_name.innerText()+" "+TV_model.innerText());
            }
            else {
                isfiltered=false;
                System.out.println("TVs are not filtered");
            }
        }
        return isfiltered;
    }
    public int getproductprice(){
        Locator productprice=page.getByText("$400", new Page.GetByTextOptions().setExact(true));
        int price=Integer.parseInt(productprice.innerText().replace("$", "").trim());
        return price;
    }

}
