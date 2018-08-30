/*
 * (c)BOC
 */
package net.pis.exception;

/**
 * 공용 SBMS Server 모듈 예외 클래스
 *
 * @author jh,Seo
 */
public class ServerException extends SBMSException {

    private String activityLogId;


    public ServerException(String codeId, String message) {
        super(codeId, message);
    }

    public ServerException(String codeId, Exception e) {
        super(codeId, e);
    }

    public String getActivityLogId() {
        return activityLogId;
    }

    public void setActivityLogId(String activityLogId) {
        this.activityLogId = activityLogId;
    }

}
