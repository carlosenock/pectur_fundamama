package com.ucla.pectusmovil.modelo;


import java.io.Serializable;
import java.util.List;

public class Comision implements Serializable {
	
	private int id;
	private String nombre;
	private String descripcion;
	private List<Voluntario> voluntarios[];
	
	
	
	
	public Comision(int id, String nombre, String descripcion,
			List<Voluntario>[] voluntarios) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.voluntarios = voluntarios;
	}

    public Comision() {

    }


    public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<Voluntario>[] getVoluntarios() {
		return voluntarios;
	}
	public void setVoluntarios(List<Voluntario>[] voluntarios) {
		this.voluntarios = voluntarios;
	}
	
	
	

}
