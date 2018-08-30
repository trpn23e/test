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
import java.util.Date;
/**
 * 수입(세금)계산서 관련 정보
 *
 * @author jh,Seo
 * @version 3.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "itemQuantity", "startDateTime", "endDateTime"})
public class ReferencedImportDocument {

    /**
     * 수입신고번호
     */
    @XmlElement(name = "ID")
    private String id;

    /**
     * 수입총건
     */
    @XmlElement(name = "ItemQuantity")
    private Integer itemQuantity;

    /**
     * 일괄발급 시작일
     */
    @XmlElement(name = "StartDateTime")
    @XmlJavaTypeAdapter(BasicDateFormatAdapter.class)
    private Date startDateTime;

    /**
     * 일괄발급 종료일
     */
    @XmlElement(name = "EndDateTime")
    @XmlJavaTypeAdapter(BasicDateFormatAdapter.class)
    private Date endDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

}
