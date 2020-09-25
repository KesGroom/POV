package Controladores;

import java.io.IOException;
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
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author Jury
 */
@ManagedBean
@Dependent
public class Control {

    Part excel;

    public Part getExcel() {
        return excel;
    }

    public void setExcel(Part excel) {
        this.excel = excel;
    }

    public void migrar() throws SQLException {
        try {
            Driver drv = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(drv);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_pov?user=KevinS&password=Kesito080117&useSSL=false");

            Workbook libro = WorkbookFactory.create(excel.getInputStream());
            XSSFSheet hoja = (XSSFSheet) libro.getSheetAt(0);

            Iterator<Row> itr = hoja.rowIterator();
            itr.next();  //Saltar encabezados de Fila 1

            while (itr.hasNext()) {
                Row fila = itr.next();

                int ncamp = 1;
                String query = "INSERT INTO bitacora_servicio_social(Id_Bitacora_Servicio, Coordinador, SalaDeServicio, Fecha_Registro, Tiempo_Prestado, Observaciones, Labores_Realizadas, Estado) VALUES(";

                Iterator<Cell> itrCelda = fila.cellIterator();

                while (itrCelda.hasNext()) {
                    Cell celda = itrCelda.next();

                    if (celda.getCellTypeEnum() == CellType.STRING) {

                        /*if (ncamp == 4) {
                            String strDate =celda.getRichStringCellValue().getString();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            Date date = null;
                            try {
                                date = sdf.parse(strDate);
                            } catch (ParseException ex) {
                              
                            }
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(date);
                            java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
                            query = query + ", " + sqlDate;
                        }*/
                        if (ncamp == 6) {
                            query = query + ", '" + celda.getRichStringCellValue() + "'";
                        }
                        if (ncamp == 7) {
                            query = query + ", '" + celda.getRichStringCellValue() + "'";
                        }

                        //System.out.print("  " + celda.getRichStringCellValue());
                    } else {

                        if (ncamp == 1) {
                            query = query + celda.getNumericCellValue();
                        }
                        if (ncamp == 2 || ncamp == 3 || ncamp == 5 || ncamp == 8) {
                            query = query + ", " + celda.getNumericCellValue();
                        }

                        //System.out.print("  " + celda.getNumericCellValue());
                    }
                    ncamp++;
                }
                query = query + ");";
                //System.out.println("");
                //Comandos para guardar Registro en BD
                //System.out.println(query);
                PreparedStatement ps = con.prepareStatement(query);
                ps.executeUpdate();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Migración Exitosa"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error abriendo archivo"));
        } catch (InvalidFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error en Formato"));
        }
    }

}
