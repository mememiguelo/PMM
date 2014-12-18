package com.maria.pasoentreactividades;

/**
 * Created by Maria on 02/12/2014.
 */
import android.os.Parcel;
import android.os.Parcelable;
public class Persona implements Parcelable{
        private int id;
        private String nombre;
        private String apellidos;

         public static final Parcelable.Creator<Persona> CREATOR = new Parcelable.Creator<Persona>()
                {
                     public Persona createFromParcel(Parcel parcel)
                     {
                        Persona per= new Persona();
                        per.id = parcel.readInt();
                        per.nombre = parcel.readString();
                        per.apellidos = parcel.readString();
                        return(per);
                    }

                    @Override
                    public Persona[] newArray(int size)
                    {
                        return new Persona[size];
                    }
                };

        @Override
        public void writeToParcel(Parcel parcel, int flags)
        {   parcel.writeInt(id);
            parcel.writeString(nombre);
            parcel.writeString(apellidos);
        }

        @Override
        public int describeContents()
        {
            return 0;
        }

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getNombre()
        {
            return nombre;
        }

        public void setNombre(String nombre)
        {
            this.nombre = nombre;
        }

        public String getApellidos()
        {
            return apellidos;
        }

        public void setApellidos(String apellidos)
        {
            this.apellidos = apellidos;
        }
         public String toString(){
             return id + "  " + nombre + "  " + apellidos;
         }

}
