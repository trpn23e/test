/**
 * (c)BOC
 */
package net.pis.dao.dti;

import net.pis.dto.table.DTIApproveIdDTO;
import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.message.MessageMetaInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by achiz on 14. 7. 14.
 */
@Repository
public class DTIApproveIdMapper implements DefaultMapper<DTIApproveIdDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    public List<DTIInterfaceDTO> select(MessageMetaInfo messageMetaInfo) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".select", messageMetaInfo);
    }

    @Override
    public int create(DTIApproveIdDTO t) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public List<DTIApproveIdDTO> read(DTIApproveIdDTO t) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".read", t);
    }

    @Override
    public int update(DTIApproveIdDTO t) {
        return sqlSessionHandler.update(
            MAPPER_CANONICAL_NAME + ".update", t);
    }

    @Override
    public int delete(DTIApproveIdDTO t) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
