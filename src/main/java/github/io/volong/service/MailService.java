package github.io.volong.service;


/**
 * @time 2019-04-12
 */
public interface MailService {


    /**
     * 发送简单文本邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送包含 html 的邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送包含附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送包含静态资源的邮件
     *
     * @param s
     * @param s1
     * @param content
     * @param imgPath
     * @param rscId
     */
    void sendInlineResourceMail(String s, String s1, String content, String imgPath, String rscId);
}
