package com.ucla.pectusmovil.modelo;

public class EstudioSolicitud {

	private Integer id;
	private Ayuda ayuda;
	private EstudioClinica estudioClinica;
	private boolean estatus;
	
	
	public EstudioSolicitud(){
		
	}


	public EstudioSolicitud(Integer id, Ayuda ayuda,
			EstudioClinica estudioClinica, boolean estatus) {
		super();
		this.id = id;
		this.ayuda = ayuda;
		this.estudioClinica = estudioClinica;
		this.estatus = estatus;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Ayuda getAyuda() {
		return ayuda;
	}


	public void setAyuda(Ayuda ayuda) {
		this.ayuda = ayuda;
	}


	public EstudioClinica getEstudioClinica() {
		return estudioClinica;
	}


	public void setEstudioClinica(EstudioClinica estudioClinica) {
		this.estudioClinica = estudioClinica;
	}


	public boolean isEstatus() {
		return estatus;
	}


	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
	
}
