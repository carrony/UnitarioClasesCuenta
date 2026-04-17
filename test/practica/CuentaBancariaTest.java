package practica;

import com.sun.nio.sctp.HandlerResult;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaTest {

    private CuentaBancaria cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaBancaria("Ana García", 1000);
        System.out.println("Instaciando a Ana García con 1000 euros de saldo... Iniciando test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Liberando las cuentas");
        cuenta = null;
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Iniciando test...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando test...");
    }

    @Test
    void testConstructor()  {
        assertEquals("Ana García", cuenta.getTitular());
        assertEquals(1000, cuenta.getSaldo());
        assertFalse( cuenta.isBloqueada());

        CuentaBancaria cuentaNegativa = new CuentaBancaria("Ana García", -300);
        assertEquals(0, cuentaNegativa.getSaldo());
    }



    @Test
    void setTitular() {
        cuenta.setTitular("Andrés Jiménez");
        assertEquals("Andrés Jiménez", cuenta.getTitular());

        cuenta.setTitular(null);
        assertEquals("Andrés Jiménez", cuenta.getTitular());

        cuenta.setTitular("   ");
        assertEquals("Andrés Jiménez", cuenta.getTitular());
    }


    @Test
    void setSaldo() {
        cuenta.setSaldo(100);
        assertEquals(100, cuenta.getSaldo());
        cuenta.setSaldo(-100);
        assertEquals(100, cuenta.getSaldo());
    }


    @Test
    void setBloqueada() {
        cuenta.setBloqueada(true);
        assertTrue(cuenta.isBloqueada());

        cuenta.setBloqueada(false);
        assertFalse(cuenta.isBloqueada());
    }

    @Test
    void getTotalCuentasCreadas() {
    }

    @Test
    void reiniciarContador() {
    }

    @Test
    void ingresar() {
        double saldo = cuenta.getSaldo();
        assertTrue(cuenta.ingresar(200));
        assertEquals(saldo+200, cuenta.getSaldo());

        assertFalse(cuenta.ingresar(-300));
        assertEquals(saldo+200, cuenta.getSaldo());

        cuenta.setBloqueada(true);
        assertFalse(cuenta.ingresar(500));
        assertEquals(saldo+200, cuenta.getSaldo());
    }

    @Test
    void retirar() {
        //double saldo= cuenta.getSaldo();
        assertTrue(cuenta.retirar(200));
        assertEquals(800, cuenta.getSaldo());

        assertFalse(cuenta.retirar(-300));
        assertEquals(800, cuenta.getSaldo());

        assertFalse(cuenta.retirar(5000));
        assertEquals(800, cuenta.getSaldo());

        cuenta.setBloqueada(true);
        assertFalse(cuenta.retirar(500));
        assertEquals(800, cuenta.getSaldo());

    }

    @Test
    void aplicarComisionMensual() {
        assertEquals(998, cuenta.aplicarComisionMensual());
        assertEquals(998, cuenta.getSaldo());

        assertEquals(993, cuenta.aplicarComisionMensual());
        assertEquals(993, cuenta.getSaldo());

        cuenta.setSaldo(3);
        assertEquals(0, cuenta.aplicarComisionMensual());
        assertEquals(0, cuenta.getSaldo());

        cuenta.setSaldo(8000);
        assertEquals(8000, cuenta.aplicarComisionMensual());
        assertEquals(8000, cuenta.getSaldo());

        cuenta.setSaldo(3000);
        cuenta.setBloqueada(true);
        assertEquals(3000, cuenta.aplicarComisionMensual());
        assertEquals(3000, cuenta.getSaldo());



    }

    @Test
    void bloquearSiSaldoCero() {
        assertFalse(cuenta.bloquearSiSaldoCero());

        cuenta.setSaldo(0);
        assertTrue(cuenta.bloquearSiSaldoCero());
    }
}