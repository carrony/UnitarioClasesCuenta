package practica;

public class Producto {
    private String nombre;
    private double precio;
    private int unidades;

    public Producto(String nombre, double precio, int unidades) {
        this.nombre = nombre;
        this.precio = precio; // setPrecio(precio);
        this.unidades = unidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre;
        }
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        if (unidades >= 0) {
            this.unidades = unidades;
        }
    }
}