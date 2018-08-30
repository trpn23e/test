/**
 * (c)BOC
 */
package net.pis.dto.table;

/**
 * TABLE DTO (DELIVERY_DTI_FILE)
 * <p>
 * Created by achiz on 2014-12-22.
 * </p>
 */
public class DeliveryDtiFileDTO {

    private String conversationId;
    private String fileSeq;
    private String eventId;
    private String fileName;
    private String fileSaveType;
    private long fileSize;
    private byte[] fileBinary;
    private String fileFullPath;
    private String fileStatus;
    private String errorCode;
    private String errorMessage;
    private String creationBy;
    private String creationDate;
    private String lastUpdateBy;
    private String lastUpdateDate;
    private String inOut;
    private String extXML;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(String fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSaveType() {
        return fileSaveType;
    }

    public void setFileSaveType(String fileSaveType) {
        this.fileSaveType = fileSaveType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileBinary() {
        return fileBinary;
    }

    public void setFileBinary(byte[] fileBinary) {
        this.fileBinary = fileBinary;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCreationBy() {
        return creationBy;
    }

    public void setCreationBy(String creationBy) {
        this.creationBy = creationBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public String getExtXML() {
        return extXML;
    }

    public void setExtXML(String extXML) {
        this.extXML = extXML;
    }
}
