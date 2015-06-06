package com.ucla.pectusmovil.modelo;


import java.util.ArrayList;
import java.util.List;

public class Diagnostico {

	private Integer id;
	private String nombre;
	private String observacion;
	private List<Ayuda> listaAyudas;
	private boolean estatus;
	
	public Diagnostico(){
		
	}

	

	public Diagnostico(Integer id, String nombre, String observacion,
			List<Ayuda> listaAyudas, boolean estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.observacion = observacion;
		this.listaAyudas = listaAyudas;
		this.estatus = estatus;
	}



	public List<Ayuda> getListaAyudas() {
		if(listaAyudas == null){
			listaAyudas = new ArrayList<Ayuda>();
		}
		return listaAyudas;
	}



	public void setListaAyudas(List<Ayuda> listaAyudas) {
		this.listaAyudas = listaAyudas;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
	
}
