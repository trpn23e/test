/*
 * (c)BOC
 */
package net.pis.dao.dti;

import net.pis.dto.table.DTIStatusDTO;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 *
 * @author jh,Seo
 */
@Component
public class DTIStatusMapper implements DefaultMapper<DTIStatusDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Override
    public int create(DTIStatusDTO t) {
        return sqlSessionHandler.insert(
            MAPPER_CANONICAL_NAME + ".create", t);
    }

    @Override
    public List<DTIStatusDTO> read(DTIStatusDTO t) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".read", t);
    }

    @Override
    public int update(DTIStatusDTO t) {
        return sqlSessionHandler.update(
            MAPPER_CANONICAL_NAME + ".update", t);
    }

    @Override
    public int delete(DTIStatusDTO t) {
        return sqlSessionHandler.delete(
            MAPPER_CANONICAL_NAME + ".delete", t);
    }

    public List<DTIStatusDTO> readNtsTargets() {

        logger.trace("~~~~~~~ {} ~~~~~~~~", MAPPER_CANONICAL_NAME + ".readNtsTargets");
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".readNtsTargets");
    }

}
