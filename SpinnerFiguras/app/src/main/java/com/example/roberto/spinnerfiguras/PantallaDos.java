package com.example.roberto.spinnerfiguras;

/**
 * Created by Miguel on 03/01/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PantallaDos extends Activity {
    private  String variable;
    private int intAncho, intAlto, intRadio;
    public double area, area2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_dos);

        variable = getIntent().getStringExtra("seleccionTipo");

       if (variable.equals("Rectangulo")) {
            intAncho = Integer.parseInt(getIntent().getStringExtra("rectanguloAncho"));
            intAlto = Integer.parseInt(getIntent().getStringExtra("rectanguloAlto"));
            setContentView(new DibujoRectangulo(this));
        }

        if (variable.equals("Circulo")) {
            intRadio = Integer.parseInt(getIntent().getStringExtra("circuloRadio"));
            setContentView(new DibujoCirculo(this));

        }


    }

    class DibujoRectangulo extends View {
        public DibujoRectangulo(Context c) {
            super(c);
        }
        protected void onDraw(Canvas lienzo) {
            int altoInicio=lienzo.getHeight()/2;
            int anchoInicio=lienzo.getWidth()/3;

            //lienzo.drawColor(Color.GRAY);
            Paint miPincel = new Paint();

            miPincel.setColor(Color.BLACK);
            miPincel.setTextSize(25);
            area2= (double) (intAncho)*(intAlto);
            lienzo.drawText("Area: "+String.valueOf(area2), 100, 200, miPincel);

            miPincel.setStyle(Paint.Style.STROKE);
            lienzo.drawRect(anchoInicio, altoInicio, anchoInicio+intAncho, altoInicio+intAlto, miPincel);


        }
    }

    class DibujoCirculo extends View {
        public DibujoCirculo(Context c) {
            super(c);
        }
        protected void onDraw(Canvas lienzo) {

            int altoPantalla=lienzo.getHeight()/2;
            int anchoPantalla=lienzo.getWidth()/2;

            //lienzo.drawColor(Color.GRAY);
            Paint miPincel = new Paint();

            miPincel.setColor(Color.BLACK);
            miPincel.setTextSize(25);
            area=(3.1416 * Math.pow((intRadio*1.0),2));
            lienzo.drawText("Area: "+String.valueOf(area), 100, 200, miPincel);

            miPincel.setStyle(Paint.Style.STROKE);
            lienzo.drawCircle(anchoPantalla, altoPantalla ,intRadio, miPincel);



        }
    }


    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
