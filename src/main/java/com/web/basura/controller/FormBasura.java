/**
 * 
 */
package com.web.basura.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.web.basura.entity.ArrayName;
import com.web.basura.entity.NombreUsuario;
import com.web.basura.service.OrdenamiendoArrayName;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * 
 */

@Component
//@Scope("view")
@Scope(value = "view", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FormBasura {
	
	private String username;
	private String name = "";
	private int anioRegistrado;
    
	@Autowired
	private ArrayName arrayNombre;
	@Autowired
	private NombreUsuario nameUser;
	@Autowired
    private OrdenamiendoArrayName ordenamiendoArrayName;
	
	
    /*
     * Metodo para cerrar sesion
     */
    public String returnSesion() {
		return "/user/usuario.xhtml";
    }
	
    
	public FormBasura() {
        Calendar c = new GregorianCalendar();
        this.anioRegistrado = c.get(Calendar.YEAR); // Almacenar el año actual
    }
	/*
	 * Metodo para realizar validacion de login
	 */
    public String login() throws IOException {
    	TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
    	Calendar c = new GregorianCalendar();
		String dia, mes;
		int annio;
		dia = Integer.toString(c.get(Calendar.DATE));
		mes = Integer.toString(c.get(Calendar.MONTH) + 1);
		annio = c.get(Calendar.YEAR);
		nameUser.setDate(annio + "/" + "0"+mes + "/" + dia);
    	// Formatear y mostrar la fecha con día de la semana
    	if(username.isEmpty() || username == null) { 
    		FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid user!", "Campo vacio"));
    		return null;
    	}
    	if(annio != anioRegistrado) {
    		anioRegistrado = annio;
    		String[] updatedArray = ordenamiendoArrayName.arrayNew();
    		for (String element : updatedArray) {
    			name += element + "\n";
    		}
 			FacesContext.getCurrentInstance().addMessage(null, 
                     new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Año actualizado"));
 		}else {
 			List<String> listName = Arrays.asList(arrayNombre.getArrayName());
 	        for (String nombre : listName) {
 	        	name += nombre + "\n";
 	        }
 		}
        if(name.contains(getUsername().toUpperCase())) {
        	nameUser.setUsuarioBean(username);
    		FacesContext.getCurrentInstance().getExternalContext().redirect("/form/form.xhtml");
    	}else {
    		 FacesContext.getCurrentInstance().addMessage(null, 
                     new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid user!", "Usuario no existe"));
    	}
    	resetFields();
		return null;
    }
    /*
     * Asignar nuevamente los campos vacios
     */
    private void resetFields() {
        this.username = "";
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
	public ArrayName getArrayNombre() {
		return arrayNombre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAnioRegistrado() {
		return anioRegistrado;
	}
	public void setAnioRegistrado(int anioRegistrado) {
		this.anioRegistrado = anioRegistrado;
	}

}
