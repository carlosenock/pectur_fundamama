package com.ucla.pectusmovil.modelo;


import java.util.Date;

public class Cita {
	
	private Integer id;
	private Date fecha;
	private String hora;
	private Paciente paciente;
	private Clinica clinica;
	private Estudio estudio;
	private boolean estatus;
	
	public Cita(Integer id, Date fecha, String hora, Paciente paciente,
			Clinica clinica, Estudio estudio, boolean estatus) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.paciente = paciente;
		this.clinica = clinica;
		this.estudio = estudio;
		this.estatus = estatus;
	}
	
	public Cita(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
	
	

}
