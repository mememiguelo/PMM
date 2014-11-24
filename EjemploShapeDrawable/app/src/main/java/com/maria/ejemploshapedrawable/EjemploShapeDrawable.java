package com.maria.ejemploshapedrawable;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;

public class EjemploShapeDrawable extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VistaAMedida(this));
    }

    public class VistaAMedida extends View {
        private ShapeDrawable miDrawable;
        public VistaAMedida(Context contexto) {
            super(contexto);
            int x=10, y=10;
            int ancho=300, alto=50;
            miDrawable = new ShapeDrawable(new OvalShape());
            miDrawable.getPaint().setColor(0xff0000ff);
            miDrawable.setBounds(x, y, x + ancho, y + alto);


        }

        protected void onDraw(Canvas canvas) {
            int x=10, y=10;
            int ancho=300, alto=50;
            miDrawable = new ShapeDrawable(new OvalShape());
            miDrawable.getPaint().setColor(0xff0000ff);
            for(int i=0;i<10;i++){
                miDrawable.setBounds(x, y, x + ancho, y + alto);
                miDrawable.draw(canvas);
                x=x+(i*10);
                y=y+(i*10);
            }
            //miDrawable.setBounds(x, y, x + ancho, y + alto);
            //miDrawable.draw(canvas);
        }
    }

}
