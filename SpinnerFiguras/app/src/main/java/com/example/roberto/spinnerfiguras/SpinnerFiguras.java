package com.example.roberto.spinnerfiguras;

/**
 * Created by Miguel on 03/01/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class SpinnerFiguras extends Activity{

    public Spinner miSpinner;

    public EditText etAncho,etAlto,etRadio;
    public TextView textViewRadio,textViewAncho,textViewAlto;
    public Button dibujar;
    public String tipoClick,stRadio,stAncho,stAlto;


    public Figuras[] figuras =
            new Figuras[]{
                    new Figuras("Elija figura:"),
                    new Figuras("Circulo"),
                    new Figuras("Rectangulo")
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_figuras);

        miSpinner = (Spinner) findViewById(R.id.spinner);

        etAncho=(EditText) findViewById(R.id.idAncho);
        textViewAncho=(TextView) findViewById(R.id.textViewAncho);
        etAncho.setVisibility(View.INVISIBLE);
        textViewAncho.setVisibility(View.INVISIBLE);

        etAlto=(EditText) findViewById(R.id.idAlto);
        textViewAlto=(TextView) findViewById(R.id.textViewAlto);
        etAlto.setVisibility(View.INVISIBLE);
        textViewAlto.setVisibility(View.INVISIBLE);

        etRadio=(EditText) findViewById(R.id.idRadio);
        textViewRadio=(TextView) findViewById(R.id.textViewRadio);
        etRadio.setVisibility(View.INVISIBLE);
        textViewRadio.setVisibility(View.INVISIBLE);

        AdaptadorFigura miAdaptador = new AdaptadorFigura(this);
        miSpinner.setAdapter(miAdaptador);

        dibujar=(Button) findViewById(R.id.buttonDibujar);
        dibujar.setVisibility(View.INVISIBLE);


        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

                tipoClick=figuras[position].getTipo();

                switch(position){
                    case 0:
                        ocultarRectangulo();
                        ocultarCirculo();
                        dibujar.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        dibujar.setVisibility(View.VISIBLE);
                        mostrarCirculo();
                        ocultarRectangulo();

                        dibujar.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                stRadio=etRadio.getText().toString();
                                pasoPantallaCirculo(tipoClick, stRadio);

                            }
                        });
                        break;

                    case 2:
                        dibujar.setVisibility(View.VISIBLE);
                        mostrarRectangulo();
                        ocultarCirculo();

                        dibujar.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                stAncho=etAncho.getText().toString();
                                stAlto=etAlto.getText().toString();
                                pasoPantallaRectangulo(tipoClick, stAncho, stAlto);

                            }
                        });

                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void ocultarCirculo(){
        etRadio.setVisibility(View.INVISIBLE);
        textViewRadio.setVisibility(View.INVISIBLE);
    }

    public void mostrarCirculo(){
        etRadio.setVisibility(View.VISIBLE);
        textViewRadio.setVisibility(View.VISIBLE);
    }

    public void ocultarRectangulo(){
        textViewAncho.setVisibility(View.INVISIBLE);
        etAncho.setVisibility(View.INVISIBLE);
        textViewAlto.setVisibility(View.INVISIBLE);
        etAlto.setVisibility(View.INVISIBLE);

    }
    public void mostrarRectangulo(){
        textViewAncho.setVisibility(View.VISIBLE);
        etAncho.setVisibility(View.VISIBLE);
        textViewAlto.setVisibility(View.VISIBLE);
        etAlto.setVisibility(View.VISIBLE);
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void pasoPantallaRectangulo(String tipoClick, String stAncho, String stAlto){

        Intent miIntent = new Intent(SpinnerFiguras.this, PantallaDos.class);
        Bundle miBundle = new Bundle();
        miBundle.putString("seleccionTipo", tipoClick);
        miBundle.putString("rectanguloAncho", stAncho);
        miBundle.putString("rectanguloAlto", stAlto);
        miBundle.putString("seleccionTipo", tipoClick);
        miIntent.putExtras(miBundle);
        startActivity(miIntent);

    }

    public void pasoPantallaCirculo(String tipoClick,String stRadio){

        Intent miIntent = new Intent(SpinnerFiguras.this, PantallaDos.class);
        Bundle miBundle = new Bundle();
        miBundle.putString("seleccionTipo", tipoClick);
        miBundle.putString("circuloRadio", stRadio);
        miBundle.putString("seleccionTipo", tipoClick);
        miIntent.putExtras(miBundle);

        startActivity(miIntent);
    }

    class AdaptadorFigura extends ArrayAdapter<Figuras> {
        public Activity Actividad;

        public AdaptadorFigura(Activity laActividad){
            super(laActividad, R.layout.activity_contenido_spinner, figuras);
            this.Actividad=laActividad;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent){
            View vistaDesplegada=getView(position, convertView, parent);
            return vistaDesplegada;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            View item=convertView;
            ViewHolder holder;

            if(item==null) {
                LayoutInflater inflater = Actividad.getLayoutInflater();
                item = inflater.inflate(R.layout.activity_contenido_spinner, null);
                holder = new ViewHolder();
                holder.tipo = (TextView) item.findViewById(R.id.campoTipo);
                item.setTag(holder);
            }else{
                holder=(ViewHolder)item.getTag();
            }
            holder.tipo.setText(figuras[position].getTipo());
            return item;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_spinner_figuras, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
