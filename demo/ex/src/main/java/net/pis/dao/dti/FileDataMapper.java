/**
 * (c)BOC
 */
package net.pis.dao.dti;


import net.pis.dto.FileDataDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by achiz on 14. 7. 14.
 */
//public interface FileDataMapper extends DefaultMapper<FileDataDTO> {
@Component
public class FileDataMapper implements DefaultMapper<FileDataDTO> {

    private final String MAPPER_CANONICAL_NAME = this.getClass().getName();

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSession sqlSessionHandler;

    @Override
    public int create(FileDataDTO fileDataDTO) throws DataAccessException {
        return sqlSessionHandler.insert(
                MAPPER_CANONICAL_NAME + ".create", fileDataDTO);
    }

    @Override
    public List<FileDataDTO> read(FileDataDTO fileDataDTO) throws DataAccessException {
        return sqlSessionHandler.selectList(
                MAPPER_CANONICAL_NAME + ".read", fileDataDTO);
    }

    @Override
    public int update(FileDataDTO fileDataDTO) throws DataAccessException {
        return sqlSessionHandler.update(
                MAPPER_CANONICAL_NAME + ".update", fileDataDTO);
    }

    @Override
    public int delete(FileDataDTO fileDataDTO) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported.");
    }
}
