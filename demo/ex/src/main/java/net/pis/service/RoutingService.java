/*
 * (c)BOC
 */
package net.pis.service;

import net.pis.exception.RoutingException;
import net.pis.message.MessageMetaInfo;

/**
 * 라우팅 서비스
 *
 * @author jh,Seo
 */
public interface RoutingService {

    final String DOMAIN = "Router";

    void route(MessageMetaInfo messageMetaInfo) throws RoutingException;

}
