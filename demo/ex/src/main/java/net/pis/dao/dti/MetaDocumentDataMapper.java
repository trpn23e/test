/*
 * (c)BOC
 */
package net.pis.dao.dti;


import net.pis.dto.MetaDocumentDataDTO;
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
// public interface MetaDocumentDataMapper extends DefaultMapper<MetaDocumentDataDTO> {
@Component
public class MetaDocumentDataMapper implements DefaultMapper<MetaDocumentDataDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Override
    public int create(MetaDocumentDataDTO metaDocumentDataDTO) throws DataAccessException {
        return sqlSessionHandler.insert(
                MAPPER_CANONICAL_NAME + ".create", metaDocumentDataDTO);
    }

    @Override
    public List<MetaDocumentDataDTO> read(MetaDocumentDataDTO metaDocumentDataDTO) throws DataAccessException {
        return sqlSessionHandler.selectList(
                MAPPER_CANONICAL_NAME + ".read", metaDocumentDataDTO);
    }

    @Override
    public int update(MetaDocumentDataDTO metaDocumentDataDTO) throws DataAccessException {
        return sqlSessionHandler.update(
                MAPPER_CANONICAL_NAME + ".update", metaDocumentDataDTO);
    }

    @Override
    public int delete(MetaDocumentDataDTO metaDocumentDataDTO) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported.");
    }
}
