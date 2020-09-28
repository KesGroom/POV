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

   

    //----- Attributes -------------------------------------------------------\\

    private String Mensaje;
    private String Grafica;
    

    //----- Methods ----------------------------------------------------------\\

    

    //----- Getters and Setters ----------------------------------------------\\
    
    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getGrafica() {
        return Grafica;
    }

    public void setGrafica(String Grafica) {
        this.Grafica = Grafica;
    }

    

}
