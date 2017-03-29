package com.woodpecker.webframework.util;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class WoodpeckerMailSender {

    private final static String senderTemplate = "#sender# <#fromAddress#>";

    /** E-mail发送的工具类 */
    private JavaMailSender mailSender;

    /** E-mail发送的模版类 */
    private VelocityEngine velocityEngine;

    /** 发件人姓名 */
    private String sender;

    /** 发件人邮箱 */
    private String fromAddress;

    /** 发件人 */
    private String from;

    /**
     * 构造函数
     * @param mailSender JavaMailSender邮件发送类
     * @param velocityEngine 邮件模版类
     * @param sender 发件人姓名
     * @param fromAddress 发件人邮件地址
     */
    public WoodpeckerMailSender(JavaMailSender mailSender, VelocityEngine velocityEngine, String sender, String fromAddress) {
        this.mailSender = mailSender;
        this.velocityEngine = velocityEngine;
        this.sender = sender;
        this.fromAddress = fromAddress;

        from = senderTemplate;
        from = from.replaceAll("#sender#", this.sender);
        from = from.replaceAll("#fromAddress#", this.fromAddress);
    }

    /**
     * 发送邮件
     * @param mail FJMailMessage 邮件相关参数（to：收件人邮箱地址， subject：邮件主题， model：邮件模版文件名）
     * @return 发送邮件结果（false的场合，发送失败）
     */
    public boolean send(WoodpeckerMailMessage mail) {
        boolean isSuccess = true;

        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @SuppressWarnings("deprecation")
				public void prepare(MimeMessage mimeMessage) throws Exception {
                    // mail设置
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, WoodpeckerConstant.CHARSET_UTF8);
                    message.setFrom(new InternetAddress(from));
                    message.setTo(mail.getTo());
                    message.setSubject(mail.getSubject());
                    String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mail.getTemplate(), WoodpeckerConstant.CHARSET_UTF8, mail.getModel());
                    message.setText(text, true);
                }
            };
            mailSender.send(preparator);
        } catch(Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public String getSender() {
        return sender;
    }

    public String getFrom() {
        return from;
    }
}
