package com.example.sp1.tarea1;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoTiendasActivity extends Activity {
 
	String Tienda="";
	ScrollView inputControls;
	Button btn2;
	Button btn;
	String tel;
	public static String numberTienda="Tienda";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_tiendas);
		

	
	    Intent intent = getIntent();
		Tienda = intent.getStringExtra(numberTienda);
		//Toast.makeText(getApplicationContext(), Tienda, Toast.LENGTH_SHORT).show();
		TextView name = (TextView)findViewById(R.id.TextViewName);
		TextView adress = (TextView)findViewById(R.id.textViewAdress);
		TextView phone = (TextView)findViewById(R.id.textViewPhone);
		
		TextView horario = (TextView)findViewById(R.id.textViewHorario);
		TextView website = (TextView)findViewById(R.id.textViewWebSite);
		
		TextView email = (TextView)findViewById(R.id.textViewEmail);
		
		
		btn = (Button)findViewById(R.id.buttonMore);
		btn.setOnClickListener(new ButtonListener());
		btn2 = (Button)findViewById(R.id.buttonCall);
		btn2.setOnClickListener(new ButtonListener());
	if (Tienda.equals("0")){
		name.setText("Tienda de Vinos");
		adress.setText("12 Calle 23 ave Zona 12");
		tel="7812-2323";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("vinos@venta.com");
		
	}else if (Tienda.equals("1")){
		name.setText("Tienda de Lego");
		adress.setText("12 Calle 23 ave Zona 12");
		tel="5815-2323";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("lego@venta.com");
		
	}else if (Tienda.equals("2")){
		name.setText("Tienda de Libros");
		adress.setText("22 ave 23 ave Zona 12");
		tel="2815-2523";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("lego@venta.com");
		
	}else if (Tienda.equals("3")){
		name.setText("Tienda de Zapatos");
		adress.setText("12 Calle 2 ave Zona 1");
		tel="4815-2323";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("lego@venta.com");
		//,"Tienda de Ropa","Tienda de vinos","Tienda de libros","Tienda de Ropa"};
	}else if (Tienda.equals("4")){
		name.setText("Tienda de Ropa");
		adress.setText("12 Calle 23 ave Zona 12");
		tel="7815-2323";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("lego@venta.com");
		
	}else if (Tienda.equals("5")){
		name.setText("Tienda de Vino");
		adress.setText("12 Calle 23 ave Zona 12");
		tel="5815-2323";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("lego@venta.com");
		
	}else if (Tienda.equals("6")){
		name.setText("Tienda de Libros");
		adress.setText("12 Calle 23 ave Zona 12");
		tel="8815-2323";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("lego@venta.com");
		
	}else if (Tienda.equals("7")){
		name.setText("Tienda de Ropa");
		adress.setText("12 Calle 23 ave Zona 12");
		tel="9815-2323";
		phone.setText(tel);
		horario.setText("Lunes a Viernes: 9:00am - 6:00pm \n Sabados 7:00am a 1:00pm");
		website.setText("www.vinosventa.com");
		email.setText("lego@venta.com");
		
	}
	Linkify.addLinks(phone,Linkify.ALL);
	Linkify.addLinks(website,Linkify.ALL);
	Linkify.addLinks(email,Linkify.ALL);
		
		
	}
	class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent inten = null;
			if (v.getId() == btn.getId()){
			inten= new Intent(getApplicationContext(),ComentesMarketActivity.class);
				
				
			}else if (v.getId() == btn2.getId()){
			 inten= new Intent(Intent.ACTION_CALL);
			inten.setData(Uri.parse("tel:"+tel));
					
				}
			startActivity(inten);
			
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_info_tiendas, menu);
		return true;
	}

}
