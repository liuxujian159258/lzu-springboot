package boot.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

        // 邮件单发，外部new 一个线程
    public static void qqEmailSend(String username, String password, String to, String subject, String text) throws MessagingException {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.qq.com");
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setPort(456);
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.ssl", "true");
        p.setProperty("mail.smtp.socketFactory.port", "465");
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.starttls.required", "true");
        sender.setJavaMailProperties(p);
        MimeMessage message = sender.createMimeMessage();
        //这里的utf-8解决 邮件 内容乱码
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        //当前发送人邮箱（也就是自己）
        helper.setFrom(username);
        //发送到的邮箱地址
        helper.setTo(to);
        //邮件主题、标题
        helper.setSubject(subject);
        //内容
        helper.setText(text);
        sender.send(message);
    }

    // 群发邮件，开启线程池
        public static void qqEmailSendMass (String username, String password, String[] to, String subject, String text) throws MessagingException {
                JavaMailSenderImpl sender = new JavaMailSenderImpl();
                sender.setHost("smtp.qq.com");
                sender.setUsername(username);
                sender.setPassword(password);
                sender.setPort(456);
                Properties p = new Properties();
                p.setProperty("mail.smtp.auth", "true");
                p.setProperty("mail.smtp.ssl", "true");
                p.setProperty("mail.smtp.socketFactory.port", "465");
                p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                p.setProperty("mail.smtp.starttls.enable", "true");
                p.setProperty("mail.smtp.starttls.required", "true");
                sender.setJavaMailProperties(p);
                MimeMessage message = sender.createMimeMessage();
                //这里的utf-8解决 邮件 内容乱码
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                //当前发送人邮箱（也就是自己）
                helper.setFrom(username);
                //发送到的邮箱地址
                helper.setTo(to);
                //邮件主题、标题
                helper.setSubject(subject);
                //内容
                helper.setText(text);

                sender.send(message);
        }

}
