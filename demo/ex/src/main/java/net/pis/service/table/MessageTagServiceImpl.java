/*
 * (c)BOC
 */
package net.pis.service.table;

import net.pis.dao.dti.MessageTagMapper;
import net.pis.dto.MessageTagDTO;
import net.pis.message.MessageMetaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Service
// @Transactional(transactionManager = "transactionManagerSBMS")
@Transactional(transactionManager = "db2TransactionManager")
public class MessageTagServiceImpl implements MessageTagService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageTagMapper mapper;

    @Override
    public int create(MessageTagDTO t) {
        logger.info("=== MessageTagServiceImpl.create() ==== ");
        return mapper.create(t);
    }

    @Override
    public List<MessageTagDTO> read(MessageTagDTO t) {

        return mapper.read(t);
    }

    @Override
    public int update(MessageTagDTO t) {
        return mapper.update(t);
    }

    @Override
    public int delete(MessageTagDTO t) {
        return mapper.delete(t);
    }

    @Override
    public MessageTagDTO getMessageTagDTO(String messageTagId) {

        MessageTagDTO messageTagDTOResult = null;

        MessageTagDTO messageTagDTOParam = new MessageTagDTO();
        messageTagDTOParam.setMessageTagId(messageTagId);

        List<MessageTagDTO> messageTagDTOList = this.read(messageTagDTOParam);

        if (!messageTagDTOList.isEmpty()) {
            messageTagDTOResult = messageTagDTOList.get(0);
        }

        return messageTagDTOResult;

    }

    @Override
    public MessageTagDTO getMessageTag(MessageMetaInfo messageMetaInfo) {

        MessageTagDTO messageTagDTOResult = null;

        MessageTagDTO messageTagDTOParam = new MessageTagDTO();
        messageTagDTOParam.setMessageTagId(messageMetaInfo.getMessageTagId());
        messageTagDTOParam.setMessageId(messageMetaInfo.getMessageId());

        List<MessageTagDTO> messageTagDTOList = this.read(messageTagDTOParam);

        if (!messageTagDTOList.isEmpty()) {
            messageTagDTOResult = messageTagDTOList.get(0);
        }

        return messageTagDTOResult;
    }

    @Override
    public void updateMessageTag(MessageMetaInfo messageMetaInfo, String status) {

        MessageTagDTO messageTagDTOParam = new MessageTagDTO();
        messageTagDTOParam.setMessageStatus(status);
        messageTagDTOParam.setMessageId(messageMetaInfo.getMessageId());
        messageTagDTOParam.setMessageTagId(messageMetaInfo.getMessageTagId());
        mapper.update(messageTagDTOParam);

    }
}
