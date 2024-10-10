public class EmpleadoFijo extends Empleado {
    public EmpleadoFijo(String nombre, double salario) throws SalarioInvalidoException {
        super(nombre, salario);
    }

    @Override
    public double calcularPago(int horasTrabajadas) throws HorasInvalidasException {
        if (horasTrabajadas < 0) {
            throw new HorasInvalidasException("Horas trabajadas no pueden ser negativas.");
        }
        return salario; // El salario fijo se paga independientemente de las horas trabajadas
    }
}


