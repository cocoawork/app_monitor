package top.cocoawork.monitor.fetcher.service;

/**
 * The interface Remote email service.
 */
public interface RemoteEmailService {

    /**
     * Send email.
     *
     * @param to      the to
     * @param content the content
     */
    void sendEmail(String to, String content);

}
