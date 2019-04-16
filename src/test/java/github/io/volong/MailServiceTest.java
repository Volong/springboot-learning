package github.io.volong;

import github.io.volong.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @time 2019-04-12
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {


    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail("", "文件邮件测试", "测试文本邮件是否正常");
    }

    @Test
    public void testHtmlMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("to_email", "html邮件测试", content);
    }

    @Test
    public void testSendAttachmentsMail() {
        String file = "F:\\project\\springboot-learning\\src\\main\\resources\\application.properties";
        mailService.sendAttachmentsMail("to_email", "测试带附件的邮件", "详情见附件", file);
    }

    @Test
    public void testSendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\Volong\\Pictures\\lblogo.jpg";

        mailService.sendInlineResourceMail("to_email", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void testSendTemplateMail() {
        Context context = new Context();
        context.setVariable("id", "1");
        String emailTemplate = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("to_email", "测试模版邮件", emailTemplate);
    }
}
