package com.example.mati.acalculadora;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


public class ACalculadora extends Activity {

    public Operacion[] operaciones =
            new Operacion[]{
                    new Operacion("Suma"),
                    new Operacion("Resta"),
                    new Operacion("Multiplicacion"),
                    new Operacion("Division")
            };
    public Spinner miSpinner;
    public String tipoClick;
    //int num1,num2,total;
    public int num1,num2,total;

    public static int COD_RESPUESTA=0;
    TextView viewResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acalculadora);

        final EditText numero1= (EditText)findViewById(R.id.numero1);
        final EditText numero2= (EditText)findViewById(R.id.numero2);
        final RadioButton suma = (RadioButton)findViewById(R.id.radioSuma);
        final RadioButton resta = (RadioButton)findViewById(R.id.radioResta);
        final RadioButton multi = (RadioButton)findViewById(R.id.radioMulti);
        final Button miBoton= (Button)findViewById(R.id.buttonVolver);
        viewResultado = (TextView)findViewById(R.id.textView3);
        //final int num1;
        //final int num2;
        //final int total;

        miSpinner = (Spinner) findViewById(R.id.spinner);

        AdaptadorFigura miAdaptador = new AdaptadorFigura(this);
        miSpinner.setAdapter(miAdaptador);

        ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "DBOperaciones", null, 1);

        final SQLiteDatabase bd = cliBDh.getWritableDatabase();


        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

                tipoClick=operaciones[position].getTipo();

                        //num1 = Integer.parseInt(numero1.getText().toString());
                        //num2 = Integer.parseInt(numero2.getText().toString());
                        //int total;

                        switch(position){
                            case 0:
                                //total = num1 + num2 ;

                                miBoton.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        num1 = Integer.parseInt(numero1.getText().toString());
                                        num2 = Integer.parseInt(numero2.getText().toString());
                                        total = num1 + num2 ;
                                        System.out.println("Es "+tipoClick+" "+num1+" + "+num2+" y el total es : "+total);

                                        bd.execSQL("INSERT INTO Resultados (total, operacion) " +
                                                "VALUES (" + total + ", 'suma')");
                                        //stRadio=etRadio.getText().toString();
                                        //pasoPantallaCirculo(tipoClick, stRadio);

                                    }
                                });
                                break;
                            case 1:
                                //total = num1 - num2 ;
                                miBoton.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        num1 = Integer.parseInt(numero1.getText().toString());
                                        num2 = Integer.parseInt(numero2.getText().toString());
                                        total = num1 - num2;
                                        System.out.println("Es " + num1 + " - " + num2 + " y el total es : " + total);
                                        bd.execSQL("INSERT INTO Resultados (total, operacion) " +
                                                "VALUES (" + total + ", 'resta')");
                                        //stRadio=etRadio.getText().toString();
                                        //pasoPantallaCirculo(tipoClick, stRadio);

                                    }
                                });
                                break;
                            case 2:
                                miBoton.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        num1 = Integer.parseInt(numero1.getText().toString());
                                        num2 = Integer.parseInt(numero2.getText().toString());
                                        total = num1 * num2 ;
                                        System.out.println("Es "+num1+" x "+num2+" y el total es : "+total);
                                        bd.execSQL("INSERT INTO Resultados (total, operacion) " +
                                                "VALUES (" + total + ", 'multi')");
                                        //stRadio=etRadio.getText().toString();
                                        //pasoPantallaCirculo(tipoClick, stRadio);

                                    }
                                });
                                //System.out.println("Es multiplicacion y el total es : ");
                                break;
                            case 3:
                                miBoton.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        num1 = Integer.parseInt(numero1.getText().toString());
                                        num2 = Integer.parseInt(numero2.getText().toString());
                                        total = num1 / num2 ;
                                        System.out.println("Es "+num1+" / "+num2+" y el total es : "+total);
                                        bd.execSQL("INSERT INTO Resultados (total, operacion) " +
                                                "VALUES (" + total + ", 'divi')");
                                        //stRadio=etRadio.getText().toString();
                                        //pasoPantallaCirculo(tipoClick, stRadio);

                                    }
                                });
                                //System.out.println("Es multiplicacion y el total es : ");
                                break;
                        }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
/*
        miBoton.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){


                miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

                        tipoClick=operaciones[position].getTipo();

                        int num1 = Integer.parseInt(numero1.getText().toString());
                        int num2 = Integer.parseInt(numero2.getText().toString());
                        int total=0;

                        switch(position){
                            case 0:
                                total = num1 + num2 ;
                                System.out.println("Es "+num1+" + "+num2+" y el total es : "+total);
                                break;
                            case 1:
                                total = num1 - num2 ;
                                System.out.println("Es "+num1+" - "+num2+" y el total es : "+total);
                                break;
                            case 2:
                                total = num1 * num2 ;
                                System.out.println("Es "+num1+" x "+num2+" y el total es : "+total);
                                break;
                            case 3:
                                total = num1 / num2 ;
                                System.out.println("Es "+num1+" / "+num2+" y el total es : "+total);
                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                String resultado="";
                Intent miIntent= new Intent(ACalculadora.this,Pantalla2.class);
                Bundle miBundle = new Bundle();
                //String mensajePaso= "Te saludo " + miTexto.getText();
                // miBundle.putString("TEXTO", mensajePaso);
                //miIntent.putExtras(miBundle);
                //startActivityForResult(miIntent, COD_RESPUESTA);
                if(suma.isChecked()){
                    int num1 = Integer.parseInt(numero1.getText().toString());
                    int num2 = Integer.parseInt(numero2.getText().toString());
                    int total = num1 + num2 ;
                    resultado = "Resultado es : " + total;
                    miBundle.putString("HOLA", resultado);
                    miIntent.putExtras(miBundle);
                    startActivityForResult(miIntent, COD_RESPUESTA);
                    //viewResultado.setText(resultado);
                }else{
                    if(resta.isChecked()){
                        int num1 = Integer.parseInt(numero1.getText().toString());
                        int num2 = Integer.parseInt(numero2.getText().toString());
                        int total = num1 - num2 ;
                        resultado = "Resultado es :" + total;
                        miBundle.putString("HOLA", resultado);
                        miIntent.putExtras(miBundle);
                        startActivityForResult(miIntent, COD_RESPUESTA);
                        //viewResultado.setText(resultado);
                    }else{
                        if(multi.isChecked()){
                            int num1 = Integer.parseInt(numero1.getText().toString());
                            int num2 = Integer.parseInt(numero2.getText().toString());
                            int total = num1 * num2 ;
                            resultado = "Resultado es :" + total;
                            miBundle.putString("HOLA", resultado);
                            miIntent.putExtras(miBundle);
                            startActivityForResult(miIntent, COD_RESPUESTA);
                            //viewResultado.setText(resultado);
                        }
                    }
                }
            }
        });
*/
    }

    class AdaptadorFigura extends ArrayAdapter<Operacion> {
        public Activity Actividad;

        public AdaptadorFigura(Activity laActividad){
            super(laActividad, R.layout.activity_contenido_spinner, operaciones);
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
            holder.tipo.setText(operaciones[position].getTipo());
            return item;
        }
    }

    public void onActivityResult(int cod_resp, int cod_result,Intent intent){
        if (cod_result== RESULT_OK) {
            Bundle otroBundle = intent.getExtras();
            viewResultado.setText(otroBundle.getString("DEVUELTO"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acalculadora, menu);
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
        return super.onOptionsItemSelected(item);
    }
}
