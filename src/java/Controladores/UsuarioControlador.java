/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Acudiente;
import Entidades.Area;
import Entidades.Curso;
import Entidades.Docente;
import Entidades.DocenteMateria;
import Entidades.Estudiante;
import Entidades.Grado;
import Entidades.Materia;
import Entidades.Rol;
import Entidades.Tipo;
import Entidades.Usuario;
import Facade.AcudienteFacade;
import Facade.CursoFacade;
import Facade.DocenteFacade;
import Facade.DocenteMateriaFacade;
import Facade.EstudianteFacade;
import Facade.GradoFacade;
import Facade.MateriaFacade;
import Facade.RolFacade;
import Facade.TipoFacade;
import Facade.UsuarioFacade;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.Integer.getInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author jusag
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    //----- Builders ---------------------------------------------------------\\
    public UsuarioControlador() {
        rol = new Rol();
        tipo = new Tipo();
        curso = new Curso();
        acudiente = new Acudiente();
        docente = new Docente();
        estudiante = new Estudiante();
        usuario = new Usuario();
        usuarioPrueba = new Usuario();
        DM = new DocenteMateria();
        materia = new Materia();
    }

    //----- Attributes -------------------------------------------------------\\
    @Inject
    AlertasControlador alerta;

    @EJB
    UsuarioFacade usuarioFacade;

    @EJB
    RolFacade rolFacade;

    @EJB
    TipoFacade tipoFacade;

    @EJB
    AcudienteFacade acuFacade;

    @EJB
    DocenteFacade docFacade;

    @EJB
    EstudianteFacade estFacade;

    @EJB
    CursoFacade cursoFacade;

    @EJB
    GradoFacade gradoFacade;

    @EJB
    MateriaFacade matFacade;

    @EJB
    DocenteMateriaFacade DMFacade;

    private Usuario usuario;
    private Usuario usuarioPrueba;
    private Rol rol;
    private Tipo tipo;
    private String FotoSeleccionada;
    private String numDocumento;
    private String diaFN;
    private int mesFN = 12;
    private String anioFN;
    private Date fechaNacimiento;
    private String numEst;
    private String selectedRol;
    private Acudiente acudiente;
    private Docente docente;
    private Materia materia;
    private DocenteMateria DM;
    private Estudiante estudiante;
    private List<Grado> gradoList;
    private List<Curso> cursoList;
    private List<Area> areaList;
    private List<Materia> materiaList;
    private int[] materiasSeleccionadas;
    private String nivel = "0";
    private String nivel2 = "0";
    private String grado = "0";
    private String grado2 = "0";
    private Curso curso;
    private int area = 0;
    private String lenguaje;
    private String avatar;

    //----- Variables para el olvido de contraseña ------------------\\
    private String Documento;
    private String Correo;
    private String Codigo = "MC-";
    private String Code;
    private String digito;
    private String Cod = "MC-" + Code;

    //----- Variables para busqueda ---------------------------------\\
    private List<Usuario> usuList;
    private List<Usuario> estList;
    private List<Estudiante> estListSelect = new ArrayList<>();
    private String numDoc = "";
    private String nombre = "";
    private String apellido = "";
    private String genero = "null";
    private String role;
    private String tipe;
    private int contUsu;
    private Rol rolSearch = null;
    private Rol rolSearch2 = null;
    private Tipo tipoSearch = null;

    //----- Variables para exportar a Excel ---------------------------------\\
    private String nombreArch;
    private String alineacion;
    private String estilo;

    //----- Methods ----------------------------------------------------------\\
    public void lenguaje(String lg) {
        this.lenguaje = lg;
    }

    public String registrarUsu() {
        usuarioPrueba = usuarioFacade.validacionDoc(numDocumento);
        if (usuarioPrueba == null) {
            usuario.setEstado(1);
            usuario.setNumerodeDocumento(numDocumento);
            usuario.setFoto("http://imgfz.com/i/pRBCSy5.png");
            rol = rolFacade.busquedaRol(selectedRol);
            usuario.setIdRoles(rol);
            usuario.setIdTipo(tipo);
            int dFN = Integer.parseInt(diaFN);
            int mFN = mesFN;
            int aFN = Integer.parseInt(anioFN) - 1900;
            usuario.setFechadeNacimiento(fechaNacimiento = new Date(aFN, mFN, dFN));

            switch (selectedRol) {
                case "Docente":
                    usuarioFacade.create(usuario);
                    docente.setIdUsuario(usuario.getIdUsuario());
                    docente.setEstado(1);
                    docFacade.create(docente);

                    for (int i = 0; i < materiasSeleccionadas.length; i++) {
                        DM.setEstado(1);
                        DM.setIdDocente(docente);
                        materia = matFacade.findUnique(materiasSeleccionadas[i]);
                        DM.setIdMateria(materia);
                        DMFacade.create(DM);
                    }

                    this.docente = null;
                    this.materiasSeleccionadas = null;
                    this.DM = null;
                    this.usuario = new Usuario();
                    this.materia = null;
                    this.area = 0;
                    this.nivel2 = "";
                    this.materiaList = null;

                    break;
                case "Estudiante":
                    usuarioFacade.create(usuario);
                    estudiante.setIdUsuario(usuario.getIdUsuario());
                    estudiante.setEstado(1);
                    estudiante.setTiempoServicio(0);
                    estudiante.setIdCurso(curso);
                    estudiante.setEstadoServicioSocial(1);

                    estFacade.create(estudiante);

                    this.estudiante = new Estudiante();
                    break;
                case "Acudiente":
                    usuarioFacade.create(usuario);
                    acudiente.setIdUsuario(usuario.getIdUsuario());
                    acudiente.setEstado(1);
                    acuFacade.create(acudiente);
                    for (int i = 0; i < estListSelect.size(); i++) {
                        estudiante = estListSelect.get(i);
                        estudiante.setIdAcudiente(acudiente);
                        estFacade.edit(estudiante);
                        this.estudiante = null;
                    }

                    this.acudiente = null;
                    this.estListSelect = null;
                    this.numEst = "";
                    this.estList = null;
                    break;
                default:
                    usuarioFacade.create(usuario);
                    break;
            }
            if ("es".equals(lenguaje)) {
                alerta.setMensaje("AlertaToast('Usuario registrado con éxito','success');");
            } else {
                alerta.setMensaje("AlertaToast('Registered user successfully','success');");
            }

            //Reinicio del formulario
            this.usuario = new Usuario();
            this.numDocumento = "";
            this.mesFN = 12;
            this.diaFN = "";
            this.anioFN = "";
            this.selectedRol = "";
            this.tipo = null;

            return "RegistrarUsuario";

        } else {
            if ("es".equals(lenguaje)) {
                alerta.setMensaje("AlertaPopUp('Usuario existente','El usuario que desea registrar ya existe en el sistema','error');");
            } else {
                alerta.setMensaje("AlertaPopUp('Existing user','The user you want to register already exists in the system','error');");
            }
            this.usuario = new Usuario();
            this.numDocumento = "";
            this.mesFN = 12;
            this.diaFN = "";
            this.anioFN = "";
            this.selectedRol = "";
            this.tipo = null;
            return "RegistrarUsuario";
        }

    }

    public String remover(Usuario usuarioRemover) {
        usuario = usuarioRemover;
        if (usuario.getIdRoles().getIdRoles() != 1) {
            usuario.setEstado(2);
            usuarioFacade.edit(usuario);
            if ("es".equals(lenguaje)) {
                alerta.setMensaje("AlertaToast('El usuario " + usuario.getNombre() + " ha sido eliminado','success');");
            } else {
                alerta.setMensaje("AlertaToast('The user " + usuario.getNombre() + " has been deleted','success');");
            }
            return "ListUsuario";
        } else {
            if ("es".equals(lenguaje)) {
                alerta.setMensaje("AlertaPopUp('Error al eliminar','El usuario " + usuario.getNombre() + " no puede ser eliminado porque es un Coordinador','error');");
            } else {
                alerta.setMensaje("AlertaPopUp('Delete failed','The user " + usuario.getNombre() + " cannot be eliminated because he is a Coordinator','error');");
            }
            return "ListUsuario";
        }
    }

    public String preActualizar(Usuario usuarioPre) {
        usuario = usuarioPre;
        rol = usuarioPre.getIdRoles();
        tipo = usuarioPre.getIdTipo();

        return "UsuariosActualizar";
    }

    public String actualizar() {
        usuario.setIdRoles(rolFacade.find(rol.getIdRoles()));
        usuario.setIdTipo(tipoFacade.find(tipo.getIdTipo()));
        usuarioFacade.edit(usuario);
        return "ListUsuario";
    }

    //----- Consultas ------------------------------------------------------\\
    public List<Usuario> conseguirUsuario(int id) {
        return usuarioFacade.obtenerUsuario(id);
    }

    public List<Usuario> consultarUsuarios() {
        return usuarioFacade.consultarUsuario(1);
    }

    public List<Usuario> buscarRol(int rol) {
        return usuarioFacade.busquedaRol(rol);
    }

    //----- Edición de perfil ----------------------------------------------\\
    public String actualizarPerfil(Usuario usuarioActualizar) {
        usuario = usuarioActualizar;
        rol = usuarioActualizar.getIdRoles();
        tipo = usuarioActualizar.getIdTipo();

        return "ActualizarPerfil";
    }

    public String actualizarPhoto(Usuario usuarioActualizar) {
        usuario = usuarioActualizar;
        rol = usuarioActualizar.getIdRoles();
        tipo = usuarioActualizar.getIdTipo();

        return "Avatar";
    }

    public String actualizarP() {
        usuario.setIdRoles(rol);
        usuario.setIdTipo(tipo);
        usuarioFacade.edit(usuario);
        usuario = new Usuario();

        return "Perfil";
    }

    public String actualizarFoto() {
        usuario.setFoto(avatar);
        usuarioFacade.edit(usuario);
        usuario = new Usuario();

        return "Perfil";
    }

    public void fotoPrueba() {

        this.FotoSeleccionada = this.avatar;

    }

    //----- Ajax ------------------------------------------------------------\\
    //----- Búsqueda multicriterio --------------------------------------\\
    public List<Usuario> getListByName() {
        if (usuList == null) { //Si es la primera vez que se accederá a la lista...
            usuList = usuarioFacade.consultarUsuario(1); //... Actualizar el contenido
        }
        return usuList; //Devuelve la lista con los elementos encontrados
    }

    public void changeListener(AjaxBehaviorEvent event) {
        //cada vez que haya un cambio en el texto, vuelve a generar la lista
        if (role != null && tipe != null && !"null".equals(genero)) {
            rolSearch = rolFacade.busquedaRol(role);
            tipoSearch = tipoFacade.busquedaTipo(tipe);
            usuList = usuarioFacade.findByTipoRolG(nombre, apellido, numDoc, genero, rolSearch, tipoSearch);
        } else if (role != null && tipe != null && "null".equals(genero)) {
            rolSearch = rolFacade.busquedaRol(role);
            tipoSearch = tipoFacade.busquedaTipo(tipe);
            usuList = usuarioFacade.findByTipoRol(nombre, apellido, numDoc, rolSearch, tipoSearch);
        } else if (role != null && !"null".equals(genero) && tipe == null) {
            rolSearch = rolFacade.busquedaRol(role);
            usuList = usuarioFacade.findByRolG(nombre, apellido, numDoc, genero, rolSearch);
        } else if (role != null && "null".equals(genero) && tipe == null) {
            rolSearch = rolFacade.busquedaRol(role);
            usuList = usuarioFacade.findByRol(nombre, apellido, numDoc, rolSearch);
        } else if (tipe != null && !"null".equals(genero) && role == null) {
            tipoSearch = tipoFacade.busquedaTipo(tipe);
            usuList = usuarioFacade.findByTipoG(nombre, apellido, numDoc, genero, tipoSearch);
        } else if (tipe != null && "null".equals(genero) && role == null) {
            tipoSearch = tipoFacade.busquedaTipo(tipe);
            usuList = usuarioFacade.findByTipo(nombre, apellido, numDoc, tipoSearch);
        } else if (!"null".equals(genero) && tipe == null && role == null) {
            usuList = usuarioFacade.findByGender(nombre, apellido, numDoc, genero);
        } else if ("null".equals(genero) && tipe == null && role == null) {
            usuList = usuarioFacade.findByName(nombre, apellido, numDoc);
        }
    }

    //----- Actualización de Selects -----------------------------------\\
    public void addStudent(Usuario usuarioSeleccion) {
        estudiante = estFacade.EstudianteDoc(usuarioSeleccion);
        estListSelect.add(estudiante);
    }

    public void removeStudent(Usuario usuarioSeleccion) {
        estudiante = estFacade.EstudianteDoc(usuarioSeleccion);
        estListSelect.remove(estudiante);
    }

    public List<Usuario> getListByEst() {
        if (estList == null) { //si es la primera vez que se accederá a la lista...
            estList = null; //... actualizar el contenido
        }
        return estList; //devuelve la lista con los elementos encontrados
    }

    public void changeListEst(AjaxBehaviorEvent event) {
        if (!"".equals(numEst)) {
            rolSearch2 = rolFacade.busquedaRol("Estudiante");
            estList = usuarioFacade.findByDoc(numEst, rolSearch2);
        } else {
            this.estList = null;
        }
    }

    public void cambioGrade(AjaxBehaviorEvent event) {
        if (!"0".equals(nivel)) {
            gradoList = new ArrayList<>();
            for (Grado g : gradoFacade.consultarGrado(1)) {
                if (g.getEducacion() == null ? nivel == null : g.getEducacion().equals(nivel)) {
                    gradoList.add(g);
                }
            }
        }
    }

    public void cambioGrade2(AjaxBehaviorEvent event) {
        if (!"0".equals(nivel2)) {
            gradoList = new ArrayList<>();
            for (Grado g : gradoFacade.consultarGrado(1)) {
                if (g.getEducacion() == null ? nivel2 == null : g.getEducacion().equals(nivel2)) {
                    gradoList.add(g);
                }
            }
        }
    }

    public void cambioCourse(AjaxBehaviorEvent event) {
        if (!"0".equals(grado)) {
            cursoList = new ArrayList<>();
            for (Curso c : cursoFacade.consultarCurso(1)) {
                if (c.getGrado().getGrado().equals(grado)) {
                    cursoList.add(c);
                }
            }
        }
    }

    public void cambioMaterias(AjaxBehaviorEvent event) {
        if (!"0".equals(nivel2) && area != 0) {
            materiaList = new ArrayList<>();
            for (Materia m : matFacade.consultarMateria(1)) {
                if (m.getIdArea().getArea().getIdArea() == area && m.getIdArea().getGrado().getEducacion().equals(nivel2)) {
                    materiaList.add(m);
                }
            }

        }
    }

    //----- Olvido de contraseña -------------------------------------------\\
    public String olvido() throws NoSuchProviderException, MessagingException {

        usuarioPrueba = usuarioFacade.OlvidoContra(Documento, Correo);

        if (usuarioPrueba != null) {

            for (int i = 0; i < 6; i++) {

                int num = (int) Math.floor(Math.random() * 10);
                digito = String.valueOf(num);

                Codigo = Codigo + digito;

            }

            Properties propiedad = new Properties();
            propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedad.setProperty("mail.smtp.starttls.enable", "true");
            propiedad.setProperty("mail.smtp.port", "587");
            propiedad.setProperty("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(propiedad);

            String correoEnvia = "mariacano.pov@gmail.com";
            String contrasena = "PovGaes7";
            String destinatario = Correo;
            String asunto = "Código de reestablecimiento de contraseña";
            String mensaje = "Estimad@ " + usuarioPrueba.getNombre() + " se ha solicitado realizar el reestablecimiento de su contraseña.\n"
                    + "Si usted no ha realizado está solicitud le recomendamos ingresar a su perfil y validar su información, "
                    + "y comunicarse lo antes posible con la institución a tráves del correo MariaCano.Pov@gmail.com o llamando al 372 6691.\n\n"
                    + "\t Su código de válidación es: " + Codigo;

            MimeMessage mail = new MimeMessage(sesion);

            try {
                mail.setFrom(new InternetAddress(correoEnvia));
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                mail.setSubject(asunto);
                mail.setContent(mensaje, "text/html");

                Transport transporte = sesion.getTransport("smtp");
                transporte.connect(correoEnvia, contrasena);
                transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                transporte.close();

            } catch (AddressException ex) {
                Logger.getLogger(Usuario.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (MessagingException ex) {
                Logger.getLogger(Usuario.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            return "Validacion";
        } else {
            return "";
        }

    }

    public String validacion() {
        if (Codigo.equals(Cod)) {
            return "DefinirContra";
        } else {
            return "";
        }
    }

    //----- Excel ----------------------------------------------------------\\
    public void generarExcel() throws IOException {
        if (!"".equals(nombreArch)) {

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nombreArch + ".xls");

            response.setContentType("application/vnd.ms-excel");
            try {
                HSSFWorkbook wb = new HSSFWorkbook();//Se crea un libro de Excel
                HSSFSheet sheet = wb.createSheet("UsuariosMC");//Se crea la hoja de trabajo
                HSSFCellStyle cellStyle = wb.createCellStyle(); //Se crea un acceso a los estilos

                List<String> titulos = new ArrayList<>();
                titulos.add("Rol");
                titulos.add("Tipo de documento");
                titulos.add("Número de documento");
                titulos.add("Nombre");
                titulos.add("Apellido");
                titulos.add("Fecha de nacimiento");
                titulos.add("Dirección");
                titulos.add("Celular");
                titulos.add("Teléfono fijo");
                titulos.add("Género");
                titulos.add("Correo electrónico");

                if (null == alineacion) {
                    cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                    if (null == estilo) {
                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                    } else {
                        switch (estilo) {
                            case "Sin":
                                cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                                break;
                            case "Con":
                                cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                                cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                                break;
                        }
                    }
                } else {
                    switch (alineacion) {
                        case "Izquierda":
                            cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                            if (null == estilo) {
                                cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                            } else {
                                switch (estilo) {
                                    case "Sin":
                                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                                        break;
                                    case "Con":
                                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                                        break;
                                }
                            }
                            break;
                        case "Centro":
                            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                            if (null == estilo) {
                                cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                            } else {
                                switch (estilo) {
                                    case "Sin":
                                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                                        break;
                                    case "Con":
                                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                                        break;
                                }
                            }
                            break;
                        case "Derecha":
                            cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            if (null == estilo) {
                                cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                                cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                            } else {
                                switch (estilo) {
                                    case "Sin":
                                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_NONE);
                                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_NONE);
                                        break;
                                    case "Con":
                                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                                        break;
                                }
                            }
                            break;
                    }
                }

                HSSFRow row1i = sheet.createRow((short) 0);//Se crea la primera fila
                for (int i = 0; i < 10; i++) {
                    HSSFCell a = row1i.createCell(i);
                    a.setCellValue(titulos.get(i));
                    a.setCellStyle(cellStyle);
                }
                for (int i = 1; i < usuList.size() + 1; i++) {

                    HSSFRow row2 = sheet.createRow(i);
                    for (int j = 0; j < 1; j++) {
                        HSSFCell a = row2.createCell(0);
                        a.setCellStyle(cellStyle);
                        a.setCellValue(usuList.get(i - 1).getIdRoles().getRol());

                        HSSFCell b = row2.createCell(1);
                        b.setCellStyle(cellStyle);
                        b.setCellValue(usuList.get(i - 1).getIdTipo().getTipo());

                        HSSFCell c = row2.createCell(2);
                        c.setCellValue(usuList.get(i - 1).getNumerodeDocumento());
                        c.setCellStyle(cellStyle);

                        HSSFCell d = row2.createCell(3);
                        d.setCellValue(usuList.get(i - 1).getNombre());
                        d.setCellStyle(cellStyle);

                        HSSFCell e = row2.createCell(4);
                        e.setCellValue(usuList.get(i - 1).getApellido());
                        e.setCellStyle(cellStyle);

                        HSSFCell g = row2.createCell(5);
                        Date date = usuList.get(i - 1).getFechadeNacimiento();
                        String dia = String.valueOf(date.getDate());
                        String mes = String.valueOf(date.getMonth() + 1);
                        String anio = String.valueOf(date.getYear() + 1900);

                        if (date.getMonth() + 1 < 10) {
                            if (date.getDate() < 10) {
                                String fechaNa = "0" + dia + " / 0" + mes + " / " + anio;
                                g.setCellValue(fechaNa);
                                g.setCellStyle(cellStyle);
                            } else {
                                String fechaNa = dia + " / 0" + mes + " / " + anio;
                                g.setCellValue(fechaNa);
                                g.setCellStyle(cellStyle);
                            }
                        } else {
                            String fechaNa = dia + " / " + mes + " / " + anio;
                            g.setCellValue(fechaNa);
                            g.setCellStyle(cellStyle);
                        }

                        HSSFCell h = row2.createCell(6);
                        h.setCellValue(usuList.get(i - 1).getDireccion());
                        h.setCellStyle(cellStyle);

                        HSSFCell ii = row2.createCell(7);
                        ii.setCellValue(usuList.get(i - 1).getCelular());
                        ii.setCellStyle(cellStyle);

                        HSSFCell jj = row2.createCell(8);
                        jj.setCellValue(usuList.get(i - 1).getTelefonoFijo());
                        jj.setCellStyle(cellStyle);

                        HSSFCell k = row2.createCell(9);
                        k.setCellValue(usuList.get(i - 1).getGenero());
                        k.setCellStyle(cellStyle);

                        HSSFCell l = row2.createCell(10);
                        l.setCellValue(usuList.get(i - 1).getCorreoElectronico());
                        l.setCellStyle(cellStyle);
                    }

                }

                OutputStream out = response.getOutputStream();
                wb.write(out);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            response.getOutputStream().flush();
            FacesContext.getCurrentInstance().responseComplete();

            alerta.setMensaje("AlertaToast('Exportación exitosa','success');");

            this.alineacion = "";
            this.estilo = "";
            this.nombreArch = "";
        } else {
            alerta.setMensaje("AlertaToast('Se debe ingresar el nombre del archivo','error');");
            this.alineacion = "";
            this.estilo = "";
            this.nombreArch = "";
        }
    }

    //----- Getters and Setters ----------------------------------------------\\
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getFotoSeleccionada() {
        return FotoSeleccionada;
    }

    public void setFotoSeleccionada(String FotoSeleccionada) {
        this.FotoSeleccionada = FotoSeleccionada;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public Usuario getUsuarioPrueba() {
        return usuarioPrueba;
    }

    public void setUsuarioPrueba(Usuario usuarioPrueba) {
        this.usuarioPrueba = usuarioPrueba;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getCod() {
        return Cod;
    }

    public void setCod(String Cod) {
        this.Cod = Cod;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public List<Usuario> getUsuList() {
        return usuList;
    }

    public void setUsuList(List<Usuario> usuList) {
        this.usuList = usuList;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreArch() {
        return nombreArch;
    }

    public void setNombreArch(String nombreArch) {
        this.nombreArch = nombreArch;
    }

    public String getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(String alineacion) {
        this.alineacion = alineacion;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public Rol getRolSearch() {
        return rolSearch;
    }

    public void setRolSearch(Rol rolSearch) {
        this.rolSearch = rolSearch;
    }

    public Tipo getTipoSearch() {
        return tipoSearch;
    }

    public void setTipoSearch(Tipo tipoSearch) {
        this.tipoSearch = tipoSearch;
    }

    public int getContUsu() {
        return contUsu;
    }

    public void setContUsu(int contUsu) {
        this.contUsu = contUsu;
    }

    public String getDiaFN() {
        return diaFN;
    }

    public void setDiaFN(String diaFN) {
        this.diaFN = diaFN;
    }

    public int getMesFN() {
        return mesFN;
    }

    public void setMesFN(int mesFN) {
        this.mesFN = mesFN;
    }

    public String getAnioFN() {
        return anioFN;
    }

    public void setAnioFN(String anioFN) {
        this.anioFN = anioFN;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumEst() {
        return numEst;
    }

    public void setNumEst(String numEst) {
        this.numEst = numEst;
    }

    public List<Usuario> getEstList() {
        return estList;
    }

    public void setEstList(List<Usuario> estList) {
        this.estList = estList;
    }

    public Rol getRolSearch2() {
        return rolSearch2;
    }

    public void setRolSearch2(Rol rolSearch2) {
        this.rolSearch2 = rolSearch2;
    }

    public List<Estudiante> getEstListSelect() {
        return estListSelect;
    }

    public void setEstListSelect(List<Estudiante> estListSelect) {
        this.estListSelect = estListSelect;
    }

    public String getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(String selectedRol) {
        this.selectedRol = selectedRol;
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Grado> getGradoList() {
        return gradoList;
    }

    public void setGradoList(List<Grado> gradoList) {
        this.gradoList = gradoList;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNivel2() {
        return nivel2;
    }

    public void setNivel2(String nivel2) {
        this.nivel2 = nivel2;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public DocenteMateria getDM() {
        return DM;
    }

    public void setDM(DocenteMateria DM) {
        this.DM = DM;
    }

    public String getGrado2() {
        return grado2;
    }

    public void setGrado2(String grado2) {
        this.grado2 = grado2;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int[] getMateriasSeleccionadas() {
        return materiasSeleccionadas;
    }

    public void setMateriasSeleccionadas(int[] materiasSeleccionadas) {
        this.materiasSeleccionadas = materiasSeleccionadas;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    
    
}
