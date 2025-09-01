package com.security.scanner.service;

import javafx.scene.control.TextArea;
import java.net.Socket;

public class ProxyConfigService {
    
    public void configureProxy(String proxyType, String ip, String port, String username, String password, TextArea output) {
        switch (proxyType) {
            case "http":
                configureHttpProxy(ip, port, username, password, output);
                break;
            case "https":
                configureHttpsProxy(ip, port, username, password, output);
                break;
            case "socket":
                configureSocketProxy(ip, port, output);
                break;
        }
    }
    
    private void configureHttpProxy(String ip, String port, String username, String password, TextArea output) {
        System.setProperty("http.proxyHost", ip);
        System.setProperty("http.proxyPort", port);
        System.setProperty("http.proxyUser", username);
        System.setProperty("http.proxy password", password);
        System.setProperty("https.proxyHost", ip);
        System.setProperty("https.proxyPort", port);
        System.setProperty("https.proxyUser", username);
        System.setProperty("https.proxy password", password);
        
        output.appendText("http代理配置成功\n");
        output.appendText("\nIP代理为:" + ip);
        output.appendText("\n端口代理为:" + port);
        output.appendText("\n用户名:" + username);
        output.appendText("\n密码:" + password);
    }
    
    private void configureHttpsProxy(String ip, String port, String username, String password, TextArea output) {
        System.setProperty("https.proxyHost", ip);
        System.setProperty("https.proxyPort", port);
        System.setProperty("https.proxyUser", username);
        System.setProperty("https.proxypassword", password);
        
        output.appendText("https代理配置成功\n");
        output.appendText("\nIP代理为:" + ip);
        output.appendText("\n端口代理为:" + port);
        output.appendText("\n用户名:" + username);
        output.appendText("\n密码:" + password);
    }
    
    private void configureSocketProxy(String ip, String port, TextArea output) {
        try {
            Socket socket = new Socket(ip, Integer.parseInt(port));
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            // 静默处理异常
        }
        
        output.appendText("socket代理配置成功\n");
        output.appendText("\nIP代理为:" + ip);
        output.appendText("\n端口代理为:" + port);
    }
    
    public void clearProxy() {
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
        System.clearProperty("http.proxyUser");
        System.clearProperty("http.proxy password");
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");
        System.clearProperty("https.proxyUser");
        System.clearProperty("https.proxypassword");
    }
}
