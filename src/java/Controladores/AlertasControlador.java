/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
        
    }


    //----- Attributes -------------------------------------------------------\\

    private String Mensaje;


    //----- Methods ----------------------------------------------------------\\

    

    //----- Getters and Setters ----------------------------------------------\\
    
    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

}
