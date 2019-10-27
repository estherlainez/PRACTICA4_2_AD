package control;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import modelo.Empleado;

public class EmpleadoController {
	String ruta="Empleados.dat";
	RandomAccessFile raf=null;
	int tRegistro=66;
	
	public EmpleadoController() {
		File f=new File(ruta);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int  getPosicion(String dni) {
		return Integer.valueOf(dni.substring(0, dni.length()-1))%10;
	}
	
	private boolean posOcupada(int pos) {
		try {
			raf=new RandomAccessFile(new File(this.ruta), "r");
			raf.seek(pos*this.tRegistro);
			if(raf.readChar()==' ') {
				raf.close();
				return false;
			}else {
				raf.close();
				return true;
			}
		} catch (FileNotFoundException e) {
		
		} catch (IOException e) {

			return false;
		}
		return true;
	}
	
	public boolean altaEmpleado(Empleado e) {
		if(!posOcupada(getPosicion(e.getDni()))) {
			try {
				raf=new RandomAccessFile(new File(this.ruta),"rw");
				if(raf.length()<=0) 
					raf.write(0);
				raf.seek(getPosicion(e.getDni())*tRegistro);
					
				StringBuffer sb=null;
				sb=new StringBuffer(e.getDni());
				sb.setLength(9);
				raf.writeChars(sb.toString());
						
				sb=new StringBuffer(e.getNombre());
				sb.setLength(10);
				raf.writeChars(sb.toString());
						
				sb=new StringBuffer(e.getApellido());
				sb.setLength(10);
				raf.writeChars(sb.toString());
						
				raf.writeDouble(e.getSalario());
				raf.close();
				
				return true;
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				return false;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
				
		}else {
			return false;
		
		}

	}
	
	public boolean modificarEmpleado(Empleado a) {
		if(posOcupada(getPosicion(a.getDni()))) {
			try {
				raf=new RandomAccessFile(new File(this.ruta),"rw");
				//me situo en el empleado a modificar
				raf.seek(getPosicion(a.getDni())*tRegistro);
					
				StringBuffer sb=null;
				sb=new StringBuffer(a.getDni());
				sb.setLength(9);
				raf.writeChars(sb.toString());
						
				sb=new StringBuffer(a.getNombre());
				sb.setLength(10);
				raf.writeChars(sb.toString());
						
				sb=new StringBuffer(a.getApellido());
				sb.setLength(10);
				raf.writeChars(sb.toString());
						
				raf.writeDouble(a.getSalario());
				raf.close();
				
				return true;
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				return false;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
				
		}else {
			return false;
		
		}

	}
	
	
	public void consultarEmpleado(String dniB){	
		if(posOcupada(getPosicion(dniB))) {
			try {
				raf= new RandomAccessFile(new File(this.ruta),"r");
				raf.seek(getPosicion(dniB)*tRegistro);//me situo en la posicion a ver
				String dni="";
				for(int i=0;i<9;i++) {
					dni+=raf.readChar();
				}
	
				String nombre="";
				for(int i=0;i<10;i++) {
					nombre+=raf.readChar();
				}
				
				String apellido="";
				for(int i=0;i<10;i++) {
					apellido+=raf.readChar();
				}
					
				double sal=raf.readDouble();
				System.out.println("Dni: "+dni+" Nombre:"+nombre+" Apellidos: "+apellido+" Salario "+sal);
					
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		
		}else {
			System.out.println("Error");
		}
		
		
	}

	
	public void listarEmpleados() {
		try {
			int posicion=0;
			raf= new RandomAccessFile(new File(this.ruta),"r");
			do {						
				raf.seek(posicion);//Nos situamos principio
				String dni="";
				for(int i=0;i<9;i++) {
					dni+=raf.readChar();
				}
				if(dni.trim().length()<=0)
					continue;
				String nombre="";
				for(int i=0;i<10;i++) {
					nombre+=raf.readChar();
				}
				String apellido="";
				for(int i=0;i<10;i++) {
					apellido+=raf.readChar();
				}
				
				double sal=raf.readDouble();
				System.out.println("Dni: "+dni+" Nombre:"+nombre+" Apellidos: "+apellido+" Salario "+sal);
									
				posicion += tRegistro;
				
			}while(raf.getFilePointer()!=raf.length());
			
			
		}catch (EOFException e) {			
			System.out.println("No hay empleados");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Empleado> mostrarEmpleados(){
		try {
			ArrayList<Empleado>misEmpleados=new ArrayList<Empleado>();
			raf= new RandomAccessFile(new File(this.ruta),"r");
			for(int pos=0;pos<raf.length();pos+=this.tRegistro) {
				String dni="";
				for(int i=0;i<9;i++) {
					dni+=raf.readChar();
				}
				if(dni.trim().length()<=0)
					continue;
				String nombre="";
				for(int i=0;i<10;i++) {
					nombre+=raf.readChar();
				}
				String apellido="";
				for(int i=0;i<10;i++) {
					apellido+=raf.readChar();
				}
				
				double sal=raf.readDouble();
				System.out.println("Dni: "+dni+" Nombre:"+nombre+" Apellidos: "+apellido+" Salario "+sal);
				misEmpleados.add(new Empleado(new String(dni),new String(nombre),new String(apellido),sal));
			}
			return misEmpleados;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public boolean bajaEmpleado(Empleado e) {
		if(posOcupada(getPosicion(e.getDni()))) {
			try {
				raf=new RandomAccessFile(new File(this.ruta),"rw");
				raf.seek(getPosicion(e.getDni())*tRegistro);
				//me situo en la posicion y escribo buffer vacio" "en el lugar del dni	
				//si fuera id se cambiaria por -1 en vez de " "
				StringBuffer sb=null;
				sb.setLength(9);
				sb=new StringBuffer(" ");
				
				raf.writeChars(sb.toString());
				raf.close();
				
				return true;
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				return false;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
				
		}else {
			return false;
		
		}

	}

}
