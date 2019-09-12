package com.example.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;

@RestController
public class test1 {

    @Autowired
    JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String form;

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    @GetMapping("hello")
    public String  hello(){

        //建立发送有邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发送者
        mailMessage.setFrom(form);
        //接收者
        mailMessage.setTo("3185986196@qq.com");
        //发送标题
        mailMessage.setSubject("嗨喽");
        //发送内容
        mailMessage.setText("hello world！");


            jms.send(mailMessage);
            System.out.println("邮件发送成功");

        return "11";
    }

    @GetMapping("getrenzheng")
    public String renzheng(){

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

        int mobile_code = (int)((Math.random()*9+1)*100000);

        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "C31165523"), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", "9ec4733cbb61231ac2caf4e150cf3aac  "),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", "15574041438"),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();

            //System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            // Release connection
            method.releaseConnection();
        }
        return "22";
    }

}
