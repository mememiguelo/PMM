package com.example.mati.examen2;

/**
 * Created by mati on 29/01/15.
 */
public class Zonas {
    protected String zona;
    protected String region;
    protected int precio;


    public Zonas(String z,String r,int p) {
        zona = z;
        region = r;
        precio = p;


    }

    public String getZona() {
        return zona;
    }

    public String getRegion() {
        return region;
    }

    public int getPrecio() {
        return precio;
    }
}
