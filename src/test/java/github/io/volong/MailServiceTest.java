package github.io.volong;

import github.io.volong.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @time 2019-04-12
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {


    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail("", "文件邮件测试", "测试文本邮件是否正常");
    }
}
