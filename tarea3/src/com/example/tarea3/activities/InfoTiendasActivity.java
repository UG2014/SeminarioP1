package com.example.tarea3.activities;




import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tarea3.R;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
public class InfoTiendasActivity extends Activity {
 
	String Tienda=" ";
	ScrollView inputControls;
	Button btn2;
	Button btn;
	
	String objID;
	
	String NAME;
	String ADDRES;
	String HORARIO;
	String EMAIL;
	String WEBSITE;
	String tel;
	String msg_shared;
	TextView name;
	TextView adress;
	TextView phone;
	TextView horario;
	TextView website;
	TextView email;
	
	
	public static String numberTienda="Tienda";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_tiendas);
		Intent intent = getIntent();
		
	     Tienda = intent.getStringExtra(numberTienda);
		
	     name = (TextView)findViewById(R.id.TextViewName);
		 adress = (TextView)findViewById(R.id.textViewAdress);
		 phone = (TextView)findViewById(R.id.textViewPhone);
		 horario = (TextView)findViewById(R.id.textViewHorario);
		 website = (TextView)findViewById(R.id.textViewWebSite);
		 email = (TextView)findViewById(R.id.textViewEmail);
		
		
		
		btn = (Button)findViewById(R.id.buttonMore);
		btn.setOnClickListener(new ButtonListener());
		btn2 = (Button)findViewById(R.id.buttonCall);
		btn2.setOnClickListener(new ButtonListener());

		Parse.initialize(this, "u18PqZKLP8ArS6aaEpoJXRQCczrjR78sttjxWEd1", "ZUrYcWpeivbMkUMHFlerwCh0WkLbOxAxtnN5g8cu");
		Log.e("TIENDA",Tienda);	
		if(Tienda.equals("0")){
				objID="M7wnm9jGzB";
		}else if (Tienda.equals("1")){
			
				objID="gNjZk6rR5R";
		}else if (Tienda.equals("2")){
				objID="6PoS09laZL";
		}else if (Tienda.equals("3")){
				objID="s7C2h3pSlC";
		}else if (Tienda.equals("4")){
				objID="Vda0Qe23pb";
		}else if (Tienda.equals("5")){
				objID="0bXV8UCnb5";
		}else if (Tienda.equals("6")){
				objID="qNCSCeOiAG";
		}else if (Tienda.equals("7")){
				objID="fn5yLWAuT2";
		}else{
			objID="NOTFOUND";
		}
		Log.e("ObjID",objID);
		
		parse();
	
	Linkify.addLinks(adress,Linkify.ALL);
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
				inten.putExtra("id", Tienda);
				
			}else if (v.getId() == btn2.getId()){
			 inten= new Intent(Intent.ACTION_CALL);
			inten.setData(Uri.parse("tel:"+tel));
					
				}
			startActivity(inten);
			
		}
		
	}

	private void parse() {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("TAREA3");	
		query.getInBackground(objID, new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject obj, ParseException arg1) {
				if(obj != null){
					NAME =  obj.getString("NAME");
					ADDRES =  obj.getString("ADDRES");
					HORARIO =obj.getString("HORARIO");
					tel = obj.getString("PHONE");
					EMAIL =obj.getString("EMAIL");
					WEBSITE =obj.getString("WEBSITE");
					msg_shared = obj.getString("MSG_SHARED");
			
					name.setText(NAME);		
					adress.setText(ADDRES);
					phone.setText(tel);
					horario.setText(HORARIO);
					website.setText(WEBSITE);
					email.setText(EMAIL);
				
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.coments_menu, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		
		switch (item.getItemId()) {
		
		case R.id.menu_favorito:	
			return true;
		case R.id.menu_compartir:
			
				//String url = "http://es.m.wikipedia.org/wiki/";
				String msg = msg_shared;
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);	
				intent.putExtra(Intent.EXTRA_TEXT,msg);
				intent.setType("text/plain");
				startActivity(Intent.createChooser(intent, getResources().getText(R.string.action_share)));
			
			return true;
		default :
			return super.onOptionsItemSelected(item);
		
		
		
		
		
	}
}

}
