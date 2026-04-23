package practica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    private Producto pr;

    @BeforeEach
    void setUp() {
        pr = new Producto("Caja de leche entera", 0.75, 35);
    }

    @Test
    void testConstructor(){
        assertEquals("Caja de leche entera", pr.getNombre());
        assertEquals(0.75, pr.getPrecio());
        assertEquals(35, pr.getUnidades());
    }

    @Test
    void setNombre() {
        pr.setNombre("caja de leche semi");;
        assertEquals("caja de leche semi", pr.getNombre());

        //casos límite
        pr.setNombre(null);
        assertEquals("caja de leche semi", pr.getNombre());

        pr.setNombre("    ");
        assertEquals("caja de leche semi", pr.getNombre());

    }

    @Test
    void setPrecio() {
        pr.setPrecio(350);
        assertEquals(350, pr.getPrecio());

        pr.setPrecio(-20);
        assertEquals(350, pr.getPrecio());

        pr.setPrecio(0);
        assertEquals(0, pr.getPrecio());
    }

    @Test
    void setUnidades() {
        pr.setUnidades(1);
        assertEquals(1, pr.getUnidades());
        pr.setUnidades(-2);
        assertEquals(1, pr.getUnidades());
        pr.setUnidades(0);
        assertEquals(0, pr.getUnidades());
    }
}