/*
 * (c)BOC
 */
package net.pis.service;

import net.pis.exception.AdaptingException;
import net.pis.message.MessageMetaInfo;

/**
 * 일반적인 비동기 메세지 통신 서비스
 *
 * @author jh,Seo
 */
public interface DefaultAsyncService {

    /**
     * 요청 메세지를 송신한다.
     * <p>
     * 새로운 트랜잭션을 생성한다.
     *
     * @param messageMetaInfo
     */
    //@Transactional(propagation = Propagation.REQUIRED)
    void sendRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException;

    /**
     * 요청 메세지 송신에 대한 서버의 ACK 수신
     * <p>
     * sendRequest 에 대한 결과 처리부. 논리적인 비즈니스 처리 결과와 무관하게 이 스텝에서 송신자의 송신이 완료되었다고
     * 판단한다.
     *
     * @param messageMetaInfo
     */
    //@Transactional(propagation = Propagation.REQUIRED)
    void recieveAckAboutRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException;

    /**
     * 요청 메세지 송신에 대한 트랜잭션 결과 수신
     * <p>
     * sendRequest 에 대한 최종 결과 처리부. Request 메세지 송신에 대한 비즈니스 결과를 처리한다. 송신자에 대한
     * 트랜잭션이 최종적으로 완료된다.
     * <p>
     * 불특정하게 recieveAckAboutRequest 보다 먼저 도달할 수 있다. 이 경우 recieveAckAboutRequest
     * 처리는 무시해야 한다.
     * <p>
     * 최종 결과를 어댑터에게 전달하는것은 SBMS가 엄격하게 관리하며 이 스탭에서 발생하는 오류는 서버(스마트빌)가 자동으로 인식하지
     * 못한다.
     *
     * @param messageMetaInfo
     */
    //@Transactional(propagation = Propagation.REQUIRED)
    void receiveResult(MessageMetaInfo messageMetaInfo) throws AdaptingException;

    /**
     * 요청 메세지를 수신한다.
     *
     * @param messageMetaInfo
     */
    //@Transactional(propagation = Propagation.REQUIRED)
    void receiveRequest(MessageMetaInfo messageMetaInfo) throws AdaptingException;

    /**
     * 요청 메세지 수신에 따른 SBMS의 결과 송신
     * <p>
     * receiveRequest 대한 결과를 서버(스마트빌)에 송신한다.
     * <p>
     * 일반적으로 receiveRequest 프로그램에서 이 매서드가 자동적으로 호출되도록 처리하여야 한다.
     *
     * @param messageMetaInfo
     */
    //@Transactional(propagation = Propagation.REQUIRED)
    void sendResult(MessageMetaInfo messageMetaInfo) throws AdaptingException;

    /**
     * 결과 메세지 송신에 대한 서버의 ACK 수신
     * <p>
     * sendResult 에 대한 결과 처리부. 수신자 입장에서 트랜잭션이 완료 되었음을 처리한다.
     *
     * @param messageMetaInfo
     */
    //@Transactional(propagation = Propagation.REQUIRED)
    void recieveAckAboutResult(MessageMetaInfo messageMetaInfo) throws AdaptingException;

}
