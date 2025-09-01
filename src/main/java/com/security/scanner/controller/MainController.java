package com.security.scanner.controller;

import com.security.scanner.service.VulnerabilityScannerService;
import com.security.scanner.service.ProxyConfigService;
import com.security.scanner.utils.FileUtils;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainController {
    
    @FXML private ComboBox<String> comboBox;
    @FXML private ComboBox<String> comboBox1;
    @FXML private ComboBox<String> comboBox3;
    @FXML private ComboBox<String> comboBoxshell;
    @FXML private ComboBox<String> comboBoxdgfd;
    @FXML private ComboBox<String> comboBoxlm;
    @FXML private ComboBox<String> comboBoxdaili;
    @FXML private TextField test1;
    @FXML private TextArea test2;
    @FXML private TextArea test3;
    @FXML private TextField cmd1;
    @FXML private TextArea testss;
    @FXML private TextField urldf;
    @FXML private TextField testdff;
    @FXML private TextField testdffd;
    @FXML private TextArea testdfdf;
    @FXML private TextField testjggj;
    @FXML private TextField dsffsf;
    @FXML private TextArea tedasfa;
    @FXML private TextArea testfdg;
    @FXML private TextField daili;
    @FXML private TextField dailid;
    @FXML private TextArea dailisda;
    @FXML private TextField yonghu;
    @FXML private TextField mima;
    @FXML private TextField testdfffds;
    @FXML private TextField zidingyi;
    @FXML private TextArea zidingyishuchu;
    
    private volatile boolean isRunning = true;
    private VulnerabilityScannerService scannerService;
    private ProxyConfigService proxyService;
    
    @FXML
    public void initialize() {
        scannerService = new VulnerabilityScannerService();
        proxyService = new ProxyConfigService();
    }
    
    @FXML
    void jeecgzhuru(ActionEvent event) {
        String selectedItem = comboBoxlm.getValue();
        if ("all".equals(selectedItem)) {
            showAlert("拼写检查", "请选择模块！", AlertType.ERROR);
        } else if ("哥斯拉".equals(selectedItem)) {
            scannerService.jeecgzhuruds(test1.getText(), testfdg);
        } else if ("冰蝎".equals(selectedItem)) {
            scannerService.jeecgzhudb(test1.getText(), testfdg);
        }
    }

    @FXML
    void peizhi(ActionEvent event) {
        String textContent = zidingyi.getText();
        if (textContent.isEmpty()) {
            showAlert("检查", "未输入", AlertType.INFORMATION);
        } else {
            zidingyishuchu.appendText("\n已配置header:" + zidingyi.getText());
        }
    }

    @FXML
    void guanyu(ActionEvent event) {
        showAlert("关于软件", "没事写的工具，喜欢的话就给个star,谢谢", AlertType.INFORMATION);
    }

    @FXML
    void dailiusds(ActionEvent event) {
        proxyService.clearProxy();
        dailisda.appendText("https代理取消成功\n");
    }

    @FXML
    void dailipeuz(ActionEvent event) {
        if (daili.getText().isEmpty()) {
            showAlert("拼写检查", "输入为空", AlertType.ERROR);
            return;
        }
        
        String selectedItem = comboBoxdaili.getValue();
        if ("all".equals(selectedItem)) {
            showAlert("拼写检查", "请选择模块！", AlertType.ERROR);
            return;
        }
        
        proxyService.configureProxy(selectedItem, daili.getText(), dailid.getText(), 
                                   yonghu.getText(), mima.getText(), dailisda);
    }

    @FXML
    void jeecgxian(ActionEvent event) {
        isRunning = false;
    }

    @FXML
    void banben(ActionEvent event) {
        // 由于MainController不再继承Application，这里需要其他方式打开URL
        // 可以使用Desktop.getDesktop().browse()或者Platform.openURI()
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/MInggongK/jeecg-"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void jeecgss(ActionEvent event) {
        isRunning = true;
    }

    @FXML
    void jeecggetshells(ActionEvent event) {
        String selectedItem = comboBoxshell.getValue();
        if ("all".equals(selectedItem)) {
            showAlert("拼写检查", "请选择模块！", AlertType.ERROR);
        } else if ("jeecg-boot commonController 任意文件上传漏洞".equals(selectedItem)) {
            scannerService.jeecggskkl(test1.getText(), tedasfa);
        } else if ("jeecg-boot jmreport任意文件上传漏洞poc1".equals(selectedItem)) {
            scannerService.jeecggetshelldf(test1.getText(), tedasfa);
        } else if ("jeecg-boot jmreport任意文件上传漏洞poc2".equals(selectedItem)) {
            scannerService.jeecgshelldf1(test1.getText(), tedasfa);
        }
    }

    @FXML
    void rce(ActionEvent event) {
        String selectedItem = comboBox.getValue();
        if ("选择模块".equals(selectedItem)) {
            showAlert("拼写检查", "请选择模块！", AlertType.ERROR);
            return;
        }

        if ("all".equals(selectedItem)) {
            runAllVulnerabilityScans();
        } else {
            runSpecificVulnerabilityScan(selectedItem);
        }
    }

    @FXML
    void jeecgjiekou(ActionEvent event) {
        String selectedItem = comboBoxdgfd.getValue();
        if ("all".equals(selectedItem)) {
            new Thread(() -> {
                scannerService.jeecgqueryudfsdf(test1.getText(), testdffd.getText(), test2);
                scannerService.jeecgdsfsfs(test1.getText(), testdffd.getText(), test2);
                scannerService.zidingyi(test1.getText(), test2);
                scannerService.zidingyi1(test1.getText(), test2);
                scannerService.zidingyi3(test1.getText(), test2);
                scannerService.zidingyi4(test1.getText(), test2);
                scannerService.zidingyi5(test1.getText(), test2);
            }).start();
        } else if ("jeecg-boot-querySysUser信息泄露漏洞".equals(selectedItem)) {
            scannerService.jeecgqueryudfsdf(test1.getText(), testdffd.getText(), test2);
        } else if ("jeecg-boot-checkOnlyUser信息泄露漏洞".equals(selectedItem)) {
            scannerService.jeecgdsfsfs(test1.getText(), testdffd.getText(), test2);
        }
    }

    @FXML
    void rce1(ActionEvent event) {
        test2.clear();
        testss.clear();
        tedasfa.clear();
    }

    @FXML
    void rce5(ActionEvent event) {
        String selectedItem = comboBox3.getValue();
        if ("All".equals(selectedItem)) {
            showAlert("拼写检查", "请选择模块！", AlertType.ERROR);
        } else if ("jeecg-boot queryFieldBySql远程命令执行漏洞poc1".equals(selectedItem)) {
            scannerService.yz(test1.getText(), cmd1.getText(), testss);
        } else if ("jeecg-boot queryFieldBySql远程命令执行漏洞poc2".equals(selectedItem)) {
            scannerService.yz1(test1.getText(), cmd1.getText(), testss);
        } else if ("jeecg-boot testConnection远程命令执行漏洞poc1".equals(selectedItem)) {
            scannerService.jeecgcmd(test1.getText(), cmd1.getText(), testss);
        } else if ("jeecg-boot testConnection远程命令执行漏洞poc2".equals(selectedItem)) {
            scannerService.jeecgcmd1(test1.getText(), cmd1.getText(), testss);
        } else if ("JeecgBoot jmreport/loadTableData SSTI模板注入漏洞poc1".equals(selectedItem)) {
            scannerService.jeecgssicmd(test1.getText(), cmd1.getText(), testss);
        } else if ("JeecgBoot jmreport/loadTableData SSTI模板注入漏洞poc2".equals(selectedItem)) {
            scannerService.jeecgsscmd1(test1.getText(), cmd1.getText(), testss);
        }
    }

    @FXML
    void rce2(ActionEvent event) {
        String selectedItem = comboBox1.getValue();
        if ("All".equals(selectedItem)) {
            showAlert("拼写检查", "请选择模块！", AlertType.ERROR);
            return;
        }
        
        runBatchScan(selectedItem);
    }

    private void runAllVulnerabilityScans() {
        new Thread(() -> {
            test2.clear();
            scannerService.zhiwen(test1.getText(), test2);
            scannerService.jeecgrce(test1.getText(), test2);
            scannerService.jeecgrce1(test1.getText(), test2);
            scannerService.jeecgtest(test1.getText(), test2);
            scannerService.jeecgtest1(test1.getText(), test2);
            scannerService.jeecgrcedfs(test1.getText(), urldf.getText(), test2);
            scannerService.jeecgdfs1(test1.getText(), urldf.getText(), test2);
            scannerService.jeecgdsd(test1.getText(), test2);
            scannerService.jeecgdsd1(test1.getText(), test2);
            scannerService.jeecggfg(test1.getText(), test2);
            scannerService.jeecggfg1(test1.getText(), test2);
            scannerService.jeecgfgdg(test1.getText(), test2);
            scannerService.jeecgfg1(test1.getText(), test2);
            scannerService.jeecggetshell(test1.getText(), tedasfa);
            scannerService.jeecgipload(test1.getText(), tedasfa);
            scannerService.jeecgload1(test1.getText(), tedasfa);
            scannerService.jeecguser(test1.getText(), test2);
            scannerService.jeecguser1(test1.getText(), test2);
            scannerService.jeecgusergfhf(test1.getText(), test2);
            scannerService.jeecggfh1(test1.getText(), test2);
            scannerService.jeecghttp(test1.getText(), test2);
            scannerService.jeecghttp1(test1.getText(), test2);
            scannerService.jeecgxiazai(test1.getText(), test2);
            scannerService.jeecgdsf(test1.getText(), test2);
            scannerService.jeecgapi(test1.getText(), test2);
            scannerService.jeecgapi1(test1.getText(), test2);
            scannerService.jeecgp3(test1.getText(), test2);
            scannerService.jeecgsys(test1.getText(), test2);
            scannerService.jeecgsys1(test1.getText(), test2);
            scannerService.jeecgbiaoda(test1.getText(), test2);
            scannerService.jeecgbiaoda1(test1.getText(), test2);
            scannerService.jeecgsfddg(test1.getText(), test2);
            scannerService.jeecgshow(test1.getText(), test2);
            
            Platform.runLater(this::saveLogToFile);
        }).start();
    }

    private void runSpecificVulnerabilityScan(String selectedItem) {
        switch (selectedItem) {
            case "jeecg-boot queryFieldBySql远程命令执行漏洞":
                scannerService.jeecgrce(test1.getText(), test2);
                scannerService.jeecgrce1(test1.getText(), test2);
                break;
            case "jeecg-boot testConnection远程命令执行漏洞":
                scannerService.jeecgtest(test1.getText(), test2);
                scannerService.jeecgtest1(test1.getText(), test2);
                break;
            case "JeecgBoot jmreport/loadTableData SSTI模板注入漏洞":
                scannerService.jeecgrcedfs(test1.getText(), urldf.getText(), test2);
                scannerService.jeecgdfs1(test1.getText(), urldf.getText(), test2);
                break;
            case "jeecg-boot-queryTableData-sqli注入漏洞":
                scannerService.jeecgdsd(test1.getText(), test2);
                scannerService.jeecgdsd1(test1.getText(), test2);
                break;
            case "jeecg-boot-getDictItemsByTable-sqli注入漏洞":
                scannerService.jeecggfg(test1.getText(), test2);
                scannerService.jeecggfg1(test1.getText(), test2);
                break;
            case "Jeecg-Boot qurestSql-SQL注入漏洞":
                scannerService.jeecgfgdg(test1.getText(), test2);
                scannerService.jeecgfg1(test1.getText(), test2);
                break;
            case "jeecg-boot commonController 任意文件上传漏洞":
                scannerService.jeecggetshell(test1.getText(), tedasfa);
                break;
            case "jeecg-boot-querySysUser信息泄露漏洞":
                scannerService.jeecguser(test1.getText(), test2);
                scannerService.jeecguser1(test1.getText(), test2);
                break;
            case "jeecg-boot-checkOnlyUser信息泄露漏洞":
                scannerService.jeecgusergfhf(test1.getText(), test2);
                scannerService.jeecggfh1(test1.getText(), test2);
                break;
            case "jeecg-boot-httptrace信息泄露漏洞":
                scannerService.jeecghttp(test1.getText(), test2);
                scannerService.jeecghttp1(test1.getText(), test2);
                break;
            case "jeecg-boot jmreport任意文件上传漏洞":
                scannerService.jeecgipload(test1.getText(), tedasfa);
                scannerService.jeecgload1(test1.getText(), tedasfa);
                break;
            case "jeecg-boot-任意文件下载漏洞":
                scannerService.jeecgxiazai(test1.getText(), test2);
                break;
            case "jeecg-boot-jeecgFormDemoController漏洞":
                scannerService.jeecgdsf(test1.getText(), test2);
                break;
            case "jeecg-boot-v2 api接口信息泄露":
                scannerService.jeecgapi(test1.getText(), test2);
                scannerService.jeecgapi1(test1.getText(), test2);
                break;
            case "jeecg-boot-v2 P3 Biz Chat任意文件读取漏洞":
                scannerService.jeecgp3(test1.getText(), test2);
                break;
            case "jeecg-boot-v2 sys/duplicate/check注入漏洞":
                scannerService.jeecgsys(test1.getText(), test2);
                scannerService.jeecgsys1(test1.getText(), test2);
                break;
            case "jeecg-boot-v2 AviatorScript表达式注入漏洞":
                scannerService.jeecgbiaoda(test1.getText(), test2);
                scannerService.jeecgbiaoda1(test1.getText(), test2);
                break;
            case "jeecg-boot getTotalData注入漏洞":
                scannerService.jeecgsfddg(test1.getText(), test2);
                break;
            case "jeecg-boot jmreport/show SQL注入漏洞":
                scannerService.jeecgshow(test1.getText(), test2);
                break;
        }
    }

    private void runBatchScan(String selectedItem) {
        String scanType = "";
        switch (selectedItem) {
            case "jeecg-boot testConnection远程命令执行漏洞":
                scanType = "jeecg-boot testConnection远程命令执行漏洞";
                break;
            case "jeecg-boot queryFieldBySql远程命令执行漏洞":
                scanType = "jeecg-boot queryFieldBySql远程命令执行漏洞";
                break;
            case "JeecgBoot jmreport/loadTableData SSTI模板注入漏洞":
                scanType = "JeecgBoot jmreport/loadTableData SSTI模板注入漏洞";
                break;
            case "jeecg-boot-queryTableData-sqli注入漏洞":
                scanType = "jeecg-boot-queryTableData-sqli注入漏洞";
                break;
            case "jeecg-boot-getDictItemsByTable-sqli注入漏洞":
                scanType = "jeecg-boot-getDictItemsByTable-sqli注入漏洞";
                break;
            case "Jeecg-Boot qurestSql-SQL注入漏洞":
                scanType = "Jeecg-Boot qurestSql-SQL注入漏洞";
                break;
            case "jeecg-boot jmreport任意文件上传漏洞":
                scanType = "jeecg-boot jmreport任意文件上传漏洞";
                break;
            case "jeecg-boot getTotalData注入漏洞":
                scanType = "jeecg-boot getTotalData注入漏洞";
                break;
            case "jeecg-boot jmreport/show SQL注入漏洞":
                scanType = "jeecg-boot jmreport/show SQL注入漏洞";
                break;
            case "批量测试":
                scanType = "geoserver ms任意文件上传漏洞";
                break;
        }
        
        test3.appendText("正在开始批量检查,已选择" + scanType + "\n");
        
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                List<String> urls = FileUtils.readTextFile("urls.txt");
                for (String line : urls) {
                    if (!isRunning) {
                        break;
                    }
                    
                    switch (selectedItem) {
                        case "jeecg-boot testConnection远程命令执行漏洞":
                            scannerService.jeecgpl(line, test3);
                            break;
                        case "jeecg-boot queryFieldBySql远程命令执行漏洞":
                            scannerService.pl(line, test3);
                            break;
                        case "JeecgBoot jmreport/loadTableData SSTI模板注入漏洞":
                            scannerService.ssi(line, test3);
                            break;
                        case "jeecg-boot-queryTableData-sqli注入漏洞":
                            scannerService.jeecgquery(line, test3);
                            break;
                        case "jeecg-boot-getDictItemsByTable-sqli注入漏洞":
                            scannerService.jeecgsql(line, test3);
                            break;
                        case "Jeecg-Boot qurestSql-SQL注入漏洞":
                            scannerService.jeecgsq(line, test3);
                            break;
                        case "jeecg-boot jmreport任意文件上传漏洞":
                            scannerService.port(line, test3);
                            break;
                        case "jeecg-boot getTotalData注入漏洞":
                            scannerService.jeecgtotal(line, test3);
                            break;
                        case "jeecg-boot jmreport/show SQL注入漏洞":
                            scannerService.jeecgshowbatch(line, test3);
                            break;
                        case "批量测试":
                            scannerService.psl(line, test3);
                            break;
                    }
                    
                    Thread.sleep(1000L);
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    private void saveLogToFile() {
        String content = test2.getText();
        if (content == null || content.trim().isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = now.format(formatter) + "_检测日志.txt";

        try {
            FileUtils.saveLogToFile(content, fileName);
            test2.appendText("\n日志已追加到文件: " + fileName + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            test2.appendText("\n保存日志文件时出错: " + e.getMessage() + "\n");
        }
    }

    private void showAlert(String title, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
