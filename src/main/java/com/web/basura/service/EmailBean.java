/**
 * 
 */
package com.web.basura.service;

import java.util.Calendar;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.web.basura.entity.ArrayName;
import com.web.basura.entity.NombreUsuario;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * 
 */
@Component
//@Scope("view")
@Scope("session") //Mantiene los datos de a misma pagina
public class EmailBean {

	private String anteString = "";
	private boolean booleanValue;
	private boolean booleanValueAnimal;
	@Autowired
	private EmailService emailService;
	@Autowired
	private NombreUsuario nameUser;
	@Autowired
	private ArrayName arrayName;
	
	
	public void enviarCorreo() {
		
		String name = nameUser.getUsuarioBean();
		String[] nombres = arrayName.getArrayName();
		try {
			// Obtener la fecha actual
	        Calendar calendario = Calendar.getInstance();
	        // Obtener el día de la semana
	        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
	        // Verificar si hoy es domingo
	        if (diaSemana == Calendar.SUNDAY || diaSemana == Calendar.TUESDAY || diaSemana == Calendar.THURSDAY) {
                enviarRecordatorio(name, nombres); // Método para enviar recordatorios
            }
	     // Lógica para enviar correo al siguiente usuario
            String siguienteUsuario = obtenerSiguienteUsuario(name, nombres);
            if (siguienteUsuario != null) {
            	if(anteString.equalsIgnoreCase(name)) {
            		FacesContext.getCurrentInstance().addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", 
                                "Este usuario ya realizo la accion"));
            		
            		this.booleanValue = false;
        			this.booleanValueAnimal = false;
        			PrimeFaces.current().executeScript(
        				    "setTimeout(function(){ window.location.href='/user/usuario.xhtml'; }, 2000);"
        				);
            		return ;
            	}
                if(booleanValueAnimal) {
                	emailService.enviarCorreo(siguienteUsuario + ", te toca sacar la basura.", true,true);
                	anteString = name;
                }else {
                	 emailService.enviarCorreo(siguienteUsuario + ", te toca sacar la basura.",false, true);
                }
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", 
                        "Correo enviado a " + siguienteUsuario));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", 
                        "No se pudo determinar el siguiente usuario."));
            }
			this.booleanValue = false;
			this.booleanValueAnimal = false;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", 
                    		e.getMessage()));
		}
	}
	/**
     * Envía un recordatorio al siguiente usuario basado en el usuario actual.
     */
    private void enviarRecordatorio(String usuarioActual, String[] nombres) {
        String siguienteUsuario = obtenerSiguienteUsuario(usuarioActual, nombres);
        if (siguienteUsuario != null && booleanValueAnimal) {
            emailService.enviarCorreo(siguienteUsuario + ", ¡recuerda sacar la basura hoy!", true, true);
        }else {
        	emailService.enviarCorreo(siguienteUsuario + ", ¡recuerda sacar la basura hoy!", false, true);
        }
    }
	 /**
     * Devuelve el siguiente usuario en el flujo, basado en el usuario actual.
     */
    private String obtenerSiguienteUsuario(String usuarioActual, String[] nombres) {
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].equalsIgnoreCase(usuarioActual)) {
                int indexSiguiente = (i + 1) % nombres.length; // Siguiente usuario en orden circular
                return nombres[indexSiguiente];
            }
        }
        return null;
    }
	public boolean isBooleanValue() {
        return booleanValue;
    }
    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
	public boolean isBooleanValueAnimal() {
		return booleanValueAnimal;
	}
	public void setBooleanValueAnimal(boolean booleanValueAnimal) {
		this.booleanValueAnimal = booleanValueAnimal;
	}
}
