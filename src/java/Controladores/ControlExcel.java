package Controladores;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
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
public class ControlExcel{

        
Part excel;

    public Part getExcel() {
        return excel;
    }

    public void setExcel(Part excel) {
        this.excel = excel;
    }
    public void migrar() throws SQLException{
    try{
          Driver drv = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(drv);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_pov?user=KevinS&password=Kesito080117&useSSL=false");
            
            Workbook libro = WorkbookFactory.create(excel.getInputStream());
            XSSFSheet hoja = (XSSFSheet) libro.getSheetAt(0);
                      
            Iterator<Row> itr = hoja.rowIterator();
            itr.next();  //Saltar encabezados de Fila 1
            
            while(itr.hasNext()){                
                Row fila = itr.next();
                
                int ncamp = 1;
                String query ="INSERT INTO cursos(Grado, Curso, Salon) VALUES(";
                
                Iterator<Cell> itrCelda = fila.cellIterator();
                
                while(itrCelda.hasNext()){                    
                    Cell celda = itrCelda.next();
                    
                    if(celda.getCellTypeEnum()== CellType.STRING){
                       
                        //System.out.print("  " + celda.getRichStringCellValue());
                    }else{
                        
                        
                        if(ncamp == 1 || ncamp==2 || ncamp==3){
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
        }catch(IOException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error abriendo archivo"));
        }catch(InvalidFormatException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error en Formato"));
        }
    }
    
}