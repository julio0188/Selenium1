package ejercicios.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Exercise1 {
    private WebDriver driver;


    @Test
    public void ExerciseNavigator1() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //maximizamos el navegador
        driver.manage().window().maximize();
        //borramos las cookies
        driver.manage().deleteAllCookies();
        //navegar al siguiente URL
        driver.get("https://phptravels.com/demo/");
        // paso 1, obtenemos el CSS de product y damos clic sobre el
        WebElement product = driver.findElement(By.cssSelector("body > header > div > nav > div:nth-child(4)"));
        product.click();
        // paso 2, damos clic sobre providers
        WebElement providers = driver.findElement(By.cssSelector("body > header > div > nav > div:nth-child(4) > div > a:nth-child(6)"));
        providers.click();
        WebElement title = driver.findElement(By.id("header-title"));
        //iniciamos el soft assert
        SoftAssert softAssert = new SoftAssert();
        //Guardamos en variables los textos a evaluar
        String titlehtmls = title.getText();
        String titlecomparison = "Travel XML API Integrations Providers";
        softAssert.assertEquals(titlehtmls, titlecomparison, "no son iguales con assert equals");
        //paso 3, damos clic sobre demo
        WebElement demo = driver.findElement(By.cssSelector("body > header > div > nav > a:nth-child(1)"));
        demo.click();
        //Obtenemos el texto del titulo mostrado
        WebElement title2 = driver.findElement(By.id("header-title"));
        titlehtmls = title2.getText();
        System.out.println(titlehtmls);
        titlecomparison = "Application Test Drive";
        System.out.println(titlecomparison);
        softAssert.assertEquals(titlehtmls, titlecomparison, "no son iguales con assert equals");
        //matamos al driver
        driver.quit();
    }

    @Test
    public void ExerciseNavigator2() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //maximizamos el navegador
        driver.manage().window().maximize();
        //borramos las cookies
        driver.manage().deleteAllCookies();
        //navegar al siguiente URL
        driver.get("http://the-internet.herokuapp.com/");

        //Seleccionamos la opcion dropdown
        WebElement dropdown = driver.findElement(By.linkText("Dropdown"));
        dropdown.click();
        //se crea un wait para esperar el dropdown
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //cogemos el elemento multiselect
        WebElement dropdownlist = driver.findElement(By.id("dropdown"));
        //se espera a que sea visible
        wait.until(ExpectedConditions.visibilityOf(dropdownlist));

        //creamos el objeto select basándonos en el web element y a partir de ahora utilizamos este nuevo
        Select drpOptions = new Select(dropdownlist);
        drpOptions.selectByValue("2");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownlist));
        //inicializamos variables boolean en false para luego hacer las validaciones
        boolean opt1 = false;
        boolean opt2 = false;

        //Se hace la validacion de la opcion seleccionada
        if (drpOptions.getFirstSelectedOption().getText().equals("Option 1"))
            opt1 = true;
        else
            opt2 = true;
        //System.out.println(drpOptions.getFirstSelectedOption().getText());
        SoftAssert softAssert = new SoftAssert();
        //Validamos que la opcion 1 no este seleccionada
        softAssert.assertFalse(opt1, "La opcion 1 si esta seleccionada - validacion 1");
        //Validamos que la opcion 2 este seleccionada
        softAssert.assertTrue(opt2, "La opcion 2 no si esta seleccionada - validacion 1");

        //se selecciona la opcion 2 por texto
        //iniciamos las variables
        opt1 = false;
        opt2 = false;

        drpOptions.selectByVisibleText("Option 1");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownlist));
        //System.out.println(drpOptions.getFirstSelectedOption().getText());
        //Se hace la validacion de la opcion seleccionada
        if (drpOptions.getFirstSelectedOption().getText().equals("Option 1"))
            opt1 = true;
        else
            opt2 = true;
        //Validamos que la opcion 1 este seleccionada
        softAssert.assertTrue(opt1, "La opcion 1 no esta seleccionada  - validacion 2");
        //Validamos que la opcion 2 no este seleccionada
        softAssert.assertFalse(opt2, "La opcion 2 si esta seleccionada  - validacion 2");

        //Se selecciona la opcion 2 por indes
        //iniciamos las variables
        opt1 = false;
        opt2 = false;

        drpOptions.selectByIndex(2);
        wait.until(ExpectedConditions.elementToBeClickable(dropdownlist));
        //System.out.println("LA OPCION SELECCIONADA ES: ");
        //System.out.println(drpOptions.getFirstSelectedOption().getText());
        //Se hace la validacion de la opcion seleccionada
        if (drpOptions.getFirstSelectedOption().getText().equals("Option 1"))
            opt1 = true;
        else
            opt2 = true;

        //Validamos que la opcion 1 no este seleccionada
        softAssert.assertFalse(opt1, "La opcion 1 no esta seleccionada  - validacion 3");
        //Validamos que la opcion 2 este seleccionada
        softAssert.assertTrue(opt2, "La opcion 2 si esta seleccionada  - validacion 3");
        //Se ejecuta la verificacion de las validaciones
        softAssert.assertAll();
        //matamos al driver
        driver.quit();
    }

    @Test
    public void ExerciseNavigator3() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //maximizamos el navegador
        driver.manage().window().maximize();
        //borramos las cookies
        driver.manage().deleteAllCookies();
        //navegar al siguiente URL
        driver.get("http://the-internet.herokuapp.com/");

        //Seleccionamos la opcion add remove elements
        WebElement addremove = driver.findElement(By.linkText("Add/Remove Elements"));
        addremove.click();
        //se crea un wait para esperar el dropdown
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //cogemos el elemento button
        WebElement buttonaddremove = driver.findElement(By.cssSelector("#content > div > button"));
        //se espera a que sea visible
        wait.until(ExpectedConditions.visibilityOf(buttonaddremove));
        SoftAssert softAssert = new SoftAssert();
        //Tomamos el titulo de la pagina cargada
        WebElement tittle = driver.findElement(By.cssSelector("#content > h3"));
        //Se valida el titulo esperado
        System.out.println(tittle.getText());
        softAssert.assertEquals(tittle.getText(), "Add/Remove Elements", "No viene el titulo esperado");
        //Se valida el boton add/remove este visible
        softAssert.assertTrue(buttonaddremove.isDisplayed(), "El boton add/remove no esta visible");
        boolean btnremove = false;
        // se toma el valor del boton remove

        try {
            WebElement butremove = driver.findElement(By.cssSelector("#elements > button"));
            if (butremove.isDisplayed())
                btnremove = true;
        } catch (Exception e) {
            btnremove = false;
        }


        //Se verifica que el boton remove no este visible
        softAssert.assertFalse(btnremove, "El boton remove esta visible");
        //Se hace clic sobre el boton Add
        buttonaddremove.click();

        WebElement butremove = driver.findElement(By.cssSelector("#elements > button"));

        //Se valida que el boton remove sea visible
        softAssert.assertTrue(butremove.isDisplayed(), "El boton remove no esta visible");
        //Se hace clic sobre el boton remobe
        butremove.click();
        try {
            WebElement butremove2 = driver.findElement(By.cssSelector("#elements > button"));
            if (butremove2.isDisplayed())
                btnremove = true;
        } catch (Exception e) {
            btnremove = false;
        }//Se valida que el boton remove ya no sea visible
        softAssert.assertFalse(btnremove, "El boton remove esta visible");

        //Se ejecuta la verificacion de las validaciones
        softAssert.assertAll();
        //matamos al driver
        driver.quit();
    }

    @Test
    public void ExerciseNavigator4() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //maximizamos el navegador
        driver.manage().window().maximize();
        //borramos las cookies
        driver.manage().deleteAllCookies();
        //navegar al siguiente URL
        driver.get("http://the-internet.herokuapp.com/");

        //Seleccionamos la opcion add remove elements
        WebElement form_authentication = driver.findElement(By.linkText("Form Authentication"));
        form_authentication.click();
        //se crea un wait para esperar el dropdown
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //cogemos el elemento button
        WebElement buttonLogin = driver.findElement(By.cssSelector("#login > button"));

        //se espera a que sea visible
        wait.until(ExpectedConditions.visibilityOf(buttonLogin));
        SoftAssert softAssert = new SoftAssert();
        //Tomamos el titulo de la pagina cargada
        WebElement tittle = driver.findElement(By.cssSelector("#content > div > h2"));
        //Se valida el titulo esperado
        //System.out.println(tittle.getText());
        softAssert.assertEquals(tittle.getText(), "Login Page", "No viene el titulo esperado 1");
        //Se hace clic sobre boton Login sin datos
        buttonLogin.click();
        WebElement msgerror = driver.findElement(By.id("flash"));
        //Se valida el mensaje de error
        softAssert.assertEquals(msgerror.getText(), "Your username is invalid!", "No viene el titulo esperado");
        //se localiza el usuario y contrasenia y se les coloca los valores correctos
        WebElement usuario = driver.findElement(By.id("username"));
        WebElement contrasenia = driver.findElement(By.id("password"));
        usuario.sendKeys("tomsmith");
        contrasenia.sendKeys("SuperSecretPassword!");

        WebElement buttonLogin2 = driver.findElement(By.cssSelector("#login > button"));
        buttonLogin2.click();
        WebElement logout = driver.findElement(By.cssSelector("#content > div > a"));
        wait.until(ExpectedConditions.visibilityOf(logout));
        softAssert.assertEquals(tittle.getText(), "Secure Area", "No viene el titulo esperado 2");
        //Se hace clic en logout
        WebElement logout2 = driver.findElement(By.cssSelector("#content > div > a"));
        logout2.click();
        wait.until(ExpectedConditions.visibilityOf(buttonLogin));
        //Se valida de nuevo el mensaje
        softAssert.assertEquals(tittle.getText(), "Login Page", "No viene el titulo esperado 3");
        //Se ejecuta la verificacion de las validaciones
        softAssert.assertAll();
        //matamos al driver
        driver.quit();
    }

}



















