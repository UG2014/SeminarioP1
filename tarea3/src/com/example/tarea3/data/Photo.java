package com.example.tarea3.data;

import java.util.LinkedList;

public class Photo {
	
	private String URL;
	private String Descripcion;
	private LinkedList<String> Listadodecomentarios;
	private int Numerodefavoritos;
	
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
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
	
	

}
