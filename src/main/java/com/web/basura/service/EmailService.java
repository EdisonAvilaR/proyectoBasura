/**
 * 
 */
package com.web.basura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

/**
 * 
 */
@Service
public class EmailService {

//	@Autowired
//    private JavaMailSender mailSender;

	@Value("${spring.sendgrid.api-key}")
	private String sendGridApiKey;

	public void enviarCorreo(String mensaje, boolean isarriba, boolean isNormal) {
//       
//        
//    	// aca es donde se envia a los destinatarios
//       
//        if(isarriba) {
//            SimpleMailMessage email = new SimpleMailMessage();
//        	email.setTo("ddigitess@gmail.com");
//        	email.setSubject("Preparate: ");
//            email.setText("Edison: Debes alistar para sacar la basura de arriba");
//            mailSender.send(email);
//        }
//        if(isNormal) {
//        	 SimpleMailMessage email = new SimpleMailMessage();
//        	email.setTo("horariobasura@gmail.com");
//        	email.setSubject("Preparate: ");
//            email.setText(""+ name+" " + " proximamente debes alistar para sacar la basura");
//            mailSender.send(email);
//        }
//        
//    }
		if (isarriba) {
			Email from = new Email("edisonmillos@outlook.com"); // VERIFIED
			Email to = new Email("ddigitess@gmail.com");

			String subject = "Aviso Basura";
			Content content = new Content("text/plain", mensaje);

			Mail mail = new Mail(from, subject, to, content);

			SendGrid sg = new SendGrid(sendGridApiKey);
			Request request = new Request();

			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());

				Response response = sg.api(request);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (isNormal) {
			Email from = new Email("edisonmillos@outlook.com"); // VERIFIED
			Email to = new Email("horariobasura@gmail.com");

			String subject = "Aviso Basura";
			Content content = new Content("text/plain", mensaje);

			Mail mail = new Mail(from, subject, to, content);

			SendGrid sg = new SendGrid(sendGridApiKey);
			Request request = new Request();

			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());

				Response response = sg.api(request);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
