package entrega2.Entrega;

import java.util.ArrayList;

public class Ronda {
	private ArrayList<Partido> partidos;
	private int nro;
	
	public Ronda(ArrayList<Partido> p, int n) {
		setPartidos(p);
		setNro(n);
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}
	public int puntos() {
		return 0;//ver puntos
	}
	public String toString() {
		String str= "\nRonda "+ nro;
		for(Partido p: this.partidos) {
			str= str + "\n" + p.getEquipo1().getNombre() + " " + p.getGolesEquipo1() + " - " + p.getGolesEquipo2() + " "+ p.getEquipo2().getNombre();
		}
		return str;
	}
}
