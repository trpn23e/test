/*
 * (c)BOC
 */
package net.pis.exception;

/**
 *
 * @author jh,Seo
 */
public class ConnectingException extends ServerException {

    private final String domain = "Connector";

    public enum ConnectingError {

        UNKNOWN_CONNECTING_EXCEPTION("59000", "Default connecting exception."),
        MOMENTARY_NETWORK_ERROR("59002", "일시적 네트워크 장애"),
        CANNOT_SOLVE_DNS("59001", "알 수 없는 호스트 입니다.");

        private final String code;
        private final String message;

        private ConnectingError(String code, String message) {
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

    public ConnectingException(String codeId, String message) {
        super(codeId, message);
    }

    @Override
    public String getDomain() {
        return this.domain;
    }

}
