package test;

import conexion.Conexion_BD;
import conexion.ingresoPruebas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;

public class listar {
    private WebDriver driver;
    private ingresoPruebas ingreso;
    private Connection conexion;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\apuertav\\IdeaProjects\\pruebaAutomatizacion\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        ingreso = new ingresoPruebas(driver);

        configurarConexionDB();
    }
    private void configurarConexionDB(){
        Conexion_BD conexionDB = new Conexion_BD();
        conexion = conexionDB.conectar();

        if(conexion != null){
            System.out.println("Conexion a la base de datos exitosa");
        }else{
            System.err.println("Error al establecer la conexión a la base de datos. ");
        }
    }

    private void esperar (int milisegundos) throws InterruptedException {
        Thread.sleep(milisegundos);
    }

    @Test
    public void prueba1()throws InterruptedException{
        ingreso.iniciarSesion();
        esperar(500);

        contratacion();
        esperar(500);

        filtrarContrato();
        esperar(500);

        filtrarFechaInicial();
        esperar(500);

        filtrarFechaFinal();
        esperar(500);

        filtrarEstadoContrato();
        esperar(500);

    }

    private void contratacion() throws InterruptedException {
        driver.get("http://10.250.2.35:8080/savia/contratacion/contratos.faces");
    }

    private void filtrarContrato() throws InterruptedException{
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt63")).sendKeys("prueba1");
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt51")).click();
        esperar(5000);
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt63")).clear();
    }

    private void filtrarFechaInicial () throws InterruptedException {
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt64")).click();
        esperar(500);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt64")).click();
    }

    private void filtrarFechaFinal () throws InterruptedException{
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt65")).click();
        esperar(500);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt65")).click();
    }

    private void filtrarEstadoContrato () throws InterruptedException {
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt67")).click();
        //VIGENTE
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt67_1")).click();
        esperar(500);
        // NO VIGENTE
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt67")).click();
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt67_2")).click();
    }

    private void filtrarNit () throws InterruptedException {

    }






}
