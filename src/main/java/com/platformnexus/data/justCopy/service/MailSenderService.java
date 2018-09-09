package com.platformnexus.data.justCopy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import javax.activation.DataSource;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSummaryReport(String subject, String emailBody, String targetEmail, String fromEmail, String fileName, DataSource dataSource, boolean isHTML) throws MailException {
        MimeMessagePreparator preparer = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setSubject(subject);
            message.setTo(targetEmail);
            message.setFrom(fromEmail);
            if (fileName !=null && dataSource != null)
                message.addAttachment(fileName, dataSource);
            message.setText(emailBody, isHTML);
        };
        this.mailSender.send(preparer);
    }

}
