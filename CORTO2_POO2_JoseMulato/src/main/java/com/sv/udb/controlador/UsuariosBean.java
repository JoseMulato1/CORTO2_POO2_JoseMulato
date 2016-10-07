/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Alumnos;
import com.sv.udb.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author joseph
 */
@Named(value = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable {

    @EJB
    private UsuariosFacadeLocal FCDEUsua;
    private Usuarios objeUsua;
    private List<Usuarios> listUsua;
    private boolean guardar;

    public UsuariosFacadeLocal getFCDEUsua() {
        return FCDEUsua;
    }

    public void setFCDEUsua(UsuariosFacadeLocal FCDEUsua) {
        this.FCDEUsua = FCDEUsua;
    }

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public List<Usuarios> getListUsua() {
        return listUsua;
    }

    public void setListUsua(List<Usuarios> listUsua) {
        this.listUsua = listUsua;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    public UsuariosBean() {
        
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeUsua = new Usuarios();
        this.guardar = true;        
    }
    
    public void consTodo()
    {
        try
        {
            this.listUsua = FCDEUsua.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiUsuaPara"));
        try
        {
            this.objeUsua = FCDEUsua.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeUsua.getAcceUsua()));
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
}