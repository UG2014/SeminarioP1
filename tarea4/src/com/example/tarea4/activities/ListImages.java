package com.example.tarea4.activities;

import android.graphics.Bitmap;


public class ListImages {
	private String nombre;
	private int drawableImageID;
	private String bitmap;
	private int code;
	private Bitmap bmap;

	public ListImages(String nombre, String bitmap, int code,Bitmap bmap) {
		this.nombre = nombre;
		this.bitmap =bitmap;
		this.code = code;
		this.bmap=bmap;
	}


		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public int getDrawableImageID() {
			return drawableImageID;
		}

		public void setDrawableImageID(int drawableImageID) {
			this.drawableImageID = drawableImageID;
		}


		public String getBitmap() {
			return bitmap;
		}


		public void setBitmap(String bitmap) {
			this.bitmap = bitmap;
		}


		public int getCode() {
			return code;
		}


		public void setCode(int code) {
			this.code = code;
		}


		public Bitmap getBmap() {
			return bmap;
		}


		public void setBmap(Bitmap bmap) {
			this.bmap = bmap;
		}


	




	
}
