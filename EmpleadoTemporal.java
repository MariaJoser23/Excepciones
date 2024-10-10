public class EmpleadoTemporal extends Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    private double pagoPorHora;

    public EmpleadoTemporal(String nombre, double salarioBase, double pagoPorHora) throws SalarioInvalidoException {
        super(nombre, salarioBase);
        this.pagoPorHora = pagoPorHora;
    }

    @Override
    public double calcularPago(int horasTrabajadas) throws HorasInvalidasException {
        if (horasTrabajadas < 0) {
            throw new HorasInvalidasException("Las horas trabajadas no pueden ser negativas.");
        }
        return salario + (pagoPorHora * horasTrabajadas);
    }

    public double getPagoTotal(int horasTrabajadas) {
        return salario + (pagoPorHora * horasTrabajadas);
    }
}


