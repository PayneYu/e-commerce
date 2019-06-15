package com.ecommerce.framework.sys.service;

import com.ecommerce.framework.base.service.BaseService;
import com.ecommerce.framework.sys.entity.SysOrg;
import java.util.List;

/**
 * 机构 服务层
 * 
 * @author huizhe yu
 * @date 2019-06-15
 */
public interface ISysOrgService extends BaseService <SysOrg> {
	/**
     * 查询机构列表
     * 
     * @param sysOrg 机构信息
     * @return 机构集合
     */
	public List<SysOrg> selectSysOrgList(SysOrg sysOrg);
}
