package com.woodpecker.webframework.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮件设置类
 * 说明：
 * 1.属性：
 *      template 模版文件名
 *      to 收件人邮箱地址
 *      subject 邮件主题
 *      model 邮件内容
 * 2.构造方法
 *      FJMailMessage(String template, String toAddress, Map<String, Object> model, String subject)
 *      创建指定参数的类实例，model = new HashMap<String, Object>();
 *      FJMailMessage(String template, String toAddress, Map<String, Object> model, String subject)
 *      创建指定参数的类实例，model = 传入的参数值;
 *      FJMailMessage()
 *      创建默认值的类实例。各属性需要单独调用seter赋值。
 * 3.方法
 *      addModelAttr(String key, String value)
 *      向属性model(邮件内容)里追加内容，一次只能追加一项内容。
 *      setModel(Map<String, Object> model)
 *      向属性model(邮件内容)里添加全部内容，注意会修改替换model全部内容
 * @author liqiang
 *
 */
public class WoodpeckerMailMessage {

    public WoodpeckerMailMessage() {
        this.template = "";
        this.to = "";
        this.subject = "";
        
        this.model = new HashMap<String, Object>();
    }

    public WoodpeckerMailMessage(String template, String toAddress, Map<String, Object> model, String subject) {
        this.template = template;
        this.to = toAddress;
        this.subject = subject;
        
        this.model = new HashMap<String, Object>();
        this.model.putAll(model);
    }

    public WoodpeckerMailMessage(String template, String toAddress, String subject) {
        this.template = template;
        this.to = toAddress;
        this.subject = subject;
        this.model = new HashMap<String, Object>();
    }

    /**
     * 添加邮件内容
     * @param key 模版键
     * @param value 邮件表示内容
     */
    public void addModelAttr(String key, String value) {
        model.put(key, value);
    }

    /** 模版 */
    private String template;

    /** 收件人 */
    private String to;

    /** 邮件内容 */
    Map<String, Object> model;

    /** 邮件主题 */
    private String subject;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    /**
     * 向属性model(邮件内容)里添加全部内容，注意会修改替换model全部内容
     * @param model 编辑好的属性model(邮件内容)
     */
    public void setModel(Map<String, Object> model) {
        this.model = new HashMap<String, Object>();
        this.model.putAll(model);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
