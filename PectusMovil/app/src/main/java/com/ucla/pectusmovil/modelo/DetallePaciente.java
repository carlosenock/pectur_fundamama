package com.ucla.pectusmovil.modelo;


//MI CLASE MODELO DE ENTRADA CARTELERA
public class DetallePaciente {
	
	private String nombre;
	private String apellido;
    private String cedula;
	private String celular;
    private String telefono;
    private String profesion;
	  
	public DetallePaciente(String nombre,String apellido,String cedula,
                           String celular,String telefono,String profesion)
                           {
	    this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.celular = celular;
        this.telefono = telefono;
        this.profesion = profesion;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }


}
