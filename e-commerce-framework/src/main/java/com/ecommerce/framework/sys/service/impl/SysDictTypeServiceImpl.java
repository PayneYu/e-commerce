package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.support.Convert;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysDictType;
import com.ecommerce.framework.sys.mapper.SysDictDataMapper;
import com.ecommerce.framework.sys.mapper.SysDictTypeMapper;
import com.ecommerce.framework.sys.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictType, SysDictTypeMapper>
    implements ISysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType
     *            字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 批量删除字典类型
     * 
     * @param ids
     *            需要删除的数据
     * @return 结果
     */
    @Override
    public void deleteDictTypeByIds(String ids) throws BusinessException {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            SysDictType dictType = selectById(dictId);
            if (dictDataMapper.selectCountByCriteria(SysDictType.PROPERTY_DICT_TYPE, dictType.getDictType()) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
        }
        this.deleteByIds(dictIds);
    }


    /**
     * 修改保存字典类型信息
     * 
     * @param dictType
     *            字典类型信息
     * @return 结果
     */
    @Override
    public void updateDictType(SysDictType dictType) {
        SysDictType oldDict = selectById(dictType.getId());
        if(dictType.getDictType().equals(oldDict.getDictType())){
            dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        }
        update(dictType);
    }

}
