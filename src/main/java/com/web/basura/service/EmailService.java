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
        SimpleMailMessage email = new SimpleMailMessage();
        // aca es donde se envia a los destinatarios
       
        if(isarriba) {
        	System.out.println("Enton en arriba");
        	email.setTo("ddigites@gmail.com");
        	email.setSubject("Preparate: ");
            email.setText("Edison: Debes alistar para sacar la basura de arriba");
            mailSender.send(email);
        }
        if(isNormal) {
        	System.out.println("Enton en normal");
        	email.setTo("horariobasura@gmail.com");
        	email.setSubject("Preparate: ");
            email.setText(""+ name+" " + " proximamente debes alistar para sacar la basura");
            mailSender.send(email);
        }
        
    }
}
