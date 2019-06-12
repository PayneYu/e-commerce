package com.ecommerce.framework.base.service.impl;

import com.ecommerce.common.constant.Constants;
import com.ecommerce.common.support.Convert;
import com.ecommerce.framework.base.entity.BaseEntity;
import com.ecommerce.framework.base.entity.CreateEntity;
import com.ecommerce.framework.base.entity.UpdateEntity;
import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.util.ShiroUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 基础数据处理层
 * 
 * @author: Huizhe Yu
 */
public class BaseServiceImpl<T, M extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    protected M mapper;

    /**
     * for insert entity
     * 
     * @param t
     * @return
     */
    @Transactional
    public T insert(T t) {
        if(t instanceof CreateEntity){
            CreateEntity entity = (CreateEntity)t;
            entity.setCreateBy(ShiroUtils.getLoginName());
            entity.setCreateTime(new Date());
        }
        mapper.insert(t);
        return t;
    }

    /**
     * Batch insert
     * 
     * @param list
     */
    @Transactional
    public void insertList(List<T> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            mapper.insertList(list);
        }
    }

    /**
     * for update entity
     * 
     * @param t
     * @return
     */
    @Transactional
    public T update(T t) {
        if(t instanceof UpdateEntity){
            UpdateEntity entity = (UpdateEntity)t;
            entity.setUpdateBy(ShiroUtils.getLoginName());
            entity.setUpdateTime(new Date());
        }
        mapper.updateByPrimaryKey(t);
        return t;
    }

    @Override
    @Transactional
    public T updateSelective(T t) {
        mapper.updateByPrimaryKeySelective(t);
        return t;
    }

    /**
     * for delete entity
     * 
     * @param id
     */
    @Transactional
    public int deleteById(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(String ids) {
        deleteByIds(Convert.toLongArray(ids));
    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        mapper.deleteByIds(Arrays.asList(ids));
    }

    @Override
    @Transactional
    public void deleteByProperties(String property, List propertyValues) {
        mapper.deleteByProperties(property, propertyValues);
    }

    /**
     * for get entity by id
     * 
     * @param id
     * @return
     */
    public T selectById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * findAll
     * 
     * @return
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> selectByCriteria(Object... pv) {
        return mapper.selectByCriteria(pv);
    }

    @Override
    public T selectOneByCriteria(Object... pv) {
        return mapper.selectOneByCriteria(pv);
    }

    @Override
    public int selectCountByCriteria(Object... pv) {
        return mapper.selectCountByCriteria(pv);
    }

    @Override
    public int deleteByCriteria(Object... pv) {
        return mapper.deleteByCriteria(pv);
    }

    @Override
    public String checkPropertyUnique(Long id, String property, Object value) {
        T t = mapper.selectOneByCriteria(property, value);
        if (t != null && t instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity)t;
            if (id == null)
                id = -1L;
            if (!id.equals(baseEntity.getId())) {
                return Constants.PROPERTY_NOT_UNIQUE;
            }
        }
        return Constants.PROPERTY_UNIQUE;
    }

    @Override
    public String checkPropertyUnique(String property, Object value) {
        int count = mapper.selectCountByCriteria(property, value);
        if (count > 0) {
            return Constants.PROPERTY_NOT_UNIQUE;
        }
        return Constants.PROPERTY_UNIQUE;
    }

}
