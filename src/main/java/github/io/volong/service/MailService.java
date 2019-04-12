package github.io.volong.service;


/**
 * @time 2019-04-12
 */
public interface MailService {


    void sendSimpleMail(String to, String subject, String content);
}
