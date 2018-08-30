/**
 * (c)BOC
 */
package net.pis.dto.table;

import java.io.Serializable;

/**
 *
 * TABLE DTO (XXSB_DTI_INTEFACE)
 * <p>
 * Created by achiz on 14. 7. 14.
 *
 * XXSB_DTI_INTERFACE
 * </p>
 */
public class DTIApproveIdDTO implements Serializable {

    private static final long serialVersionUID = 8184608183692402640L;

    private String ntsCode;
    private String linkcompanycode;
    private int seqNo;

    public String getNtsCode() {
        return ntsCode;
    }

    public void setNtsCode(String ntsCode) {
        this.ntsCode = ntsCode;
    }

    public String getLinkcompanycode() {
        return linkcompanycode;
    }

    public void setLinkcompanycode(String linkcompanycode) {
        this.linkcompanycode = linkcompanycode;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

}
