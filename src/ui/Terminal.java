package ui;

import implementacion.EmpleadoDAOImp;
import implementacion.PedidoDAOImp;
import implementacion.ProductoDAOImp;
import java.util.Scanner;
import objeto.Empleado;
import objeto.Producto;

public class Terminal {

    public Scanner scan = new Scanner(System.in);
    protected Empleado EmpleadoRegistrado;
    protected PedidoDAOImp Pedido;
    protected ProductoDAOImp Productos;

    public Terminal(Empleado EmpleadoRegistrado, PedidoDAOImp Pedido, ProductoDAOImp Productos) {
        this.Pedido = Pedido;
        this.EmpleadoRegistrado = EmpleadoRegistrado;
        this.Productos = Productos;
    }

    public void loggin() {

        System.out.println("Bienvenido a la tienda");
        System.out.println("***************************************");

        int employeeAccessCode = handleInputEmployeeAccessCode();
        String employeePassword = handleInputEmployeePassword(employeeAccessCode);

        //Asign Logged User
        var employees = new EmpleadoDAOImp();

        for (Empleado i : employees.getEmpleados()) {
            if (i.getCodigo() == employeeAccessCode) {
                this.EmpleadoRegistrado.setCodigo(i.getCodigo());
                this.EmpleadoRegistrado.setNombre(i.getNombre());
                this.EmpleadoRegistrado.setApellido(i.getApellido());
                this.EmpleadoRegistrado.setContraseña(i.getContraseña());
            }
        }
    }

    public void mainMenu() {

        String commandString = null;
        int command = 0;
        boolean validOption = false;

        while (!validOption) {

            System.out.println("--Menú principal -------------------");
            System.out.println("1. Hacer pedido");
            System.out.println("2. Modificar producto");
            System.out.println("3. Cambiar contraseña de empleado");
            System.out.println("4. Cerrar sesión");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opción: ");
            commandString = scan.nextLine();

            try {
                command = Integer.parseInt(commandString);
            } catch (NumberFormatException e) {
                System.err.println("Error - Solo se aceptan valores integer");
            }

            if (command > 0 && command <= 4) {
                validOption = true;
            } else {
                System.err.println("Error - Select An Valid Option");
            }
        }

        switch (command) {
            case 1:
                makeOrder();
                break;
            case 2:
                changeProduct();
                break;
            case 3:
                changeEmployeePassword();
                break;
            case 4:
                closeSection();
                break;
        }
    }

    public void makeOrder() {

        boolean finishOrder = false;

        while (!finishOrder) {
            String commandString = null;
            int command = 0;
            boolean validOption = false;

            while (!validOption) {

                System.out.println("");
                System.out.println("--Hacer pedido -------------------");
                System.out.println("   1 Añadir producto a la cesta");
                System.out.println("   2 Visualizar precio total de la cesta");
                System.out.println("   3 Imprimir factura");
                System.out.println("   4 Terminar pedido");
                System.out.println("------------------------------------");

                System.out.print("Seleccione una opción: ");
                commandString = scan.nextLine();

                try {
                    command = Integer.parseInt(commandString);
                } catch (NumberFormatException e) {
                    System.err.println("Error - Solo se aceptan valores integer");
                }

                if (command > 0 && command <= 4) {
                    validOption = true;
                } else {
                    System.err.println("Error - Select An Valid Option");
                }
            }

            switch (command) {
                case 1:
                    System.out.println("\n" + this.Productos.toString() + "\n");
                    Pedido.AñadirProducto(handleInputProduct());
                    break;
                case 2:
                    System.out.println("El precio TOTAL es: " + Pedido.LeerPrecioTotal() + "€");
                    break;
                case 3:
                    Pedido.ImprimirFactura(this.EmpleadoRegistrado);
                    break;
                case 4:
                    finishOrder = true;
                    this.Pedido = new PedidoDAOImp();
                    System.out.println("\n");
                    break;
            }
        }

        mainMenu();
    }

    public void changeProduct() {

        String commandString = null;
        int command = 0;
        boolean validOption = false;

        while (!validOption) {

            System.out.println("");
            System.out.println("--Modificar un producto -------------------");
            System.out.println("   1 Modificar nombre de producto");
            System.out.println("   2 Modificar precio de producto");
            System.out.println("   3 Modificar código de producto");
            System.out.println("------------------------------------");
            
            System.out.print("Seleccione una opción: ");
            commandString = scan.nextLine();

            try {
                command = Integer.parseInt(commandString);
            } catch (NumberFormatException e) {
                System.err.println("Error - Solo se aceptan valores integer");
            }

            if (command > 0 && command <= 4) {
                validOption = true;
            } else {
                System.err.println("Error - Select An Valid Option");
            }

        }

        System.out.println(this.Productos.toString());
        int CodigoProducto = handleInputProductCode();

        switch (command) {
            case 1:
                this.Productos.CambiarNombre(CodigoProducto, handleInputProductName());
                break;
            case 2:
                this.Productos.CamiarPrecio(CodigoProducto, handleInputProductPrice());
                break;
            case 3:
                this.Productos.CambiarCodigo(CodigoProducto, handleInputNewProductCode());
                break;
        }

        mainMenu();

    }

    public void changeEmployeePassword() {
        System.out.println("Escribe la contraseña antigua: ");
        handleInputEmployeePassword(this.EmpleadoRegistrado.getCodigo());
        var employees = new EmpleadoDAOImp();
        employees.CambiarContraseña(EmpleadoRegistrado, handleInputEmployeeNewPassword());
        System.out.println("Contraseña cambiada");
        mainMenu();
    }

    public void closeSection() {
        System.out.println("Sesion cerrada");
        this.EmpleadoRegistrado = new Empleado();
        this.Pedido = new PedidoDAOImp();
        loggin();
    }

    //Handlers
    private int handleInputEmployeeAccessCode() {

        int CodigoEmpleado = 0;
        boolean validAccessCode = false;
        boolean validFormat = true;

        while (!validAccessCode) {
            validFormat = true;
            System.out.print("Introduce el código de tu usuario: ");
            String employeeAccessCodeString = scan.nextLine();

            try {
                CodigoEmpleado = Integer.parseInt(employeeAccessCodeString);

            } catch (NumberFormatException e) {
                System.err.println("Error - Solo se aceptan valores integer");
                validFormat = false;
            }

            if (validFormat) {
                if (validateEmployeeAccessCode(CodigoEmpleado)) {
                    validAccessCode = true;
                } else {
                    System.err.println("Error - Usuario incorrecto");
                }
            }
        }

        return CodigoEmpleado;
    }

    private String handleInputEmployeePassword(int CodigoEmpleado) {

        String ContraseñaEmpleado = null;
        boolean validPassword = false;

        while (!validPassword) {
            System.out.print("Introduce la contraseña: ");
            ContraseñaEmpleado = scan.nextLine();
            if (validateEmployeePassword(CodigoEmpleado, ContraseñaEmpleado)) {
                validPassword = true;
            } else {
                System.err.println("Error - Contraseña incorrecta");
            }
        }
        return ContraseñaEmpleado;

    }

    private int handleInputProductCode() {

        int productCode = 0;
        boolean validProductCode = false;
        boolean validFormat = true;

        while (!validProductCode) {

            validFormat = true;
            System.out.print("Introduce el codigo del producto: ");
            String productCodeString = scan.nextLine();

            try {
                productCode = Integer.parseInt(productCodeString);

            } catch (NumberFormatException e) {
                System.err.println("Error - Solo se aceptan valores integer");
                validFormat = false;
            }

            if (validFormat) {
                if (validateProductCode(productCode)) {
                    System.out.println("El codigo del producto es correcto");
                    validProductCode = true;
                } else {
                    System.err.println("Error - El codigo del producto es incorrecto");
                }
            }
        }

        return productCode;
    }

    private int handleInputNewProductCode() {

        int newProductCode = 0;
        boolean validNewProductCode = false;
        boolean validFormat = true;

        while (!validNewProductCode) {

            validFormat = true;
            System.out.print("Introduce el nuevo codigo del producto: ");
            String newProductCodeString = scan.nextLine();

            try {
                newProductCode = Integer.parseInt(newProductCodeString);

            } catch (NumberFormatException e) {
                System.err.println("Error - Solo se aceptan valores integer");
                validFormat = false;
            }

            if (validFormat) {
                if (validateNewProductCode(newProductCode)) {
                    System.out.println("El codigo del producto es correcto");
                    validNewProductCode = true;
                } else {
                    System.err.println("Error - El coddigo del producto es incorrecto");
                }
            }
        }

        return newProductCode;
    }

    private double handleInputProductPrice() {
        double productPrice = 0.0;
        boolean validProductPrice = false;
        boolean validFormat = true;

        while (!validProductPrice) {

            validFormat = true;
            System.out.print("Introduce el nuevo precio del prodcuto: ");
            String newProductCodeString = scan.nextLine();

            try {
                productPrice = Double.parseDouble(newProductCodeString);

            } catch (NumberFormatException e) {
                System.err.println("Error - Solo se aceptan valores integer");
                validFormat = false;
            }

            if (validFormat) {
                if (validateProductPrice(productPrice)) {
                    System.out.println("El precio del producto es correcto");
                    validProductPrice = true;
                } else {
                    System.err.println("Error - El precio del producto es incorrecto");
                }
            }
        }

        return productPrice;
    }

    private String handleInputProductName() {

        String productName = null;
        boolean validProducName = false;

        while (!validProducName) {
            System.out.print("Introduce el nuevo nombre del prodcuto : ");
            productName = scan.nextLine();
            if (validateProductName(productName)) {
                System.out.println("El nombre del producto es correcto");
                validProducName = true;
            } else {
                System.err.println("Error - El nombre del producto es incorrecto");
            }
        }
        return productName;

    }

    private String handleInputEmployeeNewPassword() {

        String newPassword = "";
        boolean validNewPassword = false;

        while (!validNewPassword) {
            System.out.println("Introduce la nueva contraseña: ");
            newPassword = scan.nextLine();
            if (validateEmployeeNewPassword(newPassword)) {
                validNewPassword = true;
            } else {
                System.out.println("Contraseña incorrecta");
            }
        }

        return newPassword;
    }

    private Producto handleInputProduct() {
        int CodigoProducto = handleInputProductCode();
        Producto product = null;
        for (Producto i : this.Productos.getProductos()) {
            if (i.getCodigo()== CodigoProducto) {
                product = i;
            }
        }
        return product;
    }

    //Validators
    private static boolean validateEmployeeAccessCode(int employeeAccessCode) {

        var Empleados = new EmpleadoDAOImp();

        boolean validAccessCode = false;

        for (Empleado i : Empleados.getEmpleados()) {
            if (i.getCodigo()== employeeAccessCode) {
                validAccessCode = true;
            }
        }

        return validAccessCode;
    }

    private static boolean validateEmployeePassword(int employeeAccessCode, String employeePassword) {

        var Empleados = new EmpleadoDAOImp();
        var Empleado = new Empleado();

        boolean validPassword = false;

        for (Empleado i : Empleados.getEmpleados()) {
            if (i.getCodigo()== employeeAccessCode) {
                Empleado = i;
            }
        }

        if (Empleado.getContraseña().equals(employeePassword)) {
            validPassword = true;
        }

        return validPassword;
    }

    private boolean validateProductCode(int productCode) {

        boolean validProductCode = false;

        for (Producto i : this.Productos.getProductos()) {
            if (i.getCodigo()== productCode) {
                validProductCode = true;
            }
        }

        return validProductCode;
    }

    private boolean validateNewProductCode(int productCode) {

        boolean validProductCode = true;

        for (Producto i : this.Productos.getProductos()) {
            if (i.getCodigo()== productCode) {
                validProductCode = false;
            }
        }

        return validProductCode;
    }

    private boolean validateProductPrice(double productPrice) {

        boolean validProductCode = false;

        if (productPrice > 0) {
            validProductCode = true;
        }

        return validProductCode;

    }

    private boolean validateProductName(String productName) {

        boolean validProductCode = false;

        if (!productName.isBlank()) {
            validProductCode = true;
        }

        return validProductCode;

    }

    private boolean validateEmployeeNewPassword(String employeeNewPassword) {

        boolean validNewPassword = false;

        if (!employeeNewPassword.isBlank()) {
            validNewPassword = true;
        }

        return validNewPassword;

    }

}
