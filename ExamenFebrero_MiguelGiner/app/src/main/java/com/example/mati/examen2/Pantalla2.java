package com.example.mati.examen2;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mati on 29/01/15.
 */
public class Pantalla2 extends Activity {

    public String zonaEnvio;
    public float costeFinal;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView zona = (TextView) findViewById(R.id.zona);
        final TextView tarifa = (TextView) findViewById(R.id.tarifa);
        final TextView peso = (TextView) findViewById(R.id.peso);
        final TextView decoracion = (TextView) findViewById(R.id.decoracion);
        final TextView total = (TextView) findViewById(R.id.total);


        Bundle miBundleRecoger = getIntent().getExtras();
        zona.setText(miBundleRecoger.getString("zona")+"("+miBundleRecoger.getString("region")+")");
        tarifa.setText(miBundleRecoger.getString("tarifa"));
        String texto = Integer.toString(miBundleRecoger.getInt("peso"));
        peso.setText(texto+"Kg");

        String decora="";
        if(miBundleRecoger.getBoolean("caja")){
            decora = "Con caja regalo";
        }
        if(miBundleRecoger.getBoolean("dedicatoria")){
            decora = decora + "Con dedicatoria";
        }
        decoracion.setText(decora);
        System.out.println(miBundleRecoger.getDouble("precioTotal"));
        costeFinal = (float)miBundleRecoger.getDouble("precioTotal");
        String texto2 = String.valueOf(miBundleRecoger.getDouble("precioTotal"));
        System.out.println(texto2);
        total.setText(texto2);
        zonaEnvio=miBundleRecoger.getString("zona");

    }

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
            //System.out.println("Click guardar DB");
            ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "DBEnvios", null, 1);

            final SQLiteDatabase bd = cliBDh.getWritableDatabase();

            Context context = getApplicationContext();
            CharSequence text = "Insertando en la base de datos";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            bd.execSQL("INSERT INTO Envios (ZonaEnvio, CosteFinal) " +
                    "VALUES ('" + zonaEnvio + "', "+ costeFinal+")");

            CharSequence text2 = "Insertado en la base de datos correctamente";
            int duration2 = Toast.LENGTH_SHORT;

            Toast toast2 = Toast.makeText(context, text, duration);
            toast.show();


            bd.close();


        }
        return super.onOptionsItemSelected(item);
    }

}
