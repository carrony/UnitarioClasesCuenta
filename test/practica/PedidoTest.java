package practica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Pedido pedido;
    private Pedido pedidoVacio;
    private Pedido pedidoNulo;
    private Producto prod1,prod2,prod3;

    @BeforeEach
    void setUp() {
        prod1 =new Producto("Caja de leche entera", 0.75, 25);
        prod2 =new Producto("Galletas", 2.5, 8);
        prod3 = new Producto("Coca cola", 1,10);

        Producto []lista={prod1,prod2,prod3};

        pedido = new Pedido(1, "David Casas", lista );

        pedidoVacio = new Pedido(2, "Jhon Doe", new Producto[]{});
        pedidoNulo = new Pedido(3, "homer Simpson", null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testConstructor() {
            assertEquals(1, pedido.getNumeroPedido());
            assertEquals("David Casas", pedido.getCliente());
            Producto []lista={prod1,prod2,prod3};
            assertArrayEquals(lista,pedido.getProductos());
            assertEquals(3, pedido.getProductos().length);
            assertFalse(pedido.isUrgente());
    }


    @Test
    void setNumeroPedido() {
    }

    @Test
    void setCliente() {
    }

    @Test
    void setProductos() {
    }

    @Test
    void setUrgente() {
    }

    @Test
    void reiniciarContador() {
    }

    @Test
    void calcularTotal() {
        assertEquals(0.75*25+2.5*8+1*10,pedido.calcularTotal());
        pedido.setUrgente(true);
        assertEquals(0.75*25+2.5*8+1*10,pedido.calcularTotal());
        prod2.setPrecio(30);
        assertEquals(0.75*25+30*8+1*10+10,pedido.calcularTotal());

    }

    @Test
    void contarProductosValidos() {
    }

    @Test
    void aplicarDescuentoSiCorresponde() {
    }

    @Test
    void generarResumen() {
    }

    @Test
    void contieneProducto() {
    }

    @Test
    void eliminarProductosSinStock() {
    }
}