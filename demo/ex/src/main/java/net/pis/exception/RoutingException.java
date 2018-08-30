/*
 * (c)BOC
 */
package net.pis.exception;

/**
 *
 * @author jh,Seo
 */
public class RoutingException extends ServerException {

    public final String DOMAIN = "Connector";

    public enum RoutingError {

        NO_DIRECTION("53811", "메세지의 방향을 알 수 없음"),
        UNKNOWN("53333", "unkown exception");

        private final String code;
        private final String message;

        private RoutingError(String code, String message) {
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

    public RoutingException(String codeId, String message) {
        super(codeId, message);
    }

    @Override
    public String getDomain() {
        return this.domain;
    }

}
