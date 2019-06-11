package com.ecommerce.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecommerce.common.config.Global;

/**
 * 获取地址类
 * 
 * @author huizhe yu
 */
public class AddressUtils {
    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";

        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (Global.isAddressEnabled()) {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            // JSONObject obj;
            try {
                // obj = JSON.unmarshal(rspStr, JSONObject.class);
                // JSONObject data = obj.getObj("data");
                // String region = data.getStr("region");
                // String city = data.getStr("city");
                address = "陕西 西安";
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
