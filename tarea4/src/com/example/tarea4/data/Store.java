package com.example.tarea4.data;

import java.util.LinkedList;

public class Store {
	private String Direccion;
	private String Telefono;
	private String Horarios;
	private String Website;
	private String Email;
	private LinkedList<String> Listadodecomentarios;
	private int Numerodefavoritos;
	private String UbicacionGeografica;
	
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getHorarios() {
		return Horarios;
	}
	public void setHorarios(String horarios) {
		Horarios = horarios;
	}
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public LinkedList<String> getListadodecomentarios() {
		return Listadodecomentarios;
	}
	public void setListadodecomentarios(LinkedList<String> listadodecomentarios) {
		Listadodecomentarios = listadodecomentarios;
	}
	public int getNumerodefavoritos() {
		return Numerodefavoritos;
	}
	public void setNumerodefavoritos(int numerodefavoritos) {
		Numerodefavoritos = numerodefavoritos;
	}
	public String getUbicacionGeografica() {
		return UbicacionGeografica;
	}
	public void setUbicacionGeografica(String ubicacionGeografica) {
		UbicacionGeografica = ubicacionGeografica;
	}
	
	

}
