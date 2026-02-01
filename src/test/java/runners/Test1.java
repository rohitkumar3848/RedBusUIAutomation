package runners;

import base.DriverFactory;
import org.testng.annotations.Test;
import pages.SearchBusPage;
import pages.SelectBusPage;
import pages.SelectSeatPage;
import utils.NavigationUtil;
import org.testng.*;



public class Test1 {

    @Test
    public void redBusUi(){
        DriverFactory.initDriver();

        NavigationUtil.navigateToHome("redBus");

        SearchBusPage searchPage=new SearchBusPage();
        searchPage.enterFromDestination("Kolkata","Kolkata");
        searchPage.enterToDestination("Siliguri","Siliguri");
        searchPage.selectDate("15","February","2026");
        searchPage.searchBuses();

        SelectBusPage selectBusPage=new SelectBusPage();
        selectBusPage.selectBusByName("Lokenath Bus Service");

        SelectSeatPage selectSeatPage=new SelectSeatPage();
        boolean res=selectSeatPage.selectFirstAvailableSeat();
        Assert.assertTrue(res, "Seat was not selected successfully!");

        DriverFactory.quitDriver();
    }
}
