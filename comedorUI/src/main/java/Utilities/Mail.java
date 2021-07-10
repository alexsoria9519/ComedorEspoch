/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author alex4
 */
public class Mail {

    private String remitente;
    private String passwordRemitente;
    Properties props = System.getProperties();

    public Mail() {
        this.remitente = "comedorespoch@corebitsdev.com";
        this.passwordRemitente = "_.IiBn4{j[FZ";
    }

    public Boolean enviarMail(String destinatario, String asunto, String cuerpo) {
        props.put("mail.smtp.host", "mail.corebitsdev.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", passwordRemitente);    //La clave de la cuenta gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.trust", "mail.corebitsdev.com");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            //content
            //message.setText(cuerpo);
            message.setContent(cuerpo, "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, props.getProperty("mail.smtp.clave"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
            return false;
        }
        return true;
    }

}
