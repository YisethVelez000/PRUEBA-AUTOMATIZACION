package test;

import conexion.Conexion_BD;
import conexion.ingresoPruebas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class crearNotaTecnica {
    private WebDriver driver;
    private ingresoPruebas ingreso;
    private Connection conexion;
    private double costoPromedio;

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
    public void crearNotaTecnica() throws InterruptedException {
        ingreso.iniciarSesion();
        contratacion();
        esperar(2000);
        // Seleccionar un contrato aleatorio de la lista
        int index = (int) (Math.random() * 29 + 0);
        driver.findElement(By.cssSelector("button#frmContratos\\:tablaRegistros\\:"+index+"\\:j_idt90")).click();
        esperar(1000);
        // Seleccionar la opción de crear nota técnica
        driver.findElement(By.cssSelector("button#frmListarNotaTecnica\\:j_idt715")).click();
        esperar(1000);

        // Generar un número aleatorio entre 1 y 4
        int numero = (int) (Math.random() * 4 + 1);
        if (numero == 1) {
            System.out.println("Creando nota técnica con tecnología");
            driver.findElement(By.cssSelector("button#frmCrearNotaTecnica\\:j_idt787")).click();
            esperar(2000);
            //Crear nota técnica con tecnología
            int tecnologiaNumero = (int) (Math.random() * 15 + 0);
            System.out.println("Tecnología seleccionada: " + tecnologiaNumero);
            // Guardar los registros de tecnología en una lista
            List<WebElement> tecnologia = driver.findElements(By.cssSelector("tr.ui-widget-content.ui-datatable-even.ui-datatable-selectable"));
            esperar(1000);
            // Seleccionar un registro aleatorio de la lista
            tecnologia.get(tecnologiaNumero).click();
            esperar(500);
            metodoCostoPromedio();
            tipoFrecuencia();
            frecuenciaUso();
            cantidadAfiliados();
            tipoAmbito();
            observacion();
            fechaInicio();
            fechaFin();
            guardarCambios();

        }else if (numero == 2) {
            //Crear nota técnica con medicamentos
            System.out.println("Creando nota técnica con medicamentos");
            driver.findElement(By.cssSelector("button#frmCrearNotaTecnica\\:j_idt788")).click();
            esperar(2000);
            int medicamentoNumero = (int) (Math.random() * 15 + 0);
            System.out.println("Medicamento seleccionado: " + medicamentoNumero);
            // Guardar los registros de medicamentos en una lista
            List<WebElement> medicamento = driver.findElements(By.cssSelector("tr.ui-widget-content.ui-datatable-even.ui-datatable-selectable"));
            esperar(1000);
            // Seleccionar un registro aleatorio de la lista
            medicamento.get(medicamentoNumero).click();
            metodoCostoPromedio();
            tipoFrecuencia();
            frecuenciaUso();
            cantidadAfiliados();
            tipoAmbito();
            observacion();
            fechaInicio();
            fechaFin();
            guardarCambios();

        }else if (numero == 3) {
            //Crear nota técnica con insumos
            driver.findElement(By.cssSelector("button#frmCrearNotaTecnica\\:j_idt789")).click();
            esperar(2000);
            System.out.println("Creando nota técnica con insumos");
            int insumoNumero = (int) (Math.random() * 15 + 0);
            System.out.println("Insumo seleccionado: " + insumoNumero);
            // Guardar los registros de insumos en una lista
            List<WebElement> insumo = driver.findElements(By.cssSelector("tr.ui-widget-content.ui-datatable-even.ui-datatable-selectable"));
            esperar(5000);
            // Seleccionar un registro aleatorio de la lista
            insumo.get(insumoNumero).click();
            metodoCostoPromedio();
            tipoFrecuencia();
            frecuenciaUso();
            cantidadAfiliados();
            tipoAmbito();
            observacion();
            fechaInicio();
            fechaFin();
            guardarCambios();

        }else if (numero == 4) {
            //Crear nota técnica con paquete
            System.out.println("Creando nota técnica con paquete");
            driver.findElement(By.cssSelector("button#frmCrearNotaTecnica\\:j_idt790")).click();
            esperar(2000);
            int paqueteNumero = (int) (Math.random() * 15 + 0);
            System.out.println("Paquete seleccionado: " + paqueteNumero);
            // Guardar los registros de paquetes en una lista
            List<WebElement> paquete = driver.findElements(By.cssSelector("tr.ui-widget-content.ui-datatable-even.ui-datatable-selectable"));
            esperar(1000);
            // Seleccionar un registro aleatorio de la lista
            paquete.get(paqueteNumero).click();
            metodoCostoPromedio();
            tipoFrecuencia();
            frecuenciaUso();
            cantidadAfiliados();
            tipoAmbito();
            observacion();
            fechaInicio();
            fechaFin();
            guardarCambios();

        }


    }

    private void metodoCostoPromedio () throws   InterruptedException{
        esperar(500);
        costoPromedio = (int) (Math.random() * 100000 + 1);
        driver.findElement(By.cssSelector("input#frmCrearNotaTecnica\\:costoPromedio_input")).sendKeys(String.valueOf(costoPromedio));

    }

    private void tipoFrecuencia () throws InterruptedException {
        driver.findElement(By.cssSelector("div#frmCrearNotaTecnica\\:tipoFrecuencia")).click();
        //Numero random de 1 a 3
        int index = (int) (Math.random() * 3 + 1);
        System.out.println("Tipo de frecuencia seleccionado: " + index);
        driver.findElement(By.cssSelector("li#frmCrearNotaTecnica\\:tipoFrecuencia_"+index)).click();
    }

    private void frecuenciaUso () throws InterruptedException {
        int frecuencia = (int) (Math.random() * 30 + 1);
        driver.findElement(By.cssSelector("input#frmCrearNotaTecnica\\:frecuenciaUso_input")).sendKeys(String.valueOf(frecuencia));
    }

    private void cantidadAfiliados () throws InterruptedException {
        int afiliados = (int) (Math.random() * 100000 + 1);
        driver.findElement(By.cssSelector("input#frmCrearNotaTecnica\\:cantidadAfiliados_input")).sendKeys(String.valueOf(afiliados));
    }

    private void tipoAmbito () throws InterruptedException {
        driver.findElement(By.cssSelector("div#frmCrearNotaTecnica\\:ambito")).click();
        //Numero random de 1 a 3
        int index = (int) (Math.random() * 3 + 1);
        driver.findElement(By.cssSelector("li#frmCrearNotaTecnica\\:ambito_"+index)).click();
    }

    private void observacion () throws InterruptedException {
        String observacion = "Nota técnica creada de forma automática";
        driver.findElement(By.cssSelector("textarea#frmCrearNotaTecnica\\:observacion")).sendKeys(observacion);
    }

    private void fechaInicio () throws InterruptedException {
        driver.findElement(By.cssSelector("input#frmCrearNotaTecnica\\:fechaInicio_input")).click();
        esperar(500);
        // Seleccionar la fecha actual
        LocalDate fehaInicio = LocalDate.now();
        driver.findElement(By.cssSelector("input#frmCrearNotaTecnica\\:fechaInicio_input")).sendKeys(fehaInicio.toString());
    }

    private void fechaFin () throws InterruptedException{
        driver.findElement(By.cssSelector("input#frmCrearNotaTecnica\\:fechaFin_input")).click();
        esperar(500);
        LocalDate fechaFin = LocalDate.now().minusMonths(6);
        driver.findElement(By.cssSelector("input#frmCrearNotaTecnica\\:fechaFin_input")).sendKeys(fechaFin.toString());

    }

    private void guardarCambios() throws InterruptedException {
        driver.findElement(By.cssSelector("button#frmCrearNotaTecnica\\:j_idt813")).click();
        esperar(2000);
    }
}
