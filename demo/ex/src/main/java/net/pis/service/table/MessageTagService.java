/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.DefaultTableService;
import net.pis.dto.MessageTagDTO;
import net.pis.message.MessageMetaInfo;

/**
 * SBMS_MESSAGE_TAG 테이블 서비스
 *
 * @author jh,Seo
 */
public interface MessageTagService extends DefaultTableService<MessageTagDTO> {

    MessageTagDTO getMessageTagDTO(String messageTagId);

    MessageTagDTO getMessageTag(MessageMetaInfo messageMetaInfo);

    void updateMessageTag(MessageMetaInfo messageMetaInfo, String status);
}
