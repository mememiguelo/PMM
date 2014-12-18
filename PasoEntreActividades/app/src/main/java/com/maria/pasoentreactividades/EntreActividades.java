package com.maria.pasoentreactividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EntreActividades extends Activity {

    public static int COD_RESPUESTA=0;
    public static String PARCEL_KEY="com.maria.pasoentreactividades.laPersona";
    protected EditText elId;
    protected  EditText elNombre;
    protected  EditText elApellido;
    TextView losDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entre_actividades);
        elId = (EditText) findViewById(R.id.identificador);
        elNombre = (EditText) findViewById(R.id.nombre);
        elApellido = (EditText) findViewById(R.id.apellido);


        final Button miBoton = (Button) findViewById(R.id.miBtn);
        losDatos = (TextView) findViewById(R.id.miLbl);

       elId.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean b){
                if (b)  elId.setText("");

            }
        });
        elNombre.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean b){
                if (b)  elNombre.setText("");

            }
        });
        elApellido.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean b){
                if (b)  elApellido.setText("");

            }
        });


        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pasoParcelable();
            }

        });
    }

     public void pasoParcelable() {
        Persona p=new Persona();
        String iden=elId.getText().toString();
        p.setId(Integer.parseInt(iden));
        p.setNombre(elNombre.getText().toString());
        p.setApellidos(elApellido.getText().toString());
        Intent miIntent= new Intent(EntreActividades.this, Pantalla2.class);
        Bundle miBundle=new Bundle();
        miBundle.putParcelable(PARCEL_KEY, p);
        miIntent.putExtras(miBundle);
        startActivityForResult(miIntent,COD_RESPUESTA);
     }


   public void onActivityResult(int cod_resp, int cod_result,Intent intent){
        if (cod_result== RESULT_OK) {
            Bundle otroBundle = intent.getExtras();
            losDatos.setText(otroBundle.getString("DEVUELTO").toString());
        }
    }
}
