package com.ucla.pectusmovil.modelo;


import com.ucla.pectusmovil.modelo.Ciudad;
import com.ucla.pectusmovil.modelo.Estado;
import com.ucla.pectusmovil.modelo.Seguro;

public class Paciente {
	
	
	private Integer id;
	private Estado estado;
	private Ciudad ciudad;
	private Seguro seguro;
	private String cedula;
	private String nombre;
	private String apellido;
	private String celular;
	private String fijo;
	private String direccion;
	private String correo;
	private String fechaNacimiento;
	private String profesion;
	private char estadoCivil;
	private Integer   nroHijos;
	private String cedulaConyugue;
	private String nombreConyugue;
	private String apellidoConyugue;
	private String ocupacionConyugue;
	private char tipoVivienda;
	private Integer nroHabitantes;
	private char tendenciaVivienda;
	private Double alquiler;
	private String lugarTrabajo;
	private String direccionTrabajo;
	private String telefonoTrabajo;
	private float ingresos;
	private float egresos;
	private boolean estatus;
	
	
	public  Paciente(){
		
	}
	
	public Paciente(Integer id, Estado estado, Ciudad ciudad, Seguro seguro,
			String cedula, String nombre, String apellido, String celular,
			String fijo, String direccion, String correo,
			String fechaNacimiento, String profesion, char estadoCivil,
			Integer nroHijos, String cedulaConyugue, String nombreConyugue,
			String apellidoConyugue, String ocupacionConyugue,
			char tipoVivienda, Integer nroHabitantes, char tendenciaVivienda,
			Double alquiler, String lugarTrabajo, String direccionTrabajo,
			String telefonoTrabajo, float ingresos, float egresos,
			boolean estatus) {
		super();
		this.id = id;
		this.estado = estado;
		this.ciudad = ciudad;
		this.seguro = seguro;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.fijo = fijo;
		this.direccion = direccion;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.profesion = profesion;
		this.estadoCivil = estadoCivil;
		this.nroHijos = nroHijos;
		this.cedulaConyugue = cedulaConyugue;
		this.nombreConyugue = nombreConyugue;
		this.apellidoConyugue = apellidoConyugue;
		this.ocupacionConyugue = ocupacionConyugue;
		this.tipoVivienda = tipoVivienda;
		this.nroHabitantes = nroHabitantes;
		this.tendenciaVivienda = tendenciaVivienda;
		this.alquiler = alquiler;
		this.lugarTrabajo = lugarTrabajo;
		this.direccionTrabajo = direccionTrabajo;
		this.telefonoTrabajo = telefonoTrabajo;
		this.ingresos = ingresos;
		this.egresos = egresos;
		this.estatus = estatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public Seguro getSeguro() {
		return seguro;
	}
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getFijo() {
		return fijo;
	}
	public void setFijo(String fijo) {
		this.fijo = fijo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public char getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(char estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Integer getNroHijos() {
		return nroHijos;
	}
	public void setNroHijos(Integer nroHijos) {
		this.nroHijos = nroHijos;
	}
	public String getCedulaConyugue() {
		return cedulaConyugue;
	}
	public void setCedulaConyugue(String cedulaConyugue) {
		this.cedulaConyugue = cedulaConyugue;
	}
	public String getNombreConyugue() {
		return nombreConyugue;
	}
	public void setNombreConyugue(String nombreConyugue) {
		this.nombreConyugue = nombreConyugue;
	}
	public String getApellidoConyugue() {
		return apellidoConyugue;
	}
	public void setApellidoConyugue(String apellidoConyugue) {
		this.apellidoConyugue = apellidoConyugue;
	}
	public String getOcupacionConyugue() {
		return ocupacionConyugue;
	}
	public void setOcupacionConyugue(String ocupacionConyugue) {
		this.ocupacionConyugue = ocupacionConyugue;
	}
	public char getTipoVivienda() {
		return tipoVivienda;
	}
	public void setTipoVivienda(char tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}
	public Integer getNroHabitantes() {
		return nroHabitantes;
	}
	public void setNroHabitantes(Integer nroHabitantes) {
		this.nroHabitantes = nroHabitantes;
	}
	public char getTendenciaVivienda() {
		return tendenciaVivienda;
	}
	public void setTendenciaVivienda(char tendenciaVivienda) {
		this.tendenciaVivienda = tendenciaVivienda;
	}
	public Double getAlquiler() {
		return alquiler;
	}
	public void setAlquiler(Double alquiler) {
		this.alquiler = alquiler;
	}
	public String getLugarTrabajo() {
		return lugarTrabajo;
	}
	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}
	public String getDireccionTrabajo() {
		return direccionTrabajo;
	}
	public void setDireccionTrabajo(String direccionTrabajo) {
		this.direccionTrabajo = direccionTrabajo;
	}
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}
	public float getIngresos() {
		return ingresos;
	}
	public void setIngresos(float f) {
		this.ingresos = f;
	}
	public float getEgresos() {
		return egresos;
	}
	public void setEgresos(float f) {
		this.egresos = f;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	
	

	


}
