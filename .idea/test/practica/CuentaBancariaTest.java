package practica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaTest {

    private CuentaBancaria cuenta ;
    private CuentaBancaria cuentaNeg ;
    @BeforeEach
    void setUp() {
        cuenta = new CuentaBancaria("Ana garcia",1000);
        cuentaNeg = new CuentaBancaria("paco alfajor",-1000);
        System.out.println("instanciando cuentas...");
    }

    @Test
    void CuentaDelBanco(){
        assertEquals("Ana garcia", cuenta.getTitular());
        assertEquals(1000, cuenta.getSaldo());
        assertFalse(cuenta.isBloqueada());
        assertEquals(0, cuentaNeg.getSaldo());


    }

    @Test
    void setTitular() {
        cuenta.setTitular("pepe perez");
        assertEquals("pepe perez", cuenta.getTitular());
        cuenta.setTitular(null);
        assertEquals("pepe perez", cuenta.getTitular());
        cuenta.setTitular("");
        assertEquals("pepe perez", cuenta.getTitular());
    }

    @Test
    void setSaldo() {
        cuenta.setSaldo(100);
        assertEquals(100, cuenta.getSaldo());
        cuenta.setSaldo(-100);
        assertEquals(100, cuenta.getSaldo());

    }

    @Test
    void isBloqueada() {

    }

    @Test
    void setBloqueada() {
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
        cuenta.ingresar(1000);
        assertEquals(saldo+1000,cuenta.getSaldo());

        assertFalse(cuenta.ingresar(-100));
        assertEquals(saldo+1000,cuenta.getSaldo());

        cuenta.setBloqueada(true);
        assertFalse(cuenta.ingresar(500));
        assertEquals(saldo+1000,cuenta.getSaldo());


    }

    @Test
    void retirar() {
        double saldo = cuenta.getSaldo();
        assertTrue(cuenta.retirar(200));
        assertEquals(saldo-200,cuenta.getSaldo());

        assertFalse(cuenta.retirar(-100));
        assertEquals(saldo-200,cuenta.getSaldo());

        assertFalse(cuenta.retirar(1500));
        assertEquals(saldo-200,cuenta.getSaldo());

        cuenta.setBloqueada(true);
        assertFalse(cuenta.retirar(500));
        assertEquals(saldo-200,cuenta.getSaldo());
    }

    @Test
    void aplicarComisionMensual() {
        cuenta.setSaldo(50001);
        assertEquals(4999, cuenta.aplicarComisionMensual());
        assertEquals(4999, cuenta.getSaldo());
        cuenta.setSaldo(1000);
        assertEquals(998, cuenta.aplicarComisionMensual());
        assertEquals(998, cuenta.getSaldo());
        assertEquals(993, cuenta.aplicarComisionMensual());
        assertEquals(993, cuenta.getSaldo());
        cuenta.setSaldo(3);
        assertEquals(0, cuenta.aplicarComisionMensual());
        assertEquals(0, cuenta.getSaldo());
        cuenta.setSaldo(3000);
        cuenta.setBloqueada(true);
        assertEquals(3000,cuenta.aplicarComisionMensual());
        assertEquals(3000, cuenta.getSaldo());

    }
    @Test
    void bloquearSiSaldoCero() {
        cuenta.setSaldo(100);
        assertFalse(cuenta.bloquearSiSaldoCero());
        cuenta.setSaldo(0);
        assertTrue(cuenta.bloquearSiSaldoCero());


    }

    @AfterEach
    void tearDown() {
        System.out.println("liberando cuentas...");
        cuenta=null;
        cuentaNeg=null;
    }
}