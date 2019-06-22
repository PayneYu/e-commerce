package com.ecommerce.common.utils;

import com.ecommerce.common.config.Global;
import com.ecommerce.common.json.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取地址类
 * 
 * @author huizhe yu
 */
@Slf4j
public class AddressUtils {
    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

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
            try {
                IpInfoResponse response = JsonUtils.stringToObj(rspStr,IpInfoResponse.class);
                IpInfo ipInfo = response.getIpInfo();
                if (ipInfo != null) {
                    if(StringUtils.isNotBlank(ipInfo.getArea())){
                        address = ipInfo.getArea() + " ";
                    }
                    address += ipInfo.getCity();
                }
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}

@Getter
@Setter
@ToString
class IpInfoResponse {
    private Integer code;

    private IpInfo ipInfo;
}

@Getter
@Setter
@ToString
class IpInfo {
    private String ip;
    private String country;
    private String area ;
    private String region;
    private String city;
    private String isp;
}

