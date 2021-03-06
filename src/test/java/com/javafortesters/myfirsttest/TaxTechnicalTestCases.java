package com.javafortesters.myfirsttest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TaxTechnicalTestCases {

    private WebDriver driver;
    private String url = "https://www.taxtechnical.ird.govt.nz/";


    @Before    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void testBreadcrumbAbout() {
        driver.findElement(By.xpath("//div[@class = 'tp-header__main-links container'] //a[@title='About']")).click();
        //driver.findElement(By.xpath("//div[@id=\"header\"]/div/div/div/header/div[1]/div[1]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//a[@title='Home']")).click();
        //driver.findElement(By.xpath("//div[@id=\"content\"]/div/div[1]/div/div[1]/div/div/a")).click();
        Assert.assertEquals("Check the breadcrumb in about","https://www.taxtechnical.ird.govt.nz/",driver.getCurrentUrl());
    }

    @Test
    public void externalLinkIRMain() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("/html//div[@id='header']/div[@class='row']/div//ul[@class='tp-text-links']//a[@title='IR main site']"));
        element.click();
    }

    @Test
    public void externalLinkTP() {
        WebElement element = driver.findElement(By.xpath("/html//div[@id='header']/div[@class='row']/div//ul[@class='tp-text-links']//a[@title='IR Tax Policy']"));
        element.click();
    }


    @Test
    public void testBreadcrumbConsult() {
        WebElement element = driver.findElement(By.xpath("/html//div[@id='header']//div[@role='menu']/div/div[3]/a[@href='/consultations']"));
        element.click();
        WebElement breadCrumbLocator = driver.findElement(By.xpath("/html//div[@id='content']/div[@class='row']/div[1]//a[@title='Home']"));
        breadCrumbLocator.click();
        Assert.assertEquals("Check breadcrumb","https://www.taxtechnical.ird.govt.nz/",driver.getCurrentUrl());
    }

    @Test
    public void searchBestMatchResult() {
        WebElement searchField = driver.findElement(By.xpath("//form[@id='search-form']//input[@name='tt-search-form__input']"));
        WebElement searchButton = driver.findElement(By.xpath("//form[@id='search-form']//button[@type='submit']"));
        searchField.sendKeys("My Tax");
        searchButton.click();
        WebElement buttonField = driver.findElement(By.xpath("//div[@id=\"content\"]/div/section/div/div[1]/div[2]/div/button"));
        Assert.assertEquals("Search displays best match","Best match",buttonField.getText());
    }


    @After
    public void tearDown() throws Exception {
        Thread.sleep(4000);
        driver.quit();
    }
}
//