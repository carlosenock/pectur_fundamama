package com.ucla.pectusmovil.modelo;

import java.util.List;

public class EstudioClinica {
	
	private Integer id;
	private Clinica clinica;
	private Estudio estudio;
	private Double monto;
	private boolean estatus;
	private List<EstudioSolicitud> listaEstudiosSolicitud;
	
	public EstudioClinica(){
		
	}
	
	
	
	

	public EstudioClinica(Integer id, Clinica clinica, Estudio estudio,
			Double monto, boolean estatus,
			List<EstudioSolicitud> listaEstudiosSolicitud) {
		super();
		this.id = id;
		this.clinica = clinica;
		this.estudio = estudio;
		this.monto = monto;
		this.estatus = estatus;
		this.listaEstudiosSolicitud = listaEstudiosSolicitud;
	}





	public List<EstudioSolicitud> getListaEstudiosSolicitud() {
		return listaEstudiosSolicitud;
	}





	public void setListaEstudiosSolicitud(
			List<EstudioSolicitud> listaEstudiosSolicitud) {
		this.listaEstudiosSolicitud = listaEstudiosSolicitud;
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	

}
