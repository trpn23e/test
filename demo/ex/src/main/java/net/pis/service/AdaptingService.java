/*
 * (c)BOC
 */
package net.pis.service;

import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;

/**
 * 어댑팅 서비스
 * <p>
 * 비즈니스어플리케이션과 중계서버간 데이타를 처리한다.
 * </p>
 *
 * @author jh,Seo
 */
public interface AdaptingService {

    /**
     * Adapt Message
     *
     * @param messageMetaInfo
     */
    void adapt(MessageMetaInfo messageMetaInfo) throws SBMSException;

}
