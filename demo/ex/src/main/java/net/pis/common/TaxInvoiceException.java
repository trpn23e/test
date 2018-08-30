/*
 * (c)BOC
 */
package net.pis.common;

import net.pis.exception.AdaptingException;
import net.pis.exception.SBMSException;

/**
 *
 * @author jh,Seo
 */
public class TaxInvoiceException extends AdaptingException {

    public enum TaxInvoiceError {

        /**
         * 계산서 원천 데이타가 중복이거나 찾을 수 없음
         */
        @Deprecated
        X0003("X0003", "계산서 원천 데이타가 중복이거나 찾을 수 없음"),
        /**
         * 원본을 저장하지 못함
         */
        @Deprecated
        X0004("X0004", "원본 저장 실패"),
        /**
         * 원본을 저장하지 못함
         */
        CANNOT_STORE_TAXINVOICE("53209", "원본 저장 실패"),
        /**
         * Tax invoice marshaling exception
         */
        CANNOT_CREATE_TAX_INVOICE("53200", "전자 세금 계산서 원본 생성 실패"),
        /**
         * Tax invoice unmarshaling exception
         */
        CANNOT_PARSE_TAX_INVOICE("53201", "전자 세금 계산서 원본 분석 실패"),
        CANNOT_FIND_TAXIVOICE_DATA("53202", "상태변경 대상 데이타를 찾을 수 없거나 하나 이상임");

        private final String code;
        private final String message;

        TaxInvoiceError(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public String getErrorMessage() {
            return this.message;
        }
    }

    private final String domain = "System";

    public TaxInvoiceException(SBMSException se) {
        super(se.getCodeId(), se.getMessage());
    }

    public TaxInvoiceException(TaxInvoiceError errorCode, String message) {
        super(errorCode.name(), message);
    }

    public TaxInvoiceException(TaxInvoiceError errorCode, Exception e) {
        super(errorCode.name(), e);

    }

    public TaxInvoiceException(String errorCode, String message) {
        super(errorCode, message);

    }

    @Override
    public String getDomain() {
        return this.domain;
    }
}
