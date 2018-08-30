/*
 * (c)BOC
 */
package net.pis.dto;

import net.pis.common.adapter.FullDateFormatAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
/**
 * 관리정보
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "issueDateTime", "referencedDocument"})
public class ExchangedDocument {

    /**
     * 서비스 관리 번호
     */
    @XmlElement(name = "ID")
    private String id;
    /**
     * 사업자 관리 번호
     */
    @XmlElement(name = "ReferencedDocument")
    private ReferencedDocument referencedDocument;
    /**
     * 발행일시
     */
    @XmlElement(name = "IssueDateTime")
    @XmlJavaTypeAdapter(FullDateFormatAdapter.class)
    private Date issueDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReferencedDocument getReferencedDocument() {
        return referencedDocument;
    }

    public void setReferencedDocument(ReferencedDocument referencedDocument) {
        this.referencedDocument = referencedDocument;
    }

    public Date getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(Date issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

}
