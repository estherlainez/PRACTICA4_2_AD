package modelo;

public class Empleado {
	private String dni;
	private String nombre;
	private String apellido;
	private double salario;
	
	public Empleado(String d,String n,String a,double s) {
		this.dni=d;
		this.nombre=n;
		this.apellido=a;
		this.salario=s;
	}

	public Empleado() {
		this.dni=dni;
		this.nombre=nombre;
		this.apellido=apellido;
		this.salario=salario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Empleado [Dni=" + dni + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Salario=" + salario + "]";
	}
	
	
}
