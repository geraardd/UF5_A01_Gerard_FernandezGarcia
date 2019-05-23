package definicion;

import objeto.Empleado;
import objeto.Producto;

public interface PedidoDAO {
    
    void AñadirProducto(Producto newProduct);
    double LeerPrecioTotal();
    void ImprimirFactura(Empleado attendant);
    
}
