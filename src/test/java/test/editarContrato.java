package test;

import conexion.Conexion_BD;
import conexion.ingresoPruebas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;

public class editarContrato
{
    private WebDriver driver;
    private ingresoPruebas ingreso;
    private Connection conexion;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\apuertav\\Downloads\\pruebaAutomatizacion\\src\\test\\resources\\chromedriver.exe");
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

    private void contratacion() throws InterruptedException {
        driver.get("http://10.250.3.66:8080/savia/contratacion/contratos.faces");
        driver.manage().window().maximize();
    }

    @Test
    public void editarContrato() throws InterruptedException {
        ingreso.iniciarSesion();
        contratacion();
        esperar(2000);
    }

}
