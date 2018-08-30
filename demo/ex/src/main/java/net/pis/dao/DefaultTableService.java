/*
 * (c)BOC
 */
package net.pis.dao;

import java.util.List;

/**
 *
 * @author jh,Seo
 * @param <T>
 */
public interface DefaultTableService<T> {

    /**
     * Default INSERT statement
     *
     * @param t
     * @return
     */
    int create(T t);

    /**
     * Default SELECT statement
     *
     * @param t
     * @return
     */
    List<T> read(T t);

    /**
     * Default UPDATE statement
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * Default DELETE statement
     *
     * @param t
     * @return
     */
    int delete(T t);

}
