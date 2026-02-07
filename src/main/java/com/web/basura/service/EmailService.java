/**
 * 
 */
package com.web.basura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(String name,boolean isarriba, boolean isNormal) {
       
        
    	// aca es donde se envia a los destinatarios
       
        if(isarriba) {
            SimpleMailMessage email = new SimpleMailMessage();
        	email.setTo("ddigitess@gmail.com");
        	email.setSubject("Preparate: ");
            email.setText("Edison: Debes alistar para sacar la basura de arriba");
            mailSender.send(email);
        }
        if(isNormal) {
        	 SimpleMailMessage email = new SimpleMailMessage();
        	email.setTo("horariobasura@gmail.com");
        	email.setSubject("Preparate: ");
            email.setText(""+ name+" " + " proximamente debes alistar para sacar la basura");
            mailSender.send(email);
        }
        
    }
}
