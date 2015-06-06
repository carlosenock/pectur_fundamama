package com.ucla.pectusmovil.modelo;


import java.util.ArrayList;
import java.util.List;

public class Estudio {

	private Integer id;
	private String nombre;
	private String descripcion;
	private List<EstudioClinica> listaEstudioClinica;
	private boolean estatus;
	
	public Estudio(){
		
	}

	public Estudio(Integer id, String nombre, String descripcion,
			List<EstudioClinica> listaEstudioClinica, boolean estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.listaEstudioClinica = listaEstudioClinica;
		this.estatus = estatus;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<EstudioClinica> getListaEstudioClinica() {
		if(listaEstudioClinica == null){
			listaEstudioClinica = new ArrayList<EstudioClinica>();
		}
		return listaEstudioClinica;
	}

	public void setListaEstudioClinica(List<EstudioClinica> listaEstudioClinica) {
		this.listaEstudioClinica = listaEstudioClinica;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	

}
