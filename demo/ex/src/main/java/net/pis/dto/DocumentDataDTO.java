/**
 * (c)BOC
 */
package net.pis.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * TABLE DTO (SBMS_DOCUMENT_DATA)
 *
 *
 * @author achiz
 * @since 14. 7. 10.
 *
 */
public class DocumentDataDTO implements Serializable {

	private static final long serialVersionUID = 6851534223335209439L;

	/**
	 * UUID *
	 */
	private String documentDataId;

	/**
	 * UUID *
	 */
	private String messageTagId;

	/**
	 * 순번 *
	 */
	private int seq;

	/**
	 * 문서종류 *
	 */
	private String documentType;

	/**
	 * XML문서 전문 *
	 */
	private String documentData;
	/**
	 * 등록시간 *
	 */
	private Date regTimestamp;

	public String getMessageTagId() {
		return messageTagId;
	}

	public void setMessageTagId(String messageTagId) {
		this.messageTagId = messageTagId;
	}

	public String getDocumentData() {
		return documentData;
	}

	public void setDocumentData(String documentData) {
		this.documentData = documentData;
	}

	public Date getRegTimestamp() {
		return regTimestamp;
	}

	public void setRegTimestamp(Date regTimestamp) {
		this.regTimestamp = regTimestamp;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentDataId() {
		return documentDataId;
	}

	public void setDocumentDataId(String documentDataId) {
		this.documentDataId = documentDataId;
	}
}
