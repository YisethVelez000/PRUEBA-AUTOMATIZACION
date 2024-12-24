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

        filtrarNit();
        esperar(500);

        filtrarNombre();
        esperar(500);

        modalidadContrato();
        esperar(500);

        filtrarCodigoHabilitacion();
        esperar(500);

        filtrarNombreSede();
        esperar(500);

        filtrarFechaHoraCrea();
        esperar(500);

        driver.quit();

    }

    private void contratacion() throws InterruptedException {
        driver.get("http://10.250.2.35:8080/savia/contratacion/contratos.faces");
        driver.manage().window().maximize();
    }

    private void filtrarContrato() throws InterruptedException{
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt63")).sendKeys("072-2016");
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
        esperar(500);
        //Ninguno de los anteriores
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt67")).click();
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt67_0")).click();


    }

    private void filtrarNit () throws InterruptedException {
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt71")).sendKeys("800082394");
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt51")).click();
        esperar(800);
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt71")).clear();
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt51")).click();
        esperar(800);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt70")).click();
        esperar(800);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt70")).click();
    }

    private void filtrarNombre () throws InterruptedException {
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt73")).sendKeys("PHARMASYS UT");
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt51")).click();
        esperar(500);
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt73")).clear();
        esperar(500);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt72")).click();
        esperar(500);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt72")).click();
    }

    private void modalidadContrato () throws InterruptedException {
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt75")).click();
        // CAPITA
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt75_1")).click();
        esperar(500);
        // EVENTO
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt75")).click();
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt75_2")).click();
        esperar(500);
        // PGP
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt75")).click();
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt75_3")).click();
        esperar(500);
        // PAQUETES
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt75")).click();
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt75_4")).click();
        //COVID
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt75")).click();
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt75_5")).click();
        esperar(500);
        //Ninguno de los anteriores
        driver.findElement(By.cssSelector("div#frmContratos\\:tablaRegistros\\:j_idt75")).click();
        driver.findElement(By.cssSelector("li#frmContratos\\:tablaRegistros\\:j_idt75_0")).click();
    }

    private void filtrarCodigoHabilitacion () throws InterruptedException {
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt78")).click();
        esperar(500);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt78")).click();
        esperar(500);
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt79")).sendKeys("901835598001");
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt51")).click();
        esperar(500);
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt79")).clear();
    }

    private void filtrarNombreSede () throws InterruptedException {
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt80")).click();
        esperar(500);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt80")).click();
        esperar(500);
        driver.findElement(By.cssSelector("input#frmContratos\\:tablaRegistros\\:j_idt81")).sendKeys("UNLAB BARRIO ORTIZ");
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt51")).click();
        esperar(500);
    }

    private void filtrarFechaHoraCrea () throws InterruptedException {
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt82")).click();
        esperar(500);
        driver.findElement(By.cssSelector("th#frmContratos\\:tablaRegistros\\:j_idt82")).click();
        esperar(500);
    }
}
