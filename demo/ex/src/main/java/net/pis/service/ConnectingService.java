/*
 * (c)BOC
 */
package net.pis.service;


import net.pis.exception.ConnectingException;
import net.pis.message.MessageMetaInfo;

/**
 * 컨넥팅 서비스
 *
 * @author jh,Seo
 */
public interface ConnectingService {

    final String DOMAIN = "Connector";

    final String SUCCESS = "30000";

    /**
     * 메세지 서버로 수신된 메세지를 처리
     *
     * @param object ack signal
     * @return
     * @throws ConnectingException
     */
    Object connectToMessageServer(Object object) throws ConnectingException;

    /**
     * 스마트빌로 메세지 송신
     *
     * @param messageMetaInfo
     * @throws ConnectingException
     */
    void connectToSmartBill(MessageMetaInfo messageMetaInfo) throws ConnectingException;
}
