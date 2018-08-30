/*
 * (c)BOC
 */
package net.pis.dao.dti;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DEFAULT TABLE MAPPER
 *
 * @author jh,Seo
 * @param <T>
 */
@Component
public interface DefaultMapper<T> {

    /**
     * Default INSERT statement
     *
     * @param t
     * @return
     */
    int create(T t) throws DataAccessException;

    /**
     * Default SELECT statement
     *
     * @param t
     * @return
     */
    List<T> read(T t) throws DataAccessException;

    /**
     * Default UPDATE statement
     *
     * @param t
     * @return
     */
    int update(T t) throws DataAccessException;

    /**
     * Default DELETE statement
     *
     * @param t
     * @return
     */
    int delete(T t) throws DataAccessException;
}
