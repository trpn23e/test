/*
 * (c)BOC
 */
package net.pis.dao.dti;


import net.pis.dto.RoutingInfoDTO;
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
@Component
// public interface RoutingInfoMapper extends DefaultMapper<RoutingInfoDTO> {
public class RoutingInfoMapper implements DefaultMapper<RoutingInfoDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Override
    public int create(RoutingInfoDTO routingInfoDTO) throws DataAccessException {
        return sqlSessionHandler.insert(
                MAPPER_CANONICAL_NAME + ".create", routingInfoDTO);
    }

    @Override
    public List<RoutingInfoDTO> read(RoutingInfoDTO routingInfoDTO) throws DataAccessException {
        return sqlSessionHandler.selectList(
                MAPPER_CANONICAL_NAME + ".read", routingInfoDTO);
    }

    @Override
    public int update(RoutingInfoDTO routingInfoDTO) throws DataAccessException {
        return sqlSessionHandler.update(
                MAPPER_CANONICAL_NAME + ".update", routingInfoDTO);
    }

    @Override
    public int delete(RoutingInfoDTO routingInfoDTO) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported.");
    }
}
