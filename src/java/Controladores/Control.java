package Controladores;

import Entidades.BitacoraServicioSocial;
import Entidades.Estudiante;
import Entidades.Salaserviciosocial;
import Entidades.Usuario;
import Facade.BitacoraServicioSocialFacade;
import Facade.EstudianteFacade;
import Facade.SalaserviciosocialFacade;
import Facade.UsuarioFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author Julián
 */
@Named(value = "controlControlador")
@SessionScoped
public class Control implements Serializable {

    private Part excel;
    private BitacoraServicioSocial btSS;
    private Usuario user;
    private Salaserviciosocial SS;
    private Estudiante estudiante;

    public Control() {
        btSS = new BitacoraServicioSocial();
        user = new Usuario();
        SS = new Salaserviciosocial();
    }

    @EJB
    BitacoraServicioSocialFacade btFacade;

    @EJB
    UsuarioFacade userFacade;

    @EJB
    SalaserviciosocialFacade SSFacade;
    
    @EJB 
    EstudianteFacade estFacade;

    public Part getExcel() {
        return excel;
    }

    public void setExcel(Part excel) {
        this.excel = excel;
    }

    public BitacoraServicioSocial getBtSS() {
        return btSS;
    }

    public void setBtSS(BitacoraServicioSocial btSS) {
        this.btSS = btSS;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Salaserviciosocial getSS() {
        return SS;
    }

    public void setSS(Salaserviciosocial SS) {
        this.SS = SS;
    }

    public void migrar(Usuario coordinador) throws SQLException {
        try {
            Workbook libro = WorkbookFactory.create(excel.getInputStream());
            XSSFSheet hoja = (XSSFSheet) libro.getSheetAt(0);

            Iterator<Row> itr = hoja.rowIterator();
            itr.next();  //Saltar encabezados de Fila 1

            while (itr.hasNext()) {
                Row fila = itr.next();

                int ncamp = 0;

                Iterator<Cell> itrCelda = fila.cellIterator();

                while (itrCelda.hasNext()) {
                    Cell celda = itrCelda.next();

                    switch (ncamp) {
                        case 0:
                            SS = SSFacade.obtenerSalaZona((int) celda.getNumericCellValue());
                            btSS.setSalaDeServicio(SS);
                            System.out.println((int) celda.getNumericCellValue());
                            break;
                        case 1:
                            String fecha = celda.getStringCellValue();
                            fecha = reemplazar(fecha, String.valueOf('"'), "");
                            System.out.println(fecha);
                            btSS.setFechaRegistro(fecha);
                            break;
                        case 2:
                            btSS.setTiempoPrestado((int) celda.getNumericCellValue());
                            System.out.println((int) celda.getNumericCellValue());
                            break;
                        case 3:
                            btSS.setObservaciones(celda.getStringCellValue());
                            System.out.println(celda.getStringCellValue());
                            break;
                        default:
                            btSS.setLaboresRealizadas(celda.getStringCellValue());
                            System.out.println(celda.getStringCellValue());
                            break;
                    }

                    ncamp++;
                }
                btSS.setCoordinador(coordinador);
                btSS.setEstado(1);
                btFacade.create(btSS);
                user = btSS.getSalaDeServicio().getEstudiante().getUsuario();
                estudiante = estFacade.EstudianteDoc(user);
                int tiempo = estudiante.getTiempoServicio();
                estudiante.setTiempoServicio(tiempo+btSS.getTiempoPrestado());
                estFacade.edit(estudiante);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Migración Exitosa"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error abriendo archivo"));
        } catch (InvalidFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error en Formato"));
        }
    }

    public static String reemplazar(String cadena, String busqueda, String reemplazo) {
        return cadena.replaceAll(busqueda, reemplazo);
    }

}

