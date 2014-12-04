/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility; 

/**
 *
 * @author larva
 */
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Utility {
    static public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    
    static public boolean sendEmail(String to, String from, String subject, 
            String body, boolean bodyIsHTML){
        
        //Send email
        try{
            //mail session
            Properties props = new Properties();
            props.put("mail.transport.protocol","smtps");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.port", "465");
            props.put("mail.smtps.auth", "true");
            props.put("mail.smtps.quitwait", "false");
            Session session = Session.getDefaultInstance(props);
            
            //Create message
            Message message = new MimeMessage(session);
            message.setSubject(subject);
            if(bodyIsHTML){
                message.setContent(body, "text/html");
            }else{
                message.setText(body);
            }
            
            //address the message
            Address fromAddress = new InternetAddress(from);
            Address toAddress = new InternetAddress(to);
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            
            //send the message
            Transport transport = session.getTransport();
            transport.connect("cs605782", "hopkins2134");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch(MessagingException e){
            System.out.println(e.toString());
            return false;
        }
        return true;
    }
}
