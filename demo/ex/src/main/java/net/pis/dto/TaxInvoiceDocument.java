/*
 * (c)BOC
 */
package net.pis.dto;

import net.pis.common.adapter.BasicDateFormatAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collection;
import java.util.Date;
/**
 * 기본정보
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"issueId", "typeCode", "descriptionText", "issueDateTime",
    "amendmentStatusCode", "purposeCode", "referencedImportDocument",
    "originalIssueId"})
public class TaxInvoiceDocument {

    @XmlElement(name = "IssueID")
    private String issueId;
    @XmlElement(name = "TypeCode")
    private String typeCode;

    @XmlElement(name = "DescriptionText")
    private Collection<String> descriptionText;

    @XmlElement(name = "IssueDateTime")
    @XmlJavaTypeAdapter(BasicDateFormatAdapter.class)
    private Date issueDateTime;

    @XmlElement(name = "AmendmentStatusCode")
    private String amendmentStatusCode;
    @XmlElement(name = "PurposeCode")
    private String purposeCode;
    @XmlElement(name = "OriginalIssueID")
    private String originalIssueId;

    @XmlElement(name = "ReferencedImportDocument")
    private ReferencedImportDocument referencedImportDocument;

    public String getIssueId() {
        return issueId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public Date getIssueDateTime() {
        return issueDateTime;
    }

    public String getAmendmentStatusCode() {
        return amendmentStatusCode;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public String getOriginalIssueId() {
        return originalIssueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public void setIssueDateTime(Date issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public void setAmendmentStatusCode(String amendmentStatusCode) {
        this.amendmentStatusCode = amendmentStatusCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public void setOriginalIssueId(String originalIssueId) {
        this.originalIssueId = originalIssueId;
    }

    public ReferencedImportDocument getReferencedImportDocument() {
        return referencedImportDocument;
    }

    public void setReferencedImportDocument(ReferencedImportDocument referencedImportDocument) {
        this.referencedImportDocument = referencedImportDocument;
    }

    public Collection<String> getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(Collection<String> descriptionText) {
        this.descriptionText = descriptionText;
    }

}
