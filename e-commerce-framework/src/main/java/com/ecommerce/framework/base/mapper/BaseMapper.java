package com.ecommerce.framework.base.mapper;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.util.MetaObjectUtil;

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

    default Example createExample(Object[] pv) {
        Example example = createExample();
        Example.Criteria criteria = example.createCriteria();
        constructAndCriteria(example.getEntityClass(), pv, criteria);
        return example;
    }

    default Example createExample() {
        Class entityClass = (Class<
            ?>)((ParameterizedType)((Class)(this.getClass().getGenericInterfaces()[0])).getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
        Example example = new Example(entityClass);
        return example;
    }

    default void constructAndCriteria(Class entityClass, Object[] pv, Criteria criteria) {
        if (pv.length == 1) {
            MetaObject metaObject = MetaObjectUtil.forObject(pv[0]);
            String[] properties = metaObject.getGetterNames();

            if (properties == null || properties.length == 0) {
                return;
            }
            EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
            Map<String, EntityColumn> propertyMap = entityTable.getPropertyMap();
            for (String p : properties) {
                if (propertyMap.get(p) != null) {
                    Object value = metaObject.getValue(p);
                    if (value != null) {
                        if (value instanceof java.util.Collection) {
                            criteria.andIn(p, (Iterable)value);
                        } else {
                            criteria.andEqualTo(p, value);
                        }
                    }
                }
            }
            return;
        }
        for (int i = 0, n = pv.length; i < n; i += 2) {
            if (pv[i] != null && pv[i + 1] != null) {
                if (pv[i + 1] instanceof java.util.Collection) {
                    criteria.andIn(pv[i] + "", (Iterable)pv[i + 1]);
                } else {
                    criteria.andEqualTo(pv[i] + "", pv[i + 1]);
                }
            }
        }
        return;
    }

    default List<T> selectByCriteria(Object... pv) {
        Example example = createExample(pv);
        return selectByExample(example);
    }

    default int selectCountByCriteria(Object... pv) {
        Example example = createExample(pv);
        return selectCountByExample(example);
    }

    default T selectOneByCriteria(Object... pv) {
        Example example = createExample(pv);
        return selectOneByExample(example);
    }

    default int updateByCriteriaSelective(T t, Object... pv) {
        Example example = createExample(pv);
        return updateByExampleSelective(t, example);
    }

    default int deleteByCriteria(Object... pv) {
        Example example = createExample(pv);
        return deleteByExample(example);
    }

    default int deleteByProperties(String property, List propertyValues) {
        Example example = createExample();
        example.createCriteria().andIn(property, propertyValues);
        return deleteByExample(example);
    }

    default int deleteByIds(List ids) {
        Example example = createExample();
        example.createCriteria().andIn("id", ids);
        return deleteByExample(example);
    }

}
