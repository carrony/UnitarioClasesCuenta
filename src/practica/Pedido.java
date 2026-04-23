package practica;

// hacer test unitarios
public class Pedido {
    private int numeroPedido;
    private String cliente;
    private Producto[] productos;
    private boolean urgente;
    private static int totalPedidosCreados = 0;

    public Pedido(int numeroPedido, String cliente, Producto[] productos) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.productos = productos;
        this.urgente = false;
        totalPedidosCreados++;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        if (numeroPedido > 0) {
            this.numeroPedido = numeroPedido;
        }
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        if (cliente != null && !cliente.isBlank()) {
            this.cliente = cliente;
        }
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public static int getTotalPedidosCreados() {
        return totalPedidosCreados;
    }

    public static void reiniciarContador() {
        totalPedidosCreados = 0;
    }

    public double calcularTotal() {
        if (productos == null || productos.length == 0) {
            return 0;
        }
        double total = 0;
        for (Producto producto : productos) {
            if (producto != null && producto.getPrecio() > 0 && producto.getUnidades() > 0) {
                total += producto.getPrecio() * producto.getUnidades();
            }
        }
        if (urgente && total > 100) {
            total += 10;
        }
        return total;
    }

    public int contarProductosValidos() {
        if (productos == null) {
            return 0;
        }
        int contador = 0;
        for (Producto producto : productos) {
            if (producto != null && producto.getNombre() != null && !producto.getNombre().isBlank()) {
                contador++;
            }
        }
        return contador;
    }

    public boolean aplicarDescuentoSiCorresponde() {
        double total = calcularTotal();
        if (total >= 200) {
            for (Producto producto : productos) {
                if (producto != null) {
                    producto.setPrecio(producto.getPrecio() * 0.9);
                }
            }
            return true;
        }
        return false;
    }

    public String generarResumen() {
        if (cliente == null || cliente.isBlank()) {
            return "Pedido sin cliente";
        }
        String tipo = urgente ? "URGENTE" : "NORMAL";
        return "Pedido " + numeroPedido + " de " + cliente + " - " + tipo + " - " + contarProductosValidos() + " productos";
    }

    public boolean contieneProducto(String nombre) {
        if (nombre == null || productos == null) {
            return false;
        }
        for (Producto producto : productos) {
            if (producto != null && nombre.equalsIgnoreCase(producto.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public int eliminarProductosSinStock() {
        if (productos == null) {
            return 0;
        }
        int eliminados = 0;
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] != null && productos[i].getUnidades() <= 0) {
                productos[i] = null;
                eliminados++;
            }
        }
        return eliminados;
    }
}