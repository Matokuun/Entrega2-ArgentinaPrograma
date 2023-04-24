package entrega2.Entrega;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App 
{
	public static void main(String[] args) {
		Path archivo =Paths.get(args[0]);
		Path archivo2=Paths.get(args[1]);		
		List<String> lineasResultados = null;
		List<String> lineasPronosticos = null;
		
		try {
			lineasResultados = Files.readAllLines(archivo);
		}
		catch (Exception e){
			System.out.println("hay problemas (seccion resultados): " + e);
		}
		
		try {
			lineasPronosticos = Files.readAllLines(archivo2);
		}
		catch (Exception e){
			System.out.println("hay problemas (seccion resultados): " + e);
		}
		
		ArrayList<Partido> partidosRonda= new ArrayList<Partido>();
		ArrayList<Ronda> rondas= new ArrayList<Ronda>();
		ArrayList<Participante> participantes= new ArrayList<Participante>();
		int numeroRonda=1;
		Ronda r= new Ronda(partidosRonda,1);
		
			//resultados...
		for (String linea : lineasResultados){
			String [] lineaCortada= linea.split(",");
			if(Integer.parseInt(lineaCortada[0])== numeroRonda) {
				Equipo e1= new Equipo(lineaCortada[1],"seleccion nacional");
				Equipo e2= new Equipo(lineaCortada[4],"seleccion nacional");
				Partido p= new Partido(e1,e2,Integer.parseInt(lineaCortada[2]),Integer.parseInt(lineaCortada[3]));
				partidosRonda.add(p);
			}
			else {
				r.setPartidos(partidosRonda);
				rondas.add(r);
				numeroRonda++;
				partidosRonda= new ArrayList<Partido>();
				r= new Ronda(partidosRonda,numeroRonda);
				Equipo e1= new Equipo(lineaCortada[1],"seleccion nacional");
				Equipo e2= new Equipo(lineaCortada[4],"seleccion nacional");
				Partido p= new Partido(e1,e2,Integer.parseInt(lineaCortada[2]),Integer.parseInt(lineaCortada[3]));
				partidosRonda.add(p);
			}
		}
		r.setPartidos(partidosRonda);
		rondas.add(r);
		rondas.stream().forEach(ronda -> System.out.println(ronda.toString()+"\n"));
			
		//analizando pronosticos...	
		Participante participante= new Participante();
		String nombreActual= "unknown";
		int i=0;
		for (String linea : lineasPronosticos){
			String [] lineaCortada= linea.split(",");
			if(i==0) { //en la primera iteracion si o si hay que crear un participante
				i++;
				nombreActual=lineaCortada[0];
				participante= new Participante(nombreActual);
			}
			else {
				if(!lineaCortada[0].equals(nombreActual)) {
					participantes.add(participante);
					nombreActual=lineaCortada[0];
					participante= new Participante(nombreActual);
				}
			}
			Equipo equipo1=new Equipo(lineaCortada[1],"seleccion nacional");
			Equipo equipo2=new Equipo(lineaCortada[5],"seleccion nacional");
			Partido partidoActual= null;
			//buscando partido, la unica desventaja que tiene es que si se repite un partido, se guarda el ultimo encontrado
			for (Ronda ronda : rondas) {
				for(Partido partido: ronda.getPartidos()) {
					if (partido.getEquipo1().getNombre().equals(equipo1.getNombre())
							&& partido.getEquipo2().getNombre().equals(equipo2.getNombre())){
						partidoActual = partido;
					}
				}
			}
			ResultadoEnum resultado = null;
			if(lineaCortada[2].equals("X")) {
				resultado = ResultadoEnum.GANADOR;
			}
			if(lineaCortada[3].equals("X")) {
				resultado = ResultadoEnum.EMPATE;
			}
			if(lineaCortada[4].equals("X")) {
				resultado = ResultadoEnum.PERDEDOR;
			}
			//System.out.println(partidoActual.toString());
			//a pronostico lo use nomas para ver si la prediccion es correcta, si lo es devuelve 1, sino 0
			Pronostico pronostico = new Pronostico(partidoActual, equipo1, resultado);
			if(pronostico.puntos()==1) {
				participante.incrementar();
				System.out.println(partidoActual.toString()+": SUMA");
			}
			else {
				System.out.println(partidoActual.toString()+": NO SUMA");
			}
		}
		participantes.add(participante);
		//fin del programa, imprimiendo resultados...
		System.out.println("\nPUNTAJES: \n");
		participantes.stream().forEach(persona -> System.out.println(persona.toString()));
	}
}
