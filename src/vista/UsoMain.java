package vista;
import java.util.*;

import control.EmpleadoController;
import modelo.Empleado;
public class UsoMain {

	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		System.out.println("Menu");
		System.out.println("1.Insertar");
		System.out.println("2.Consulta empleado");
		System.out.println("3.Modificar empleado");
		System.out.println("4.Consulta de lista de empleados");
		System.out.println("5.Borrar empleado");
		System.out.println("6.Salir");
		int opcion=teclado.nextInt();
		do {
			switch(opcion) {
			case 1:
				teclado.nextLine();
				Empleado e= new Empleado();
				System.out.println("Introduzca dni");
				String dni=teclado.nextLine();
				e.setDni(dni);
				System.out.println("Introduzca nombre");
				String nombre=teclado.nextLine();
				e.setNombre(nombre);
				System.out.println("Introduzca apellidos");
				String apellido=teclado.nextLine();
				e.setApellido(apellido);
				System.out.println("Introduzca salario");
				double salario=teclado.nextDouble();
				e.setSalario(salario);
				if(new EmpleadoController().altaEmpleado(e)) {
					System.out.println("Empleado insertado");
				}else {
					System.out.println("Empleado no insertado");
				}
				teclado.nextLine();
				break;
			case 2:
				teclado.nextLine();
				System.out.println("Introduzca dni del empleado a buscar");
				String dniBuscar=teclado.nextLine();
				new EmpleadoController().consultarEmpleado(dniBuscar);
				break;
			case 3:
				teclado.nextLine();
				System.out.println("Introduzca dni del empleado a modificar");
				String dniModificar=teclado.nextLine();
				Empleado a= new Empleado();
				System.out.println("Introduzca dni");
				String dni1=teclado.nextLine();
				a.setDni(dni1);
				System.out.println("Introduzca nombre");
				String nombre1=teclado.nextLine();
				a.setNombre(nombre1);
				System.out.println("Introduzca apellidos");
				String apellido1=teclado.nextLine();
				a.setApellido(apellido1);
				System.out.println("Introduzca salario");
				double salario1=teclado.nextDouble();
				a.setSalario(salario1);
				if(new EmpleadoController().altaEmpleado(a)) {
					System.out.println("Empleado insertado");
				}else {
					System.out.println("Empleado no insertado");
				}
				teclado.nextLine();
				break;
			case 4:
				System.out.println("Lista de empleados");
				new EmpleadoController().listarEmpleados();
				System.out.println("=========================");
				ArrayList<Empleado>empleados = new EmpleadoController().mostrarEmpleados();
				for (Empleado empleado : empleados) {
					System.out.println(empleado);
				}
				
				break;
			case 5:
				teclado.nextLine();
				Empleado c=new Empleado();
				System.out.println("Introduzca dni del empleado a eliminar");
				String dniBorrar=teclado.nextLine();
				c.setDni(dniBorrar);
				if(new EmpleadoController().bajaEmpleado(c)) {
					System.out.println("El empleado se ha borrado");
				}else {
					System.out.println("Algo fallo");
				}
				break;
			case 6:
				System.out.println("Ha salido del programa");
				break;
			default:
				System.out.println("Ha elegido opcion erronea");
				break;
			}
			
		}while(opcion!=6);

	}

}
