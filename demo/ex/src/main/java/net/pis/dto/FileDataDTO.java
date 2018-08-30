/**
 * (c)BOC
 */
package net.pis.dto;

import java.io.Serializable;

/**
 * TABLE DTO (SBMS_FILE_DATA)
 * <p>
 * Created by achiz on 14. 7. 10.
 *
 * SBMS_FILE_DATA
 * </p>
 */
public class FileDataDTO implements Serializable {

    private static final long serialVersionUID = 4176267499050334992L;

    /**
     * UUID *
     */
    private String fileDataId;

    /**
     * UUID *
     */
    private String messageTagId;

    /**
     * MESSAGE_TAG_SEQ *
     */
    private double messageTagSeq;

    /**
     * 파일명 *
     */
    private String fileName;

    /**
     * 파일크기 *
     */
    private long fileSize;

    /**
     * 파일데이터 *
     */
    private byte[] fileData;

    /**
     * 파일구분 *
     */
    private String fileGubun;

    /**
     * 파일 일련번호 *
     */
    private String fileSeq;

    /**
     * 등록시간 *
     */
    private String regTimestamp;

    public String getFileDataId() {
        return fileDataId;
    }

    public void setFileDataId(String fileDataId) {
        this.fileDataId = fileDataId;
    }

    public String getMessageTagId() {
        return messageTagId;
    }

    public void setMessageTagId(String messageTagId) {
        this.messageTagId = messageTagId;
    }

    public double getMessageTagSeq() {
        return messageTagSeq;
    }

    public void setMessageTagSeq(double messageTagSeq) {
        this.messageTagSeq = messageTagSeq;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileGubun() {
        return fileGubun;
    }

    public void setFileGubun(String fileGubun) {
        this.fileGubun = fileGubun;
    }

    public String getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(String fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getRegTimestamp() {
        return regTimestamp;
    }

    public void setRegTimestamp(String regTimestamp) {
        this.regTimestamp = regTimestamp;
    }
}
