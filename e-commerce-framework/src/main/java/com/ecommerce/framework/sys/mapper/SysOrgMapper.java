package com.ecommerce.framework.sys.mapper;

import java.util.List;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysOrg;

/**
 * 机构 数据层
 * 
 * @author huizhe yu
 * @date 2019-06-15
 */
public interface SysOrgMapper extends BaseMapper<SysOrg> {

	/**
     * 查询机构列表
     * 
     * @param sysOrg 机构信息
     * @return 机构集合
     */
	List<SysOrg> selectSysOrgList(SysOrg sysOrg);
	

}