/**
 * (c)BOC
 */
package net.pis.dao.dti;


import net.pis.dto.DocumentDataDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by achiz on 14. 7. 14.
 */
// public interface DocumentDataMapper extends DefaultMapper<DocumentDataDTO> {
@Component
public class DocumentDataMapper implements DefaultMapper<DocumentDataDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Override
    public int create(DocumentDataDTO documentDataDTO) throws DataAccessException {
        return sqlSessionHandler.insert(
                MAPPER_CANONICAL_NAME + ".create", documentDataDTO);
    }

    @Override
    public List<DocumentDataDTO> read(DocumentDataDTO documentDataDTO) throws DataAccessException {
        return sqlSessionHandler.selectList(
                MAPPER_CANONICAL_NAME + ".read", documentDataDTO);

    }

    @Override
    public int update(DocumentDataDTO documentDataDTO) throws DataAccessException {
        return sqlSessionHandler.update(
                MAPPER_CANONICAL_NAME + ".update", documentDataDTO);
    }

    @Override
    public int delete(DocumentDataDTO documentDataDTO) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported.");
    }
}
