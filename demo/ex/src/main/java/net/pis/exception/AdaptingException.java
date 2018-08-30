/*
 * (c)BOC
 */
package net.pis.exception;

/**
 *
 * @author jh,Seo
 */
public class AdaptingException extends ServerException {

    private final String domain = "Adapter";

    public enum AdaptingError {

        DEFAULT_ADPATING_EXCEPTION("53100", "Default adpating exception."),
        UNKOWN_SIGNAL("53600", "Unkown siganl."),
        CANNOT_FIND_ADAPTER("53700", "어댑터를 찾을 수 없음."),
		CANNOT_FIND_SENDER_AND_RECEIVER("53701", "송수신자를 판단할 수 없음"),
        ACK_EXCEPTION("53800", "ACK 처리 실패"),
        ACK_INTERFACE_EXCEPTION("53801", "인터페이스 ACK 처리 실패"),
        ACK_STATUS_EXCEPTION("53802", "STATUS ACK 처리 실패"),
        ACK_TAG_EXCEPTION("53803", "Message TAG ACK 처리 실패"),
        RESULT_EXCEPTION("53804", "Result 처리 실패"),
        RESULT_STATUS_EXCEPTION("53805", "STATUS RESULT 처리 실패"),
        RESULT_TAG_EXCEPTION("53806", "Message TAG RESULT 처리 실패"),
        CANNOT_FIND_STATUS("53900", "상태 정보를 찾을 수 없음"),
        SAVE_EXCEPTION("53810", "원본 저장 실패"),
        NO_DIRECTION("53811", "메세지의 방향을 알 수 없음"),
		
		
        @Deprecated
        A0001("A001", "알 수 없는 adapting 오류"),
        @Deprecated
        A0002("A002", "전송 메세지 분석 실패");

        private final String code;
        private final String message;

        private AdaptingError(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getErrorMessage() {
            return this.message;
        }

        public String getErrorCode() {
            return this.code;
        }
    }

    public AdaptingException(SBMSException se) {
        super(se.getCodeId(), se.getMessage());
    }

    public AdaptingException(String codeId, String message) {
        super(codeId, message);
    }

    public AdaptingException(String codeId, Exception e) {
        super(codeId, e);

    }

    @Override
    public String getDomain() {
        return this.domain;
    }

}
