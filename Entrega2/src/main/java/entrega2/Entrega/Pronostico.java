package entrega2.Entrega;

public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;
	
	public Pronostico() {
		
	}
	public Pronostico(Partido p, Equipo e, ResultadoEnum res) {
		this.setPartido(p);
		this.setEquipo(e);
		this.setResultado(res);
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo e) {
		this.equipo = e;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public ResultadoEnum getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoEnum resultado) {
		this.resultado = resultado;
	}

	public int puntos() {
		ResultadoEnum resultadoReal = this.partido.resultado(this.equipo);
		if (this.resultado == resultadoReal) {
			return 1;
		} else {
			return 0;
		}
	}
	public String toString() {
		return "a";
	}
}
