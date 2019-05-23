package implementacion;

import definicion.ProductoDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objeto.Producto;

public class ProductoDAOImp implements ProductoDAO {

    List<Producto> productos;
    private final String RutaRelativaArchivo = "src/files/products.txt";

    public ProductoDAOImp() {

        Path path = Paths.get(this.RutaRelativaArchivo);

        List<Producto> productoList = new ArrayList<Producto>();

        try ( var reader = Files.newBufferedReader(path)) {

            int CodigoProducto = 0;
            String NombreProducto = null;
            String DescripcionProducto = null;
            double PrecioProducto = 0.0;

            while (reader.readLine() != null) {

                reader.readLine();
                CodigoProducto = Integer.parseInt(reader.readLine().trim());

                reader.readLine();
                NombreProducto = reader.readLine().trim();

                reader.readLine();
                DescripcionProducto = reader.readLine().trim();

                reader.readLine();
                PrecioProducto = Double.parseDouble(reader.readLine().trim().replace(',', '.'));

                productoList.add(new Producto(CodigoProducto, NombreProducto, DescripcionProducto, PrecioProducto));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setProductos(productoList);

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public void CambiarNombre(int CodigoProducto, String NombreProducto) {

        Producto producto = new Producto();
        for (int i = 0; i < this.productos.size(); i++) {
            if (this.productos.get(i).getCodigo() == CodigoProducto) {
                producto.setCodigo(this.productos.get(i).getCodigo());
                producto.setDescripcion(this.productos.get(i).getDescripcion());
                producto.setPrecio(this.productos.get(i).getPrecio());
                producto.setNombre(NombreProducto);
                this.productos.set(i, producto);
            }
        }
        this.EscribirEnArchivo();
    }

    @Override
    public void CamiarPrecio(int PrecioProducto, double CodigoProducto) {
        Producto producto = new Producto();
        for (int i = 0; i < this.productos.size(); i++) {
            if (this.productos.get(i).getCodigo() == CodigoProducto) {
                producto.setDescripcion(this.productos.get(i).getDescripcion());
                producto.setPrecio(PrecioProducto);
                producto.setNombre(this.productos.get(i).getNombre());
                producto.setCodigo(this.productos.get(i).getCodigo());
                this.productos.set(i, producto);
            }
        }
        this.EscribirEnArchivo();
    }

    @Override
    public void CambiarCodigo(int CodigoProducto, int NuevoCodigoProducto) {
        Producto producto = new Producto();
        for (int i = 0; i < this.productos.size(); i++) {
            if (this.productos.get(i).getCodigo() == CodigoProducto) {
                producto.setDescripcion(this.productos.get(i).getDescripcion());
                producto.setPrecio(this.productos.get(i).getPrecio());
                producto.setNombre(this.productos.get(i).getNombre());
                producto.setCodigo(NuevoCodigoProducto);
                this.productos.set(i, producto);
            }
        }
        this.EscribirEnArchivo();
    }

    @Override
    public void EscribirEnArchivo() {
        String productListString = this.toString();
        try {
            FileWriter writer = new FileWriter(RutaRelativaArchivo);
            writer.write(productListString);
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(ProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {

        String productoListString = "";
        for (Producto i : this.productos) {
            productoListString += "[producto]" + "\n [codigo]\n " + i.getCodigo() + "\n [nombre]\n " + i.getNombre() + "\n [descripcion]\n " + i.getDescripcion() + "\n [precio]\n " + i.getPrecio() + "\n";
        }
        return productoListString;
    }
    
}
