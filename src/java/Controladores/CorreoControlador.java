/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.Part;


/**
 *
 * @author kesgr
 */
@Named(value = "correoControlador")
@SessionScoped
public class CorreoControlador implements Serializable {

    
    public CorreoControlador() {
    }

    private String ruta ="C:\\Users\\kesgr\\OneDrive\\Documentos\\GitHub\\POV\\web\\Resources\\img\\Noticias\\"; 
    
    //----- Gráficas -------------------------------------------------------\\
    //----- Colores ------------------------------------\\
    public List<String> colores(int cont) {
        List<String> colores = new ArrayList<>();
        for (int i = 0; i < cont; i++) {
            String colorFinal = "'rgb(";
            for (int j = 0; j < 3; j++) {
                int num = (int) Math.floor(Math.random() * 256);
                String digito = String.valueOf(num);
                if (j != 2) {
                    colorFinal = colorFinal + digito + ",";
                } else {
                    colorFinal = colorFinal + digito;
                }
            }
            String cierre = ")'";
            colorFinal = colorFinal + cierre;
            colores.add(colorFinal);
        }

        return colores;
    }

    //---------- Cargue de imagenes -------------------\\
    private Part imagen;

    public void subirImagen() {
        try {
            InputStream in = imagen.getInputStream();
            File f = new File(ruta + imagen.getSubmittedFileName());
            f.createNewFile();
            
            FileOutputStream nf = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int tamaño;

            while ((tamaño = in.read(buffer)) > 0) {
                nf.write(buffer, 0, tamaño);
            }

            nf.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("El archivo es: " + imagen.getSubmittedFileName());
    }

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
}
