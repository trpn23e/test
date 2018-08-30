/*
 * (c)BOC
 */
package net.pis.dao.dti;

import net.pis.dto.table.DTIItemDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Component
public class DTIItemMapper implements DefaultMapper<DTIItemDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;


    @Override
    public int create(DTIItemDTO t) {
        return sqlSessionHandler.insert(
            MAPPER_CANONICAL_NAME + ".create", t);
    }

    @Override
    public List<DTIItemDTO> read(DTIItemDTO t) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".read", t);
    }

    @Override
    public int update(DTIItemDTO t) {
        return sqlSessionHandler.update(
            MAPPER_CANONICAL_NAME + ".update", t);
    }

    @Override
    public int delete(DTIItemDTO t) {
        return sqlSessionHandler.delete(
            MAPPER_CANONICAL_NAME + ".delete", t);
    }

}
