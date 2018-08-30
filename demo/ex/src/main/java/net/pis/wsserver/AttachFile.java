package net.pis.wsserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * AttachFile complex type에 대한 Java 클래스입니다.
 *
 * <p>
 * 다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 *
 * <pre>
 * &lt;complexType name="AttachFile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FILE_DATA" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *         &lt;element name="FILE_FULL_PATH" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FILE_GUBUN" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FILE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FILE_SAVE_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FILE_SEQ" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FILE_SIZE" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttachFile", namespace = "http://schemas.datacontract.org/2004/07/BizOn.Biz.Model", propOrder = {
    "filedata",
    "filefullpath",
    "filegubun",
    "filename",
    "filesavetype",
    "fileseq",
    "filesize"
})
public class AttachFile {

    @XmlElement(name = "FILE_DATA", required = true, nillable = true)
    protected byte[] filedata;
    @XmlElement(name = "FILE_FULL_PATH", required = true, nillable = true)
    protected String filefullpath;
    @XmlElement(name = "FILE_GUBUN", required = true, nillable = true)
    protected String filegubun;
    @XmlElement(name = "FILE_NAME", required = true, nillable = true)
    protected String filename;
    @XmlElement(name = "FILE_SAVE_TYPE", required = true, nillable = true)
    protected String filesavetype;
    @XmlElement(name = "FILE_SEQ", required = true, nillable = true)
    protected String fileseq;
    @XmlElement(name = "FILE_SIZE")
    protected long filesize;

    /**
     * filedata 속성의 값을 가져옵니다.
     *
     * @return possible object is byte[]
     */
    public byte[] getFILEDATA() {
        return filedata;
    }

    /**
     * filedata 속성의 값을 설정합니다.
     *
     * @param value allowed object is byte[]
     */
    public void setFILEDATA(byte[] value) {
        this.filedata = value;
    }

    /**
     * filefullpath 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFILEFULLPATH() {
        return filefullpath;
    }

    /**
     * filefullpath 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFILEFULLPATH(String value) {
        this.filefullpath = value;
    }

    /**
     * filegubun 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFILEGUBUN() {
        return filegubun;
    }

    /**
     * filegubun 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFILEGUBUN(String value) {
        this.filegubun = value;
    }

    /**
     * filename 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFILENAME() {
        return filename;
    }

    /**
     * filename 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFILENAME(String value) {
        this.filename = value;
    }

    /**
     * filesavetype 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFILESAVETYPE() {
        return filesavetype;
    }

    /**
     * filesavetype 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFILESAVETYPE(String value) {
        this.filesavetype = value;
    }

    /**
     * fileseq 속성의 값을 가져옵니다.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFILESEQ() {
        return fileseq;
    }

    /**
     * fileseq 속성의 값을 설정합니다.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFILESEQ(String value) {
        this.fileseq = value;
    }

    /**
     * filesize 속성의 값을 가져옵니다.
     *
     */
    public long getFILESIZE() {
        return filesize;
    }

    /**
     * filesize 속성의 값을 설정합니다.
     *
     */
    public void setFILESIZE(long value) {
        this.filesize = value;
    }

}
