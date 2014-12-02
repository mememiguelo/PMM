package com.elbauldelprogramador.notificaciones;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class NotificacionesActivity extends Activity {
   
   private static final int HELLO_ID = 1;
   
   private Button toastButton;
   private Button barButton;
   private Button dialogButton;
   private Button progressButton;
   
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        toastButton = (Button) findViewById(R.id.toastButton);
        toastButton.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
            mostrarToast();
         }
      });
        
        barButton = (Button) findViewById(R.id.barButton);
        barButton.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
            mostrarNotBarra();
         }
      });
        
        dialogButton = (Button) findViewById(R.id.DialogButton);
        dialogButton.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
            mostrarAlertDialogo();
         }
      });
        
        progressButton = (Button) findViewById(R.id.ProgressButton);
        progressButton.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
            mostrarProgressDialog();
         }
      });
    }
    
    private void mostrarToast(){
       LayoutInflater inflater = getLayoutInflater();
       View layout = inflater.inflate(
             R.layout.toast_layout
            ,(ViewGroup) findViewById(R.id.toastLayout));

       Toast toast = new Toast(getApplicationContext());
       toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
       toast.setDuration(Toast.LENGTH_LONG);
       toast.setView(layout);
       toast.show();
    }
    
    private void mostrarNotBarra(){
       NotificationManager mNotificationManager = 
          (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       
       //Agregando el icono, texto y momento para lanzar la notificación
       int icon = R.drawable.ok;
       CharSequence tickerText = "Notification Bar";
       long when = System.currentTimeMillis();

       Notification notification = new Notification(icon, tickerText, when);
       
       Context context = getApplicationContext();
       CharSequence contentTitle = "Notificación en barra";
       CharSequence contentText = "Mensaje corto de la notificación";
       
       //Agregando sonido
       notification.defaults |= Notification.DEFAULT_SOUND;
       //Agregando vibración
       notification.defaults |= Notification.DEFAULT_VIBRATE;
       
       Intent notificationIntent = new Intent(this, NotificacionesActivity.class);
       PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
       notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

       mNotificationManager.notify(HELLO_ID, notification);
    }
    
    private void mostrarAlertDialogo(){
       AlertDialog.Builder dialog = new AlertDialog.Builder(this);
       
       dialog.setMessage("¿Salir?");
       dialog.setCancelable(false);
       dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
         
         @Override
         public void onClick(DialogInterface dialog, int which) {
            NotificacionesActivity.this.finish();
         }
      });
       dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
         
         @Override
         public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
         }
      });
       dialog.show();
    }
    
    private void mostrarProgressDialog(){
       ProgressDialog.show(
             NotificacionesActivity.this
            ,"ProgressDialog"
            ,"Ejemplo diálogo de progreso"
            ,true
            ,true);
    }
}