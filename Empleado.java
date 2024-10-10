public abstract class Empleado implements java.io.Serializable {
    protected String nombre;
    protected double salario;

    public Empleado(String nombre, double salario) throws SalarioInvalidoException {
        this.nombre = nombre;
        setSalario(salario);
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) throws SalarioInvalidoException {
        if (salario < 0) { // Solo restringimos salarios negativos
            throw new SalarioInvalidoException("El salario no puede ser negativo.");
        }
        this.salario = salario;
    }

    public abstract double calcularPago(int horasTrabajadas) throws HorasInvalidasException;
}



