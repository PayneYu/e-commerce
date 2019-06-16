package com.ecommerce.web.system.controller;

import com.ecommerce.common.annotation.Log;
import com.ecommerce.common.base.AjaxResult;
import com.ecommerce.common.enums.BusinessType;
import com.ecommerce.common.page.TableDataInfo;
import com.ecommerce.common.utils.poi.ExcelUtil;
import com.ecommerce.framework.base.controller.BaseController;
import com.ecommerce.framework.sys.entity.SysOrg;
import com.ecommerce.framework.sys.service.ISysOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 机构 信息操作处理
 * 
 * @author huizhe yu
 * @date 2019-06-15
 */
@Controller
@RequestMapping("/system/org")
public class SysOrgController extends BaseController {

    private String prefix = "system/org";
	
	@Autowired
	private ISysOrgService sysOrgService;
	
	@RequiresPermissions("sys:sysOrg:view")
	@GetMapping
	public String sysOrg() {
	    return prefix + "/sysOrg";
	}
	
	/**
	 * 查询机构列表
	 */
	@RequiresPermissions("sys:sysOrg:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysOrg sysOrg) {
		startPage();
        List<SysOrg> list = sysOrgService.selectSysOrgList(sysOrg);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出机构列表
	 */
	@RequiresPermissions("sys:sysOrg:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysOrg sysOrg) {
    	List<SysOrg> list = sysOrgService.selectSysOrgList(sysOrg);
        ExcelUtil<SysOrg> util = new ExcelUtil<SysOrg>(SysOrg.class);
        return util.exportExcel(list, "sysOrg");
    }
	
	/**
	 * 新增机构
	 */
	@GetMapping("/add")
	public String add() {
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存机构
	 */
	@RequiresPermissions("sys:sysOrg:add")
	@Log(title = "机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SysOrg sysOrg) {
		sysOrg.setId(Long.valueOf(sysOrg.getOrgCode()));
		sysOrgService.insert(sysOrg);
		return success();
	}

	/**
	 * 修改机构
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("sysOrg", sysOrgService.selectById(id));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存机构
	 */
	@RequiresPermissions("sys:sysOrg:edit")
	@Log(title = "机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SysOrg sysOrg) {
		return toAjax(sysOrgService.update(sysOrg));
	}
	
	/**
	 * 删除机构
	 */
	@RequiresPermissions("sys:sysOrg:remove")
	@Log(title = "机构", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		sysOrgService.deleteByIds(ids);
        return success();
	}

	/**
	 * 加载区划列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(){
		List<Map<String, Object>> tree = sysOrgService.selectOrgTree(new SysOrg());
		return tree;
	}

	/**
	 * 选择部门树
	 */
	@GetMapping("/selectOrgTree/{orgId}")
    public String selectDeptTree(@PathVariable("orgId") Long orgId, ModelMap mmap) {
        mmap.put("org", sysOrgService.selectById(orgId));
        return prefix + "/tree";
    }
	
}
