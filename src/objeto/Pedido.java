package objeto;

import java.util.List;

public class Pedido {
    
    private Empleado empleados;
    private List<Producto> productos;
    private double PrecioTotal;

    public Pedido(Empleado empleados, List<Producto> productos, double PrecioTotal) {
        this.empleados = empleados;
        this.productos = productos;
        this.PrecioTotal = PrecioTotal;
    }
    
    public double getPrecioTotal() {
        return PrecioTotal;
    }
    
    public void setPrecioTotal(double PrecioTotal) {
        this.PrecioTotal = PrecioTotal;
    }
    
}
