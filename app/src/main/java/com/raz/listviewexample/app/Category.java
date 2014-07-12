package com.raz.listviewexample.app;

/**
 * Created by raz on 11/07/14.
 */
public class Category {
    private String Categoria;
    private int CategoriaID;

    public String getCategoria() {
        return Categoria;
    }

    public int getCategoriaID() {
        return CategoriaID;
    }

    @Override
    public String toString() {
        return getCategoria();
    }
}
