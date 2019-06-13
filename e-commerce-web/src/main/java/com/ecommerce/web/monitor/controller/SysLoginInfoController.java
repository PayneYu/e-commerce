package com.ecommerce.web.monitor.controller;

import com.ecommerce.common.annotation.Log;
import com.ecommerce.common.base.AjaxResult;
import com.ecommerce.common.enums.BusinessType;
import com.ecommerce.common.page.TableDataInfo;
import com.ecommerce.common.utils.poi.ExcelUtil;
import com.ecommerce.framework.base.controller.BaseController;
import com.ecommerce.framework.sys.entity.SysLoginInfo;
import com.ecommerce.framework.sys.service.ISysLoginInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统访问记录
 * 
 * @author huizhe yu
 */
@Controller
@RequestMapping("/monitor/loginInfo")
public class SysLoginInfoController extends BaseController {
    private String prefix = "monitor/loginInfo";

    @Autowired
    private ISysLoginInfoService loginInfoService;

    @RequiresPermissions("monitor:loginInfo:view")
    @GetMapping()
    public String loginInfo() {
        return prefix + "/loginInfo";
    }

    @RequiresPermissions("monitor:loginInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLoginInfo loginInfo) {
        startPage();
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        return getDataTable(list);
    }

    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:loginInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysLoginInfo loginInfo) {
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        ExcelUtil<SysLoginInfo> util = new ExcelUtil<SysLoginInfo>(SysLoginInfo.class);
        return util.exportExcel(list, "登陆日志");
    }

    @RequiresPermissions("monitor:loginInfo:remove")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        loginInfoService.deleteByIds(ids);
        return success();
    }

    @RequiresPermissions("monitor:loginInfo:remove")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        loginInfoService.cleanLoginInfo();
        return success();
    }
}
