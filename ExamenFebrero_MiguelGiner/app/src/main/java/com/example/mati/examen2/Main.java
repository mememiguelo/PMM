package com.example.mati.examen2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


public class Main extends Activity {

    public Zonas[] zonas=
            new Zonas[]{
                    new Zonas("Zona A","Asia y Oceania",30),
                    new Zonas("Zona B","America y Africa", 20),
                    new Zonas("Zona C","Europa",10)
            };
    public Spinner miSpinner;
    public String tipoZona,tipoRegion;
    public int tipoPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton normal = (RadioButton)findViewById(R.id.tarifa_normal);
        final RadioButton urgente = (RadioButton)findViewById(R.id.tarifa_urgente);
        final CheckBox regalo = (CheckBox)findViewById(R.id.regalo);
        final CheckBox tarjeta = (CheckBox)findViewById(R.id.tarjeta);
        final EditText peso = (EditText)findViewById(R.id.peso_paquete);
        final Button calculos = (Button)findViewById(R.id.calculos);
        miSpinner = (Spinner) findViewById(R.id.spinner);

        AdaptadorFigura miAdaptador = new AdaptadorFigura(this);
        miSpinner.setAdapter(miAdaptador);

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

                tipoZona = zonas[position].getZona();
                tipoRegion=zonas[position].getRegion();
                tipoPrecio=zonas[position].getPrecio();
                //posicionClick = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calculos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int numpeso = Integer.parseInt(peso.getText().toString());
                boolean caja = false;
                boolean dedicatoria = false;
                if(regalo.isChecked()){
                    System.out.println("Regalo esta check");
                    caja=true;
                }
                if(tarjeta.isChecked()){
                    System.out.println("Tarjeta esta check");
                    dedicatoria=true;
                }

                double precio = tipoPrecio;
                double precioTotal;

                if(numpeso<=5){
                    precio = precio + numpeso;
                }else if(numpeso<=10 && numpeso>5){
                    precio = precio + (numpeso*1.5);
                }else if(numpeso>10){
                    precio = precio +(numpeso*2);
                }

                System.out.println(tipoZona +" "+ tipoRegion +" "+ tipoPrecio +" "+ numpeso);
                String tarifa;
                if(normal.isChecked()){
                    System.out.println("normal esta check");
                    tarifa="normal";
                    precioTotal = precio;
                }else{
                    System.out.println("urgente esta check");
                    tarifa="urgente";
                    precioTotal = precio + (precio*0.3);
                }
                System.out.println("Precio total: "+ precioTotal);

                Intent miIntent= new Intent(Main.this,Pantalla2.class);
                Bundle miBundle = new Bundle();
                miBundle.putString("zona",tipoZona);
                miBundle.putString("region",tipoRegion);
                miBundle.putString("tarifa",tarifa);
                //miBundle.putInt("precio",tipoPrecio);
                miBundle.putInt("peso",numpeso);
                miBundle.putBoolean("caja",caja);
                miBundle.putBoolean("dedicatoria",dedicatoria);
                miBundle.putDouble("precioTotal", precioTotal);
                miIntent.putExtras(miBundle);
                startActivity(miIntent);
            }
        });


    }

            class AdaptadorFigura extends ArrayAdapter<Zonas> {
                public Activity Actividad;

                public AdaptadorFigura(Activity laActividad) {
                    super(laActividad, R.layout.activity_contenido_spinner, zonas);
                    this.Actividad = laActividad;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View vistaDesplegada = getView(position, convertView, parent);
                    return vistaDesplegada;
                }

                public View getView(int position, View convertView, ViewGroup parent) {
                    View item = convertView;
                    ViewHolder holder;

                    if (item == null) {
                        LayoutInflater inflater = Actividad.getLayoutInflater();
                        item = inflater.inflate(R.layout.activity_contenido_spinner, null);
                        holder = new ViewHolder();
                        holder.tipo = (TextView) item.findViewById(R.id.campoTipo);
                        item.setTag(holder);
                    } else {
                        holder = (ViewHolder) item.getTag();
                    }
                    holder.tipo.setText(zonas[position].getZona() + "\n" + zonas[position].getRegion() + "\n" + zonas[position].getPrecio()+"€");
                    //holder.tipo.setText(zonas[position].getZona());
                    return item;
                }
            }

/*
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    return true;
                }
                if (id == R.id.action_saveDB) {
                    System.out.println("Click guardar DB");
                }
                return super.onOptionsItemSelected(item);
            }*/
        }
