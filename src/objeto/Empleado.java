package objeto;

public class Empleado {
    
    private int codigo;
    private String nombre;
    private String apellido;
    private String contraseña;

    public Empleado(int codigo, String nombre, String apellido, String contraseña) {
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        
    }
    
    public Empleado(){
        this(0, null, null, null);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
