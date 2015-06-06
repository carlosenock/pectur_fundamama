package com.ucla.pectusmovil.modelo;


import java.util.ArrayList;
import java.util.List;

public class Clinica {
	
	private Integer id;
	private String nombre;
	private String rif;
	private String direccion;
	private String telefono;
	private List<EstudioClinica> listaEstudioClinica;
	private boolean estatus;
	
	public Clinica(){
		
	}
	
	public Clinica(Integer id, String nombre, String rif, String direccion,
			String telefono, List<EstudioClinica> listaEstudioClinica,
			boolean estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.rif = rif;
		this.direccion = direccion;
		this.telefono = telefono;
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

	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
