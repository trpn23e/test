/**
 * (c)BOC
 */
package net.pis.dao.dti;

import net.pis.dto.table.DTIInterfaceDTO;
import net.pis.message.MessageMetaInfo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by achiz on 14. 7. 14.
 */
@Repository
public class DTIInterfaceMapper implements DefaultMapper<DTIInterfaceDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Deprecated
    public List<DTIInterfaceDTO> select(MessageMetaInfo messageMetaInfo) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".select", messageMetaInfo);
    }

    @Override
    public int create(DTIInterfaceDTO t) {
        return sqlSessionHandler.insert(
            MAPPER_CANONICAL_NAME + ".insert", t);
    }

    @Override
    public List<DTIInterfaceDTO> read(DTIInterfaceDTO t) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".read", t);
    }

    public List<DTIInterfaceDTO> read(DTIInterfaceDTO t, Integer limit) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".read", t, new RowBounds(0, limit));
    }

    @Override
    public int update(DTIInterfaceDTO t) {

        return sqlSessionHandler.update(
            MAPPER_CANONICAL_NAME + ".update", t);

    }

    @Override
    public int delete(DTIInterfaceDTO t) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
