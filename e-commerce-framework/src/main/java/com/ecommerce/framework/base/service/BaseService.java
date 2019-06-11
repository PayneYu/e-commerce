package com.ecommerce.framework.base.service;

import java.util.List;

/**
 * @author: Huizhe Yu
 */
public interface BaseService<T> {

    /**
     * for create entity
     * 
     * @param entity
     * @return
     */
    T insert(T entity);

    /**
     * Batch insert
     * 
     * @param list
     */
    void insertList(List<T> list);

    /**
     * for update entity
     * 
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * for update entity
     * 
     * @param entity
     * @return
     */
    T updateSelective(T entity);

    /**
     * for delete entity
     * 
     * @param id
     */
    int deleteById(Long id);

    /**
     * for delete by ids
     *
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * for delete by ids
     *
     * @param ids
     */
    void deleteByIds(Long[] ids);

    /**
     * for delete by ids
     * 
     * @param property
     * @param propertyValues
     */
    void deleteByProperties(String property, List propertyValues);

    /**
     * for get entity by id
     * 
     * @param id
     * @return
     */
    T selectById(Long id);

    /**
     * findAll
     */
    List<T> selectAll();

    List<T> select(T t);

    List<T> selectByCriteria(Object... pv);

    T selectOneByCriteria(Object... pv);

    int selectCountByCriteria(Object... pv);

    int deleteByCriteria(Object... pv);

    String checkPropertyUnique(Long id, String property, Object value);

    String checkPropertyUnique(String property, Object value);

}
