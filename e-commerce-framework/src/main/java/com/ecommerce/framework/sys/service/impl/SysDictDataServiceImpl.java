package com.ecommerce.framework.sys.service.impl;

import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysDictData;
import com.ecommerce.framework.sys.mapper.SysDictDataMapper;
import com.ecommerce.framework.sys.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典 业务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictData, SysDictDataMapper>
    implements ISysDictDataService {

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return sysDictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType
     *            字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return this.selectByCriteria(SysDictData.PROPERTY_DICT_TYPE, dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType
     *            字典类型
     * @param dictValue
     *            字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return sysDictDataMapper.selectDictLabel(dictType, dictValue);
    }

}
