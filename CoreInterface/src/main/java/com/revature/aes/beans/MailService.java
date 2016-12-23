package com.revature.aes.beans;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	
	private static Logger logger = Logger.getAnonymousLogger();

	@Autowired
	private MailSender mailSender;
	
	public SimpleMailMessage setupMessage(String sender, String recipient, String sub, String body){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);
        msg.setTo(recipient);
        msg.setSubject(sub);
        msg.setText(body);
        
        return msg;
	}
	
    public Boolean sendEmail(SimpleMailMessage msg) {	
        try{
        	mailSender.send(msg);
        	return true;
        }
        catch(MailException e){
        	
        	logger.info("error" + e);
        	
        	try{
        		mailSender.send(msg);
        		return true;
        	}
        	catch(MailException ex){
        		logger.info("error" + e);
        		return false;
        	}
        }
    }
}