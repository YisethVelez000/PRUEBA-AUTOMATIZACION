package test;

import conexion.Conexion_BD;
import conexion.ingresoPruebas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;

public class crearContratos
{
    private WebDriver driver;
    private ingresoPruebas ingreso;
    private Connection conexion;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\apuertav\\IdeaProjects\\pruebaAutomatizacion\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        ingreso = new ingresoPruebas(driver);
        configurarConexionDB();
    }

    private void configurarConexionDB() {
        Conexion_BD conexionDB = new Conexion_BD();
        conexion = conexionDB.conectar();
        if (conexion != null) {
            System.out.println("Conexion a la base de datos exitosa");
        }
        else {
            System.err.println("Error al establecer la conexión a la base de datos. ");
        }
    }

    private void esperar(final int milisegundos) throws InterruptedException {
        Thread.sleep(milisegundos);
    }

    @Test
    public void crearContrato() throws InterruptedException {
        ingreso.iniciarSesion();
        driver.get("http://10.250.2.35:8080/savia/contratacion/contratos.faces");
        esperar(500);
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt52")).click();
    }

    private void contratoContributivo() {

    }

    private void contratoSubsidiado() {

    }

    private void contrato() throws  InterruptedException{

    }

}
