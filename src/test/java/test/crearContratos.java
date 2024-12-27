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
import java.util.List;

public class crearContratos
{
    private WebDriver driver;
    private ingresoPruebas ingreso;
    private Connection conexion;
    private long valorContrato;
    private long numeroAfiliados;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\apuertav\\Downloads\\pruebaAutomatizacion\\src\\test\\resources\\chromedriver.exe");
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
        driver.get("http://10.250.3.66:8080/savia/contratacion/contratos.faces");
        esperar(500);
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt52")).click();
        esperar(1000);
        contratoContributivo();
        System.out.println("Contrato contributivo creado exitosamente");
        esperar(10500);
        driver.findElement(By.cssSelector("button#frmContratos\\:j_idt52")).click();
        esperar(1000);
        contratoSubsidiado();
        System.out.println("Contrato subsidiado creado exitosamente");

    }

    private void contratoContributivo() throws InterruptedException {
        contrato();
        afiliados();
        fechaIncial();
        fechaFinal();
        activo();
        valorContrato();
        valorMes();
        valorPresupuestoTotal();
        diasLimitePago();
        objetoContrato();
        regimenContributivo();
        prestador();
        sede();

    }

    private void contratoSubsidiado() throws InterruptedException {
        contrato();
        afiliados();
        fechaIncial();
        fechaFinal();
        activo();
        valorContrato();
        valorMes();
        valorPresupuestoTotal();
        diasLimitePago();
        objetoContrato();
        regimenSubsidiado();
        prestador();
        sede();
    }

    private void contrato() throws  InterruptedException{
        //Generamos un número random para el contrato
        int numeroContrato = (int) (Math.random() * 1000000 + 1);
        driver.findElement(By.cssSelector("input#frmCrear\\:contrato")).sendKeys(String.valueOf(numeroContrato));

    }

    private void afiliados () throws InterruptedException{
         numeroAfiliados = (long) (Math.random() * 1000000000 + 1);
//        driver.findElement(By.cssSelector("input#Crear\\:numAfiliados")).sendKeys(String.valueOf(numeroAfiliados));
        driver.findElement(By.id("frmCrear:numAfiliados_input")).sendKeys(String.valueOf(numeroAfiliados));
    }

    private void fechaIncial () throws InterruptedException {
        //Traemos la fecha actual
        LocalDate fechaInicio = LocalDate.now();
        driver.findElement(By.cssSelector("input#frmCrear\\:fechaInicio_input")).sendKeys(String.valueOf(fechaInicio));
    }

    private void fechaFinal () throws InterruptedException {
        //Traemos la fecha actual
        LocalDate fechaFinal = LocalDate.now();
        //Le sumamos 6 meses a la fecha actual
        fechaFinal = fechaFinal.plusMonths(6);
        driver.findElement(By.cssSelector("input#frmCrear\\:fechaFin_input")).sendKeys(String.valueOf(fechaFinal));
        driver.findElement(By.cssSelector("label#frmCrear\\:j_idt195")).click();
    }

    private void activo () throws InterruptedException {
        driver.findElement(By.id("frmCrear:activo")).click();
    }

    private void valorContrato () throws InterruptedException {
        valorContrato = (long) (Math.random() * 1000000000 + 1);
        driver.findElement(By.cssSelector("input#frmCrear\\:valor_input")).sendKeys(String.valueOf(valorContrato));
    }

    private void valorMes () throws InterruptedException {
        long valorMes = valorContrato / 6;
        driver.findElement(By.cssSelector("input#frmCrear\\:valorMes_input")).sendKeys(String.valueOf(valorMes));
    }

    private void valorPresupuestoTotal () throws InterruptedException {
        // Sacamos el 10% del valor del contrato
        long valorPresupuestoTotal = valorContrato +(valorContrato / 10);
        driver.findElement(By.cssSelector("input#frmCrear\\:valorPptoTotal_input")).sendKeys(String.valueOf(valorPresupuestoTotal));
    }

    private void diasLimitePago () throws InterruptedException {
        driver.findElement(By.cssSelector("input#frmCrear\\:diasLimite")).sendKeys(String.valueOf(30));
    }

    private void objetoContrato() throws InterruptedException {
        driver.findElement(By.cssSelector("textarea#frmCrear\\:objContrato")).sendKeys("Prestar servicios integrales de salud de baja complejidad a los afiliados del Régimen subsidiado, contributivo y en estado de portabilidad y movilidad de Savia Salud EPS, del Municipio de Angostura y que se encuentren debidamente registrados en la base de datos que dispone la EPS y con derecho a los servicios contenidos en el Plan de Beneficios de Salud y la atención en todos los servicios contratados como Prestador Primario de Salud.");
    }

    private void regimenSubsidiado() throws InterruptedException {
        driver.findElement(By.cssSelector("div#frmCrear\\:regimenContrato")).click();
        driver.findElement(By.cssSelector("li#frmCrear\\:regimenContrato_1")).click();
    }

    private void regimenContributivo() throws InterruptedException {
        driver.findElement(By.cssSelector("div#frmCrear\\:regimenContrato")).click();
        driver.findElement(By.cssSelector("li#frmCrear\\:regimenContrato_2")).click();
    }

    private void prestador() throws InterruptedException {
        String query = "SELECT c.cnt_prestadores_id,p.numero_documento FROM cnt_contratos c JOIN cnt_prestadores p ON c.cnt_prestadores_id = p.id ORDER BY RAND() LIMIT 1";
        String numeroDocumento = "";
        try {
            java.sql.Statement st = conexion.createStatement();
            java.sql.ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                numeroDocumento = resultSet.getString("numero_documento");
                System.out.println("Numero de documento: " + numeroDocumento);
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }

        driver.findElement(By.cssSelector("button#frmCrear\\:botonConsultarPrestador")).click();
        esperar(900);

        // Enviar el número de documento
        driver.findElement(By.cssSelector("input#frmPrestadorLista\\:tablaRegistrosPrestador\\:j_idt1076"));
        driver.findElement(By.cssSelector("input#frmPrestadorLista\\:tablaRegistrosPrestador\\:j_idt1076")).clear();
        driver.findElement(By.cssSelector("input#frmPrestadorLista\\:tablaRegistrosPrestador\\:j_idt1076")).sendKeys(numeroDocumento);

        driver.findElement(By.cssSelector("button#frmPrestadorLista\\:j_idt1065")).click();
        esperar(500);

        // Encontrar la primera fila y hacer clic
        WebElement primerElemento = driver.findElement(By.cssSelector("tr.ui-widget-content.ui-datatable-even.ui-datatable-selectable"));
        System.out.println(primerElemento.getText() + "  prestadorrr");
        primerElemento.click();
    }

    private void sede() throws InterruptedException{
        driver.findElement(By.cssSelector("button#frmCrear\\:j_idt225")).click();
        esperar(500);

        // Seleccionar la primera sede
        WebElement primeraSede = driver.findElement(By.cssSelector("tr.ui-widget-content.ui-datatable-even.ui-datatable-selectable"));
        System.out.println(primeraSede.getText()+ " sede");
        primeraSede.click();

        esperar(500);

        driver.findElement(By.id("frmCrearContratoSede:nroAfiliados_input")).sendKeys(String.valueOf(numeroAfiliados));
        driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:modalidad")).click();
        // Generamos un numero random para la modalidad de 1 a 5
        int modalidad = (int) (Math.random() * 5 + 1);
        System.out.println("Modalidad: " + modalidad);
        driver.findElement(By.cssSelector("li#frmCrearContratoSede\\:modalidad_" + modalidad)).click();

        int nivelAtencion = (int) (Math.random() * 4 + 1);
        System.out.println("Nivel de atención: " + nivelAtencion);
        driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:nivelAtencion")).click();
        driver.findElement(By.cssSelector("li#frmCrearContratoSede\\:nivelAtencion_" + nivelAtencion)).click();

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaContributivo = (int) (Math.random() * 2);
        System.out.println("Aplica contributivo: " + aplicaContributivo);
        if (aplicaContributivo == 1) {
            System.out.println("Aplica contributivo");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaContributivo")).click();
        }else{
            System.out.println("No aplica contributivo");
        }

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaSubsidiado = (int) (Math.random() * 2);
        System.out.println("Aplica subsidiado: " + aplicaSubsidiado);
        if (aplicaSubsidiado == 1) {
            System.out.println("Aplica subsidiado");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaSubsidiado")).click();
        }else {
            System.out.println("No aplica subsidiado");
        }

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaGlosa = (int) (Math.random() * 2);
        System.out.println("Aplica glosa: " + aplicaGlosa);
        if (aplicaGlosa == 1) {
            System.out.println("Aplica glosa");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaGlosa")).click();
        }else {
            System.out.println("No aplica glosa");
        }

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaPAC = (int) (Math.random() * 2);
        System.out.println("Aplica PAC: " + aplicaPAC);
        if (aplicaPAC == 1) {
            System.out.println("Aplica PAC");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaPac")).click();
        }else {
            System.out.println("No aplica PAC");
        }

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaPortabilidad = (int) (Math.random() * 2);
        System.out.println("Aplica portabilidad: " + aplicaPortabilidad);
        if (aplicaPortabilidad == 1) {
            System.out.println("Aplica portabilidad");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaPortabilidad")).click();
        }else {
            System.out.println("No aplica portabilidad");
        }

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaAuditoria = (int) (Math.random() * 2);
        System.out.println("Aplica auditoria: " + aplicaAuditoria);
        if (aplicaAuditoria == 1) {
            System.out.println("Aplica auditoria");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaAuditoria")).click();
        }else {
            System.out.println("No aplica auditoria");
        }

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaAutorizacion = (int) (Math.random() * 2);
        System.out.println("Aplica autorizacion: " + aplicaAutorizacion);
        if (aplicaAutorizacion == 1) {
            System.out.println("Aplica autorizacion");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaAutorización")).click();
        }else {
            System.out.println("No aplica autorizacion");
        }

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaAgendamiento = (int) (Math.random() * 2);
        System.out.println("Aplica agendamiento: " + aplicaAgendamiento);
        if (aplicaAgendamiento == 1) {
            System.out.println("Aplica agendamiento");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaAgendamiento")).click();
        }else {
            System.out.println("No aplica agendamiento");
        }

        esperar(200);
        driver.findElement(By.id("frmCrearContratoSede:valorContrato_input")).sendKeys(String.valueOf(valorContrato));

        // Generamos un número random del 1 al 0, si es 1 = Aplica y si es 0 = No aplica
        int aplicaRecaudoCopago = (int) (Math.random() * 2);
        System.out.println("Aplica recaudo copago: " + aplicaRecaudoCopago);
        if (aplicaRecaudoCopago == 1) {
            System.out.println("Aplica recaudo copago");
            driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:aplicaCopago")).click();
        }else {
            System.out.println("No aplica recaudo copago");
        }

        // Traemos la fecha actual
        LocalDate fechaInicio = LocalDate.now();
        driver.findElement(By.cssSelector("input#frmCrearContratoSede\\:fechaInicio_input")).sendKeys(String.valueOf(fechaInicio));

        // Le sumamos 6 meses a la fecha actual
        LocalDate fechaFinal = LocalDate.now();
        fechaFinal = fechaFinal.plusMonths(6);
        driver.findElement(By.cssSelector("input#frmCrearContratoSede\\:fechaFin_input")).sendKeys(String.valueOf(fechaFinal));

        driver.findElement(By.cssSelector("textarea#frmCrearContratoSede\\:observacion")).sendKeys("Prestar servicios integrales de salud de baja complejidad a los afiliados del Régimen subsidiado, contributivo y en estado de portabilidad y movilidad de Savia Salud EPS, del Municipio de Angostura y que se encuentren debidamente registrados en la base de datos que dispone la EPS y con derecho a los servicios contenidos en el Plan de Beneficios de Salud y la atención en todos los servicios contratados como Prestador Primario de Salud.");

//        driver.findElement(By.cssSelector("button#frmCrearContratoSede\\:j_idt441")).click();

        driver.findElement(By.cssSelector("div#frmCrearContratoSede\\:municipioCobertura")).click();

        int cantidadMunicipios = (int) (Math.random() * 10 + 1);
        System.out.println("Cantidad de municipios: " + cantidadMunicipios);
        List <WebElement> municipios = driver.findElements(By.className("ui-selectcheckboxmenu-item"));

        for (int i = 0; i < cantidadMunicipios; i++) {
            int municipio = (int) (Math.random() * 10 + 1);
            System.out.println("Municipio: " + municipio);

            municipios.get(municipio).getText();
            municipios.get(municipio).click();

        }

        driver.findElement(By.cssSelector("button#frmCrearContratoSede\\:j_idt441")).click();
        driver.findElement(By.cssSelector("button#frmCrear\\:j_idt242")).click();

    }

}
