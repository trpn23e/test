/*
 * (c)BOC
 */
package net.pis.common;

/**
 *
 * @author jh,Seo
 */
public enum MessageStatus {

    /**
     * 인지
     */
    Recognition("R"),
    /**
     * 진행
     */
    Progress("P"),
    /**
     * 전송중
     */
    Sending("S"),
    /**
     * 전송완료
     */
    Sent("T"),
    /**
     * 전송실패
     */
    Failure("F"),
    /**
     * 완료
     */
    Complete("C"),
    /**
     * 오류
     */
    Error("E"),
    /**
     * 경고
     */
    Warning("W");

    private final String code;

    private MessageStatus(String code) {
        this.code = code;

    }

    public String getCode() {
        return this.code;
    }

    public static MessageStatus toMessageStatus(String code) {

        MessageStatus messageStatus = null;
        for (MessageStatus ms : MessageStatus.values()) {

            if (ms.getCode().equals(code)) {
                messageStatus = ms;
                break;
            }
        }

        return messageStatus;

    }

}
