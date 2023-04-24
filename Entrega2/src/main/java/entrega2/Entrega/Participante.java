package entrega2.Entrega;

public class Participante {
	private String nombre;
	private int puntaje;
	
	public Participante() {

	}
	
	public Participante(String n) {
		this.setNombre(n);
		this.setPuntaje(0);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public void incrementar() {
		this.puntaje= this.puntaje+1;
	}
	public String toString() {
		return "Participante: " + this.nombre +", puntaje: "+ this.puntaje + "\n";
	}
}
