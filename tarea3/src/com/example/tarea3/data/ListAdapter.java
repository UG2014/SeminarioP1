package com.example.tarea3.data;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tarea3.R;
import com.example.tarea3.activities.ListImages;

public class ListAdapter extends ArrayAdapter<ListImages>{
private Context context;
private ArrayList<ListImages> datos;
private String photoPath;
private int code;

public ListAdapter(Context context, ArrayList<ListImages> datos) {
    super(context, R.layout.info_list_image, datos);
    // Guardamos los parámetros en variables de clase.
    this.context = context;
    this.datos = datos;
    
}

public View getView(int position, View convertView, ViewGroup parent) {
    // En primer lugar "inflamos" una nueva vista, que será la que se
    // mostrará en la celda del ListView. Para ello primero creamos el
    // inflater, y después inflamos la vista.
	code = datos.get(position).getCode();
    LayoutInflater inflater = LayoutInflater.from(context);
    View item = inflater.inflate(R.layout.info_list_image, null);
 
    // A partir de la vista, recogeremos los controles que contiene para
    // poder manipularlos.
    // Recogemos el ImageView y le asignamos una foto.
    ImageView imagen = (ImageView) item.findViewById(R.id.imgList);
//    imagen.setImageResource(datos.get(position).getDrawableImageID());
    this.photoPath=datos.get(position).getBitmap();
    if(code == 0){
    imagen.setImageBitmap(BitmapFactory.decodeFile(datos.get(position).getBitmap()));
    }else  if(code == 1){
    		//	Bitmap bitmap = resizeBitmap(imagen.getWidth(), imagen.getHeight());
    			//imagen.setImageBitmap(bitmap);
    	imagen.setImageBitmap(BitmapFactory.decodeFile(datos.get(position).getBitmap()));
    }
    // Recogemos el TextView para mostrar el nombre y establecemos el
    // nombre.
    TextView nombre = (TextView) item.findViewById(R.id.Coment);
    nombre.setText(datos.get(position).getNombre());
    
    
    
 
 
    // Devolvemos la vista para que se muestre en el ListView.
    return item;
}


public Bitmap resizeBitmap(int targetW, int targetH) {
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(photoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
		}

		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		return BitmapFactory.decodeFile(photoPath, bmOptions);    	
  }
}
