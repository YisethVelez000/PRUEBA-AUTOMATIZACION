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

    }

    private void contratacion() throws InterruptedException {
        driver.get("http://10.250.3.66:8080/savia/contratacion/contratos.faces");
    }

    private void filtrarContrato() throws InterruptedException{
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt63")).sendKeys("0001-2017");
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt52")).click();

    }

}
