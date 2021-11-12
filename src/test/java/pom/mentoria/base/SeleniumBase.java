package pom.mentoria.base;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class SeleniumBase {

    //Atributos
    WebDriver driver;

    //Constructor para traernos driver
    public SeleniumBase(WebDriver driver){
        this.driver = driver;
    }

    //Metodos Wrapper - Envoltorio
    public WebElement encontrarElemento(By localizador){
        return driver.findElement(localizador);
    }

    public void irUrl(String URL){
        driver.get(URL);
    }

    public void clickear(By localizador){
        WebElement element = encontrarElemento(localizador);
        Boolean isclicked;
        try {
            esperaExplicitaElementoClickeable(localizador,10);
            element.click();
            isclicked= true;
        } catch(Exception e){
            isclicked= false;
        }
        Assert.assertTrue(isclicked);
    }

    public void esperaExplicitaElementoClickeable(By localizador, int time){
        WebDriverWait espera;
        espera = new WebDriverWait(driver,time);
        espera.until(ExpectedConditions.elementToBeClickable(localizador));
    }

    public void esperaExplicitaPrescenciaElemento(By localizador, int time){
        WebDriverWait espera;
        espera = new WebDriverWait(driver,time);
        espera.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    public String obtenerTexto(By localizador){
        return encontrarElemento(localizador).getText();
    }

    public void ingresarTexto(By localizador, String txt) { encontrarElemento(localizador).sendKeys(txt); }

    public void selectPestana(int index)
    {
        ArrayList<String> tabsAbiertas;
        tabsAbiertas = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabsAbiertas.get(index));
    }

    public void dormir(int mseconds) throws InterruptedException {
        Thread.sleep(mseconds);
    }

}
