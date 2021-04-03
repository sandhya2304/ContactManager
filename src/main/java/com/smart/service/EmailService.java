package com.smart.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class EmailService
{
	
	public boolean sendEmail(String subject,String message,String to)
    {

        boolean status= false;

        String from = "tehie9762@gmail.com";

        //Variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties props = System.getProperties();
        System.out.println(props);

        //setting important function to properties object


        //set host
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.auth", "true");


        //step 1. get the session object

        Session session = Session.getInstance(props,new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("techie9762@gmail.com","");
            }

        });

        session.setDebug(true);

        //step 2. compose the msg
        MimeMessage mime = new MimeMessage(session);


        try{

            mime.setFrom(from);

            //adding receipt to msg
            mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to msg
            mime.setSubject(subject);


            //set attachment
            //file path
            String path = "E:\\img\\hadoop.jpg";

            MimeMultipart mim = new MimeMultipart();
            //text

            //file

            MimeBodyPart text = new MimeBodyPart();



            MimeBodyPart file = new MimeBodyPart();

            try
            {

                //text.setText(message);
            	text.setContent(message,"text/html");
               
            	//mime.setContent(message,"text/html");
                

                File ff= new File(path);
                file.attachFile(ff);


                mim.addBodyPart(text);
                mim.addBodyPart(file);


            }catch(Exception e){
                e.printStackTrace();
            }




            mime.setContent(mim);


            //Step 3. send the message
            Transport.send(mime);



            System.out.println("Sent succesfuly");

            status = true;

        }catch(Exception e)
        {
            e.printStackTrace();
        }

       return  status;
    }
	

}
