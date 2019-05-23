package base;

import implementacion.ProductoDAOImp;
import implementacion.PedidoDAOImp;
import objeto.Empleado;
import ui.Terminal;

public class Main {

    public static void main(String[] args) {
        Main program = new Main();
        program.start();
       
    }
    
    public void start() {
        var EmpleadoRegistrado = new Empleado();
        var Pedido = new PedidoDAOImp();
        var Productos = new ProductoDAOImp();
        var Sesion = new Terminal(EmpleadoRegistrado, Pedido, Productos);
    
        while (true) {
            Sesion.loggin();
            Sesion.mainMenu();
        }
    }
    
}
