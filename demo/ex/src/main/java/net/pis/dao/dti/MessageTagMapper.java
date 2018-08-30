/*
 * (c)BOC
 */
package net.pis.dao.dti;


import net.pis.dao.dti.DefaultMapper;
import net.pis.dto.MessageTagDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author jh,Seo
 */
// public interface MessageTagMapper extends DefaultMapper<MessageTagDTO> {
@Component
public class MessageTagMapper implements DefaultMapper<MessageTagDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Override
    public int create(MessageTagDTO messageTagDTO) throws DataAccessException {
        return sqlSessionHandler.insert(
                // MAPPER_CANONICAL_NAME + ".insert", messageTagDTO);
                MAPPER_CANONICAL_NAME + ".create", messageTagDTO);
    }

    @Override
    public List<MessageTagDTO> read(MessageTagDTO messageTagDTO) throws DataAccessException {
        return sqlSessionHandler.selectList(
                MAPPER_CANONICAL_NAME + ".read", messageTagDTO);
    }

    @Override
    public int update(MessageTagDTO messageTagDTO) throws DataAccessException {
        return sqlSessionHandler.update(
                MAPPER_CANONICAL_NAME + ".update", messageTagDTO);
    }

    @Override
    public int delete(MessageTagDTO messageTagDTO) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported.");
    }
}
