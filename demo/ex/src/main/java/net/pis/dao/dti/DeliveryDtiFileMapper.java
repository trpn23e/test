/**
 * (c)BOC
 */
package net.pis.dao.dti;

import net.pis.dto.table.DeliveryDtiFileDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * Created by achiz on 14. 7. 14.
 */
@Component
public class DeliveryDtiFileMapper implements DefaultMapper<DeliveryDtiFileDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Override
    public int create(DeliveryDtiFileDTO t) {
        return sqlSessionHandler.insert(
            MAPPER_CANONICAL_NAME + ".create", t);
    }

    @Override
    public List<DeliveryDtiFileDTO> read(DeliveryDtiFileDTO t) {
        return sqlSessionHandler.selectList(
            MAPPER_CANONICAL_NAME + ".read", t);
    }

    @Override
    public int update(DeliveryDtiFileDTO t) {
        return sqlSessionHandler.update(
            MAPPER_CANONICAL_NAME + ".update", t);
    }

    @Override
    public int delete(DeliveryDtiFileDTO t) {
        return sqlSessionHandler.delete(
            MAPPER_CANONICAL_NAME + ".delete", t);
    }

}
