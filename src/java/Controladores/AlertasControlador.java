/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kesgr
 */
@Named(value = "alertasControlador")
@RequestScoped
public class AlertasControlador {

    //----- Builders ---------------------------------------------------------\\

    public AlertasControlador() {
        abecedario.add("a");
        abecedario.add("b");
        abecedario.add("c");
        abecedario.add("d");
        abecedario.add("f");
        abecedario.add("g");
        abecedario.add("h");
        abecedario.add("i");
        abecedario.add("j");
        abecedario.add("k");
        abecedario.add("l");
        abecedario.add("m");
        abecedario.add("n");
        abecedario.add("o");
        abecedario.add("p");
        abecedario.add("q");
        abecedario.add("r");
        abecedario.add("s");
        abecedario.add("t");
        abecedario.add("u");
        abecedario.add("v");
        abecedario.add("w");
        abecedario.add("x");
        abecedario.add("y");
        abecedario.add("z");
    }


    //----- Attributes -------------------------------------------------------\\

    private String Mensaje;
    private List<String> abecedario = new ArrayList<>();
    

    //----- Methods ----------------------------------------------------------\\

    

    //----- Getters and Setters ----------------------------------------------\\
    
    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public List<String> getAbecedario() {
        return abecedario;
    }

    public void setAbecedario(List<String> abecedario) {
        this.abecedario = abecedario;
    }

}
