package com.maria.pasoentreactividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Maria on 17/09/2014.
 */
public class Pantalla2 extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

       final TextView otroSaludo= (TextView)findViewById(R.id.miMensaje);
       final Button volverBtn= (Button)findViewById(R.id.miVolver);
       //Bundle miBundleRecoger = getIntent().getExtras();
       //final Persona pp=miBundleRecoger.getParcelable("laPersona");
           final Persona pp = (Persona)getIntent().getParcelableExtra(EntreActividades.PARCEL_KEY);
            otroSaludo.setText(pp.toString());

          volverBtn.setOnClickListener( new View.OnClickListener(){
                public void onClick(View v){
                    Intent vueltaIntent= new Intent(Pantalla2.this, EntreActividades.class);
                    Bundle vueltaBundle=new Bundle();
                    String mensajeDevuelto= "Devuelvo a Principal " + pp.toString();
                    vueltaBundle.putString("DEVUELTO", mensajeDevuelto);
                    vueltaIntent.putExtras(vueltaBundle);
                    setResult(RESULT_OK, vueltaIntent);
                    finish();


                }

            });


    }
    }
