package definicion;

public interface ProductoDAO {
    
    void CambiarNombre(int CodigoProducto, String NombreProducto);
    void CamiarPrecio(int CodigoProducto, double PrecioProducto);
    void CambiarCodigo(int CodigoProducto, int NuevoCodigoProducto);
    void EscribirEnArchivo();
  
    
    
}
