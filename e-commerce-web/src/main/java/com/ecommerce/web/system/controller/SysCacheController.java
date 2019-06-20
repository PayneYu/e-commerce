package com.ecommerce.web.system.controller;

import com.ecommerce.common.annotation.Log;
import com.ecommerce.common.base.AjaxResult;
import com.ecommerce.common.enums.BusinessType;
import com.ecommerce.common.page.TableDataInfo;
import com.ecommerce.common.support.Convert;
import com.ecommerce.framework.base.controller.BaseController;
import com.ecommerce.framework.cache.component.CacheComponent;
import com.ecommerce.framework.cache.model.CacheBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-19 20:45
 */
@Controller
@RequestMapping("/system/cache")
public class SysCacheController extends BaseController {

    private String prefix = "system/cache";

    @Autowired
    private CacheComponent cacheComponent;

    @RequiresPermissions("system:cache:view")
    @GetMapping
    public String config() {
        return prefix + "/cache";
    }

    @RequiresPermissions("system:cache:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CacheBean searchBean) {
        List<CacheBean> list = cacheComponent.findCacheList(searchBean);
        return getDataTable(list);
    }

    @RequiresPermissions("system:cache:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String cacheName) {
        String[] cacheNames =Convert.toStrArray(cacheName);
        for (String name : cacheNames) {
            cacheComponent.removeCache(name);
        }
        return AjaxResult.success();
    }

}
