/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.utilidades;

import com.google.gson.Gson;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
public class Mail {

    JSONObject resJson = new JSONObject();
    Gson gson = new Gson();
    private String remitente;
    private String passwordRemitente;
    Properties props = System.getProperties();

    public Mail() {
        this.remitente = "comedorespoch@corebitsdev.com";
        this.passwordRemitente = "_.IiBn4{j[FZ";
    }

    public Boolean sendMail(String destinatario, String asunto, String cuerpo) {
        props.put("mail.smtp.host", "mail.corebitsdev.com");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", passwordRemitente);    //La clave de la cuenta gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
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
            transport.connect("mail.corebitsdev.com", remitente, props.getProperty("mail.smtp.clave"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            System.err.println("com.comedor.utilidades.Mail.sendMail()");
            me.printStackTrace();
            return false;
        }
        return true;
    }

    public String enviarMail(String JSONMail) {

        try {
            JSONObject dataMail = new JSONObject(JSONMail);
            Boolean valMail = sendMail(dataMail.getString("destinatario"), dataMail.getString("asunto"), dataMail.getString("cuerpo"));

            if (valMail) {
                resJson.put("success", "ok");
                resJson.put("data", "Envio Correcto");
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Error en el envio del correo");
            }

        } catch (JSONException ex) {
            System.err.println("com.comedor.utilidades.Mail.enviarMail() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error con los datos del envio");
        }
        return resJson.toString();
    }

}
