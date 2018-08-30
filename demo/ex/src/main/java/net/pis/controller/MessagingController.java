/*
 * (c)BOC
 */
package net.pis.controller;


import net.pis.exception.SBMSException;
import net.pis.message.MessageMetaInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 메세지 컨트롤러
 *
 * @author jh,Seo
 */
@Controller
public interface MessagingController {

    /**
     * 스마트빌에서 수신한 메세지 처리
     *
     * @param messageMetaInfo
     */
    // @RequestMapping(value = "/inb", method = RequestMethod.GET)
    public void handleInbound(MessageMetaInfo messageMetaInfo) throws SBMSException;

    /**
     * 스마트빌로 송신할 메세지 처리
     *
     * @param messageMetaInfo
     * @throws SBMSException
     */
    // @RequestMapping(value = "/outb", method = RequestMethod.GET)
    public void handleOutbound(MessageMetaInfo messageMetaInfo) throws SBMSException;

}
