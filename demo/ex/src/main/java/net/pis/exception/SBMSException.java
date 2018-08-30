/*
 * (c)BOC
 */
package net.pis.exception;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jh,Seo
 */
public class SBMSException extends Exception {

    private String codeId;
    private String className;
    private String methodName;

    private int lineNumber;

    private Date timestamp;

    protected String domain = "DEFAULT";

    public enum SBMSError {

        UNKOWN_ERROR("55555", "SBMS Unkown Error"),
        JMS_ERROR("55501", "JMS Default Error"),
        ROUTING_ERROR("55502", "라우팅 정보가 없습니다."),
        INVALID_CERTIFICATIE_VID("55502", "인증서 신원 확인 불일치"),
        INVALID_CERTIFICATE("55555", "인증서 검증 실패");

        private final String code;
        private final String message;

        private SBMSError(String code, String message) {
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

    public SBMSException(String codeId, String message) {
        super(message);

        this.codeId = codeId;
        this.timestamp = Calendar.getInstance().getTime();

        // FIXME : Using this as parameter can be dangerous in the contructor because the object is not fully initialized.
        //this.initialize(this);
    }

    public SBMSException(String codeId, Exception e) {
        super(e.getMessage());

        this.codeId = codeId;

        this.initialize(e);
    }

    private void initialize(Exception e) {

        StackTraceElement element = this.extractStackTraceElement(e);

        this.className = element.getClassName();
        this.methodName = element.getMethodName();
        this.lineNumber = element.getLineNumber();

        this.timestamp = Calendar.getInstance().getTime();

    }

    private StackTraceElement extractStackTraceElement(Exception e) {
        StackTraceElement[] elements = e.getStackTrace();
        for (StackTraceElement element : elements) {
            String clazzName = element.getClassName();
            if (clazzName.startsWith("com.bizon.sbms.")) {
                return element;
            }
        }
        return elements[0];
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDomain() {
        return domain;
    }

}
