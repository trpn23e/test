/*
 * (c)BOC
 */
package net.pis.service;


import net.pis.message.MessageMetaInfo;

import java.util.List;

/**
 *
 * @author jh,Seo
 */
public interface PollingService {

    /**
     * 폴링 매서드
     *
     * @return
     */
    @Deprecated
    public List<?> execute();

    public List<MessageMetaInfo> execute2();

    @Deprecated
    public void execute3();

    public void poll();

}
