package implementacion;

import definicion.PedidoDAO;
import java.util.ArrayList;
import java.util.List;
import objeto.Empleado;
import objeto.Producto;

public class PedidoDAOImp implements PedidoDAO{
    
    List<Producto> cesta;
    
    public PedidoDAOImp() {
        this.cesta = new ArrayList<>();
    }

    @Override
    public void AñadirProducto(Producto newProduct) {
        this.cesta.add(newProduct);
    }

    @Override
    public double LeerPrecioTotal() {
        double PrecioTotal = 0;
        for (Producto i : this.cesta) {
            PrecioTotal += i.getPrecio();
        }
        return PrecioTotal;
    }

    @Override
    public void ImprimirFactura(Empleado attendant) {
        System.out.println("Factura simplificada");
        System.out.println("--------------------------------------------------------------");
        
        if (!this.cesta.isEmpty()) {
            for (Producto i : this.cesta) {
                System.out.println("Codigo: " + i.getCodigo());
                System.out.println("Nombre: " + i.getNombre());
                System.out.println("Descripcion: " + i.getDescripcion());
                System.out.println("Precio: " + i.getPrecio());
            }
        } else {
            System.out.println("La cesta esta llena.");
        }
        
        System.out.println("--------------------------------------------------------------");
        System.out.println("El precio TOTAL es: " + LeerPrecioTotal() + "€");
        System.out.println("Atendido por: " + attendant.getNombre() + attendant.getApellido());
    }
    
}
