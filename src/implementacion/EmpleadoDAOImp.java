package implementacion;

import definicion.EmpleadoDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import objeto.Empleado;

public class EmpleadoDAOImp implements EmpleadoDAO{

    private List<Empleado> empleados;
    private final String RutaRelativaArchivo = "src/files/products.txt";

    public EmpleadoDAOImp() {

        Path path = Paths.get(this.RutaRelativaArchivo);

        List<Empleado> empleadosList = new ArrayList<Empleado>();

        try ( var reader = Files.newBufferedReader(path)) {

            int CodigoEmpleado = 0;
            String NombreEmpleado = null;
            String ApellidoEmpleado = null;
            String ContraseñaEmpleado = null;

            while (reader.readLine() != null) {

                reader.readLine();
                CodigoEmpleado = Integer.parseInt(reader.readLine().trim());

                reader.readLine();
                NombreEmpleado = reader.readLine().trim();

                reader.readLine();
                ApellidoEmpleado = reader.readLine().trim();

                reader.readLine();
                ContraseñaEmpleado = reader.readLine().trim();

                empleadosList.add(new Empleado(CodigoEmpleado, NombreEmpleado, ApellidoEmpleado, ContraseñaEmpleado));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setEmpleados(empleadosList);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public void CambiarContraseña(Empleado empleado, String ContraseñaEmpleado) {
        for (int i = 0; i < getEmpleados().size(); i++) {
            if (getEmpleados().get(i).getCodigo() == empleado.getCodigo()) {
                this.empleados.get(i).setContraseña(ContraseñaEmpleado);
            }
        }
        this.EscribirEnArchivo();
    }

    @Override
    public void LeerEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EscribirEnArchivo() {
        String empleadoListString = this.toString();
        try {
            FileWriter writer = new FileWriter(RutaRelativaArchivo);
            writer.write(empleadoListString);
            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ProductoDAOImp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        String productoListString = "";
        for (Empleado i : this.empleados) {
            productoListString += "[empleado]" + "\n [codigo]\n " + i.getCodigo()+ "\n [nombre]\n " + i.getNombre()+ "\n [apellidos]\n " + i.getApellido()+ "\n [contraseña]\n " + i.getContraseña()+ "\n";
        }
        return productoListString;
    }
    
    
    
    
    
    

}


