package com.ecommerce.quartz.task;

import com.ecommerce.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.framework.sys.service.ISysOperLogService;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-13 22:44
 */
@Component("deleteLogTask")
public class DeleteLogTask {


    @Autowired
    private ISysOperLogService operLogService;

    public void deleteOperLog(String days){
        operLogService.deleteBeforeDays(StringUtils.isNotBlank(days)?Integer.parseInt(days):0);
    }
}
