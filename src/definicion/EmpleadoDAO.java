package definicion;

import objeto.Empleado;

public interface EmpleadoDAO {
    
    void LeerEmpleados();
    
    void CambiarContraseña(Empleado empleado, String ContraseñaEmpleado);
    
    void EscribirEnArchivo();
    
}
