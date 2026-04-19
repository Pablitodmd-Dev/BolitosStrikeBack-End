package com.example.webthymeleaf.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    @Value("${app.url}")
    private String appUrl;

    public void enviarEmailVerificacion(String emailDestino, String token) {
        Email from = new Email(fromEmail);
        Email to = new Email(emailDestino);
        String subject = "Verifica tu cuenta en BolitosStrike";
        String linkVerificacion = appUrl + "/auth/verify?token=" + token;
        String contenido = "<h2>¡Bienvenido a BolitosStrike!</h2>"
                + "<p>Gracias por registrarte. Para activar tu cuenta haz clic en el siguiente enlace:</p>"
                + "<a href='" + linkVerificacion + "' style='background-color:#C8920A;color:white;"
                + "padding:12px 24px;border-radius:8px;text-decoration:none;font-weight:bold;'>"
                + "Verificar mi cuenta</a>"
                + "<p>Si no te has registrado en BolitosStrike ignora este correo.</p>";
        Content content = new Content("text/html", contenido);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el email: " + e.getMessage());
        }
    }
}