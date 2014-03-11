package com.seminarioP1.demo4.data;

import com.google.android.gms.maps.model.LatLng;

public class Place {
	private int id; 
	private String date;
	private String time; 
	private String author; 
	private String thumbnailURL;
	private LatLng loacalitation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getThumbnailURL() {
		return thumbnailURL;
	}
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	} 
	
	public String toString(){
		return "Lugar Creado: "+ date +" "+time;
	}
	public LatLng getLoacalitation() {
		return loacalitation;
	}
	public void setLoacalitation(LatLng loacalitation) {
		this.loacalitation = loacalitation;
	}
}
