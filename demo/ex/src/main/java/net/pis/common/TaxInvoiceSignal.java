/*
 * (c)BOC
 */
package net.pis.common;

/**
 * 전자(세금)계산서에 사용되는 비즈니스 플래그.
 * <p>
 * 스마트빌 연동 시스템이 제공하는 비즈니스 서비스 목록이다. 정의는 스마트빌에서 이루어지며 SBMS시스템은 그 인터페이스에 따른다. 각
 * 시그널은 독립적인 비즈니스 서비스이지만 논리적으로 의존관계에 놓여 있는 항목들이 다수 존재한다. 예를 들면 상태변경은 발행에 의존한다.
 * </p>
 *
 * @author jh,Seo
 */
public enum TaxInvoiceSignal implements Signal {
    /**
     * 전자(세금)계산서 발행
     */
    ARISSUE(MessageType.REQUEST),
    /**
     * 전자(세금)계산서 발행 결과
     */
    ARISSUE_FINISH(MessageType.RESULT),
    /**
     * 전자(세금)계산서 발행 예정 요청
     */
    WARREQUEST(MessageType.REQUEST),
    /**
     * 전자(세금)계산서 발행 예정 요청 결과
     */
    WARREQUEST_FINISH(MessageType.RESULT),
    /**
     * 전자(세금)계산서 발행예정 발행
     */
    WARISSUE(MessageType.REQUEST),
    /**
     * 전자(세금)계산서 발행예정 발행 결과
     */
    WARISSUE_FINISH(MessageType.RESULT),
    /**
     * 전자(세금)계산서 역발행 요청
     */
    RARREQUEST(MessageType.REQUEST),
    /**
     * 전자(세금)계산서 역발행 요청 결과
     */
    RARREQUEST_FINISH(MessageType.RESULT),
    /**
     * 세금계산서 역발행
     */
    RARISSUE(MessageType.REQUEST),
    /**
     * 세금계산서 역발행
     */
    RARISSUE_FINISH(MessageType.RESULT),
    /**
     * 거래명세서 발행
     */
    DETAILARISSUE(MessageType.REQUEST),
    /**
     * 거래명세서 발행
     */
    DETAILARISSUE_FINISH(MessageType.RESULT),
    /**
     * 거래명세서 발행예정 요청
     */
    WDETAILREQUEST(MessageType.REQUEST),
    /**
     * 거래명세서 발행예정 요청
     */
    WDETAILREQUEST_FINISH(MessageType.RESULT),
    /**
     * 거래명세서 발행예정 발행
     */
    WDETAILISSUE(MessageType.REQUEST),
    /**
     * 거래명세서 발행예정 발행
     */
    WDETAILISSUE_FINISH(MessageType.RESULT),
    /**
     * 거래명세서 역발행 요청
     */
    RDETAILREQUEST(MessageType.REQUEST),
    /**
     * 거래명세서 역발행 요청
     */
    RDETAILREQUEST_FINISH(MessageType.RESULT),
    /**
     * 거래명세서 역발행
     */
    RDETAILISSUE(MessageType.REQUEST),
    /**
     * 거래명세서 역발행
     */
    RDETAILISSUE_FINISH(MessageType.RESULT),
    /**
     * 세금 계산서 상태변경
     */
    CHGSTATUS(MessageType.STATUS),
    /**
     * 세금 계산서 상태변경
     */
    CHGSTATUS_FINISH(MessageType.RESULT),
    /**
     * 상태변경 승인
     */
    APPROVE(MessageType.STATUS),
    /**
     * 상태변경 승인
     */
    APPROVE_FINISH(MessageType.RESULT),
    /**
     * 상태변경 승인거부
     */
    REJECT(MessageType.STATUS),
    /**
     * 상태변경 승인거부
     */
    REJECT_FINISH(MessageType.RESULT),
    /**
     * 메일 재전송
     */
    SENDMAIL(MessageType.UTIL),
    /**
     * 메일 재전송
     */
    SENDMAIL_FINISH(MessageType.RESULT),
    /**
     * SMS 재전송
     *
     */
    SENDSMS(MessageType.UTIL),
    /**
     * SMS 재전송
     *
     */
    SENDSMS_FINISH(MessageType.RESULT),
    /**
     * 네이트온 재알림
     */
    SENDNATEON(MessageType.UTIL),
    /**
     * 네이트온 재알림
     */
    SENDNATEON_FINISH(MessageType.RESULT),
    /**
     * 국세청 전송 요청
     */
    NTS_REQUEST(MessageType.OpenAPI),
    /**
     * 국세청 전송 요청
     */
    NTS_REQUEST_FINISH(MessageType.RESULT),
    /**
     * 첨부파일전송
     */
    FILEUPLOAD(MessageType.REQUEST),
    /**
     * 첨부파일전송
     */
    FILEUPLOAD_FINISH(MessageType.RESULT),
    /**
     * 타사ASP 서비스
     */
    DIFASPUP(MessageType.UTIL),
    /**
     * 타사ASP 서비스
     */
    DIFASPUP_FINISH(MessageType.RESULT),
    /**
     * 신한카드전송
     */
    RESEARCH(MessageType.UTIL),
    /**
     * 타사ASP 서비스
     */
    RESEARCH_FINISH(MessageType.RESULT),
    /**
     * 발행취소
     */
    CANCELALL(MessageType.STATUS),
    /**
     * 발행취소
     */
    CANCELALL_FINISH(MessageType.RESULT),
    /**
     * 역발행 요청 취소
     */
    CANCELRREQUEST(MessageType.STATUS),
    /**
     * 역발행 요청 취소
     */
    CANCELRREQUEST_FINISH(MessageType.RESULT),
    /**
     * 역발행 요청 거부
     */
    RIREJECT(MessageType.STATUS),
    /**
     * 역발행 요청 거부
     */
    RIREJECT_FINISH(MessageType.RESULT),
    /**
     * SAP 전용 HELLO SIGNAL
     */
    HELLO(MessageType.OpenAPI),
    /**
     * SAP 전용 HELLO SIGNAL
     */
    HELLO_FINISH(MessageType.RESULT),
    /**
     * 타사 발행 XML 업로드
     */
    UPLOAD_XML(MessageType.OpenAPI),
    /**
     * 타사 발행 XML 업로드
     */
    UPLOAD_XML_FINISH(MessageType.RESULT),
    /**
     * 세금계산서 보기
     */
    VIEW(MessageType.InnerService),
    VIEW_FINISH(MessageType.RESULT),
    /**
     * 거래명세서 보기
     */
    DETAILVIEW(MessageType.InnerService),
    DETAILVIEW_FINISH(MessageType.RESULT),
    /**
     * 인증서 관리 (SAP)
     */
    MANAGE_CERT(MessageType.InnerService),
    /**
     * 인증서 관리 (SAP)
     */
    MANAGE_CERT_FINISH(MessageType.RESULT),
    /**
     * XML 파싱 (SAP)
     */
    PARSE_XML(MessageType.InnerService),
    /**
     * XML 파싱 (SAP)
     */
    PARSE_XML_FINISH(MessageType.RESULT),
    /**
     * 부가정보 조회 (SAP)
     */
    SUB_INFO(MessageType.OpenAPI),
    /**
     * 부가정보 조회 (SAP)
     */
    SUB_INFO_FINISH(MessageType.RESULT),
    /**
     * 전자(세금)계산서 상태 조회
     */
    DTI_STATUS(MessageType.OpenAPI),
    /**
     * 전자(세금)계산서 상태 조회
     */
    DTI_STATUS_FINISH(MessageType.RESULT),
    /**
     * 통계 정보 요청
     */
    STATS_REQUEST(MessageType.REQUEST),
    STATS_REQUEST_FINISH(MessageType.RESULT),
    /**
     * 통계 정보 결과
     */
    STATS_RESULT(MessageType.REQUEST),
    STATS_RESULT_FINISH(MessageType.RESULT),
    
    
    
    /**
     * #멀티발행# 정매출
     */
    ARISSUE_S(MessageType.REQUEST),
    ARISSUE_S_FINISH(MessageType.RESULT),
    /**
     * #멀티발행# 역매출
     */
    RARISSUE_S(MessageType.REQUEST),
    RARISSUE_S_FINISH(MessageType.RESULT),
    /**
     * #멀티발행# 정매출 거래명세서
     */
    DETAILARISSUE_S(MessageType.REQUEST),
    DETAILARISSUE_S_FINISH(MessageType.RESULT),
    /**
     * #멀티발행# 상태변경
     */
    CHGSTATUS_S(MessageType.STATUS),
    CHGSTATUS_S_FINISH(MessageType.RESULT);
    
    

    private final MessageType messageType;

    TaxInvoiceSignal(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public MessageType toMessageType() {
        return messageType;
    }

    @Override
    public String toString() {

        return this.name();
    }
}
