import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Empresa {
    private ArrayList<Empleado> empleados = new ArrayList<>();

    // Método para agregar empleado
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    // Método para consultar salarios
    public void consultarSalarios() {
        for (Empleado e : empleados) {
            System.out.println("Empleado: " + e.getNombre() + ", Salario: " + e.getSalario());
        }
    }

    // Simular pago de nómina
    public void simularNomina(int horasTrabajadas) {
        for (Empleado e : empleados) {
            try {
                double pago = e.calcularPago(horasTrabajadas);
                System.out.println("Empleado: " + e.getNombre() + ", Pago: " + pago);
            } catch (HorasInvalidasException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    // Guardar lista de empleados en un archivo
    public void guardarEmpleados(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(empleados);
            System.out.println("Empleados guardados en archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    // Recuperar lista de empleados desde un archivo
    public void recuperarEmpleados(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            empleados = (ArrayList<Empleado>) ois.readObject();
            System.out.println("Empleados recuperados del archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recuperar empleados: " + e.getMessage());
        }
    }

    // Método para mostrar empleados desde un archivo
    public void mostrarEmpleadosDesdeArchivo(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArrayList<Empleado> empleadosRecuperados = (ArrayList<Empleado>) ois.readObject();
            for (Empleado e : empleadosRecuperados) {
                System.out.println("Nombre: " + e.getNombre() + ", Salario: " + e.getSalario());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recuperar empleados: " + e.getMessage());
        }
    }

    // Método para agregar empleados desde la consola
    public void agregarEmpleadoDesdeConsola() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Ingrese el nombre del empleado:");
        String nombre = scanner.nextLine();
        
        System.out.println("Seleccione el tipo de empleado: ");
        System.out.println("1. Empleado Fijo");
        System.out.println("2. Empleado Temporal");
        int tipoEmpleado = scanner.nextInt();
        
        try {
            if (tipoEmpleado == 1) {
                System.out.println("Ingrese el salario fijo del empleado:");
                double salario = scanner.nextDouble();
                Empleado emp = new EmpleadoFijo(nombre, salario);
                agregarEmpleado(emp);
                System.out.println("Empleado Fijo agregado con éxito.");
            } else if (tipoEmpleado == 2) {
                System.out.println("Ingrese el salario base:");
                double salario = scanner.nextDouble();
                
                System.out.println("Ingrese el pago por hora:");
                double pagoPorHora = scanner.nextDouble();
                
                Empleado emp = new EmpleadoTemporal(nombre, salario, pagoPorHora);
                agregarEmpleado(emp);
                System.out.println("Empleado Temporal agregado con éxito.");
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (SalarioInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de la Empresa ---");
            System.out.println("1. Agregar Empleado");
            System.out.println("2. Consultar Salarios");
            System.out.println("3. Simular Pago de Nómina");
            System.out.println("4. Guardar Empleados en Archivo");
            System.out.println("5. Recuperar Empleados desde Archivo");
            System.out.println("6. Mostrar Empleados desde Archivo");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    empresa.agregarEmpleadoDesdeConsola();
                    break;
                case 2:
                    empresa.consultarSalarios();
                    break;
                case 3:
                    System.out.print("Ingrese el número de horas trabajadas: ");
                    int horasTrabajadas = scanner.nextInt();
                    empresa.simularNomina(horasTrabajadas);
                    break;
                case 4:
                    empresa.guardarEmpleados("empleados.dat");
                    break;
                case 5:
                    empresa.recuperarEmpleados("empleados.dat");
                    break;
                case 6:
                    empresa.mostrarEmpleadosDesdeArchivo("empleados.dat");
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
    }
}






