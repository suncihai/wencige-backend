package dev.local.todo.util;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
public class JavaMailUtil {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Java邮件发送器
     */
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendSimpleEmail(String deliver, String[] receivers, String[] carbonCopys, String subject, String text)
            throws Exception {
        sendMimeMail(deliver, receivers, carbonCopys, subject, text, false, null);
    }

    public void sendTemplateEmail(String deliver, String[] receivers, String[] carbonCopys, String subject, String thymeleafTemplatePath,
                                  Map<String, Object> thymeleafTemplateVariable) throws Exception {
        String text = null;
        if (thymeleafTemplateVariable != null && thymeleafTemplateVariable.size() > 0) {
            Context context = new Context();
            thymeleafTemplateVariable.forEach((key, value)->context.setVariable(key, value));
            text = templateEngine.process(thymeleafTemplatePath, context);
        }
        sendMimeMail(deliver, receivers, carbonCopys, subject, text, true, null);
    }

    private void sendMimeMail(String deliver, String[] receivers, String[] carbonCopys, String subject, String text,
                              boolean isHtml, String[] attachmentFilePaths) throws Exception {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(deliver);
            helper.setTo(receivers);
            helper.setCc(carbonCopys);
            helper.setSubject(subject);
            helper.setText(text, isHtml);

            mailSender.send(mimeMessage);
            stopWatch.stop();
            logger.info("邮件发送成功, 花费时间{}秒", stopWatch.getTotalTimeSeconds());
        } catch (Exception e) {
            logger.error("邮件发送失败, 失败原因 :{} 。", e.getMessage(), e);
            throw e;
        }
    }

}