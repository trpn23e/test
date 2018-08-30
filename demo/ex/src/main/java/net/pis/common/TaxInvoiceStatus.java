/*
 * (c)BOC
 */
package net.pis.common;

/**
 *
 * @author jh,Seo
 */
public enum TaxInvoiceStatus {

    /**
     * 매출 저장
     */
    Saved("S"),
    /**
     * 역매입 요청 저장
     */
    SavedReversed("A"),
    /**
     * 미승인
     */
    Unapproved("I"),
    /**
     * 수신승인
     */
    Confirmation("C"),
    /**
     * 수신승인 거부
     */
    Rejection("R"),
    /**
     * 발행 취소
     */
    Canceled("O"),
    /**
     * 역매입 요청
     */
    IssueRequest("V"),
    /**
     * 역매입요청 취소
     */
    IssueRequestCanceled("W"),
    /**
     * 역매출 거부
     */
    IssueRequestRejection("T");

    private final String code;

    TaxInvoiceStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
