package test;

import conexion.Conexion_BD;
import conexion.ingresoPruebas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class editarContrato
{
    private WebDriver driver;
    private ingresoPruebas ingreso;
    private Connection conexion;
    int valor;

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
    }

    @Test
    public void editarContrato() throws InterruptedException {
        ingreso.iniciarSesion();
        contratacion();
        esperar(2000);
        driver.findElement(By.cssSelector("button#frmContratos\\:tablaRegistros\\:0\\:j_idt88")).click();
        esperar(2000);
        numeroAfiliados();
        esperar(500);
        fechaFinal();
        esperar(500);
        estadoContrato();
        esperar(500);
        valor();
        esperar(500);
        valorMes();
        esperar(500);
        valorTotalPresupuesto();
        esperar(500);
        diasLimite();
        esperar(500);
        regimen();

    }

    private void numeroAfiliados () throws InterruptedException {
        String numAfiliadosAntes = driver.findElement(By.cssSelector("input#frmEditar\\:numAfiliados_input")).getAttribute("value");
        System.out.println("Número de afiliados antes de la edición: " + numAfiliadosAntes);

        int numeroAfiliados = (int) (Math.random() * 10000 +1);
        System.out.println("Número de afiliados después de la edición: " + numeroAfiliados);
        driver.findElement(By.cssSelector("input#frmEditar\\:numAfiliados_input")).sendKeys(String.valueOf(numeroAfiliados));
    }

    private void fechaFinal() throws InterruptedException {
        String fechaFinalAntes = driver.findElement(By.cssSelector("input#frmEditar\\:fechaFin_input")).getAttribute("value");
        System.out.println("Fecha final antes de la edición: " + fechaFinalAntes);

        //Convertirlo a Date
        Date fechaFinal = Date.valueOf(fechaFinalAntes);

        //Le asignamos a la fecha nueva la fecha final anterior + 6 meses
        fechaFinal.setMonth(fechaFinal.getMonth() + 6);
        System.out.println("Fecha final después de la edición: " + fechaFinal);

        driver.findElement(By.cssSelector("input#frmEditar\\:fechaFin_input")).clear();
        driver.findElement(By.cssSelector("input#frmEditar\\:fechaFin_input")).sendKeys(fechaFinal.toString());
        esperar(2000);
        driver.findElement(By.cssSelector("input#frmEditar\\:numAfiliados_input")).click();
        esperar(2000);

    }

    private void estadoContrato() throws InterruptedException {
        String estadoContrato = driver.findElement(By.cssSelector("div#frmEditar\\:activo")).getText();
        System.out.println("Estado del contrato antes de la edición: " + estadoContrato);
        driver.findElement(By.cssSelector("div#frmEditar\\:activo")).click();
    }

    private void valor () throws InterruptedException {
        String valorAntes = driver.findElement(By.cssSelector("input#frmEditar\\:valor_input")).getAttribute("value");
        System.out.println("Valor antes de la edición: " + valorAntes);

        valor = (int) (Math.random() * 1000000 +1);
        System.out.println("Valor después de la edición: " + valor);
        driver.findElement(By.cssSelector("input#frmEditar\\:valor_input")).clear();
        driver.findElement(By.cssSelector("input#frmEditar\\:valor_input")).click();
        driver.findElement(By.cssSelector("input#frmEditar\\:valor_input")).sendKeys(String.valueOf(valor));
    }

    private void valorMes () throws InterruptedException{
        String valorMesAntes = driver.findElement(By.cssSelector("input#frmEditar\\:valorMes_input")).getAttribute("value");
        System.out.println("Valor mes antes de la edición: " + valorMesAntes);

        int valorMes = valor/ 12;
        System.out.println("Valor mes después de la edición: " + valorMes);
        driver.findElement(By.cssSelector("input#frmEditar\\:valorMes_input")).clear();
        driver.findElement(By.cssSelector("input#frmEditar\\:valorMes_input")).click();
        driver.findElement(By.cssSelector("input#frmEditar\\:valorMes_input")).sendKeys(String.valueOf(valorMes));


    }

    private void valorTotalPresupuesto () throws InterruptedException {
        String valorTotalPresupuestoAntes = driver.findElement(By.cssSelector("input#frmEditar\\:valorPptoTotal_input")).getAttribute("value");
        System.out.println("Valor total presupuesto antes de la edición: " + valorTotalPresupuestoAntes);

        int valorTotalPpto = valor + (valor * 10);
        System.out.println("Valor total presupuesto después de la edición: " + valorTotalPpto);
        driver.findElement(By.cssSelector("input#frmEditar\\:valorPptoTotal_input")).clear();
        driver.findElement(By.cssSelector("input#frmEditar\\:valorPptoTotal_input")).click();
        driver.findElement(By.cssSelector("input#frmEditar\\:valorPptoTotal_input")).sendKeys(String.valueOf(valorTotalPpto));

    }

    private void diasLimite () throws InterruptedException {
        String diasLimiteAntes = driver.findElement(By.cssSelector("input#frmEditar\\:diasLimite")).getAttribute("value");
        System.out.println("Días límite antes de la edición: " + diasLimiteAntes);

        int diasLimite = (int) (Math.random() * 60 +1);
        System.out.println("Días límite después de la edición: " + diasLimite);
        driver.findElement(By.cssSelector("input#frmEditar\\:diasLimite")).clear();
        driver.findElement(By.cssSelector("input#frmEditar\\:diasLimite")).click();
        driver.findElement(By.cssSelector("input#frmEditar\\:diasLimite")).sendKeys(String.valueOf(diasLimite));
    }

    private void regimen () throws InterruptedException {
         driver.findElement(By.cssSelector("div#frmEditar\\:regimenContrato")).click();
         List regimenes = driver.findElements(By.cssSelector("div#frmEditar\\:regimenContrato_panel li"));
         // Seleccionar un regimen aleatorio de la lista del 1 al 2
         int index = (int) (Math.random() * regimenes.size() + 1);
         System.out.println("Regimen seleccionado: " + index);
            driver.findElement(By.cssSelector("li#frmEditar\\:regimenContrato_"+index)).click();
    }


}
