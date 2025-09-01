# Jeecg Exploit Scanner

Jeecg Boot安全漏洞扫描工具，用于检测Jeecg Boot框架中的各种安全漏洞。

## 项目结构

```
jeecg-exploit-scanner/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── security/
│       │           └── scanner/
│       │               ├── Main.java                    # 主启动类
│       │               ├── controller/
│       │               │   └── MainController.java      # 主控制器类
│       │               ├── service/
│       │               │   ├── ProxyConfigService.java # 代理配置服务
│       │               │   └── VulnerabilityScannerService.java # 漏洞扫描服务
│       │               ├── utils/                       # 工具类
│       │               │   ├── FileUtils.java          # 文件操作工具
│       │               │   └── NetworkUtils.java       # 网络请求工具
│       │               └── vulnerabilities/             # 漏洞检测模块
│       │                   ├── FileUploadVulnerabilities.java    # 文件上传漏洞
│       │                   ├── InfoDisclosureVulnerabilities.java # 信息泄露漏洞
│       │                   ├── MemshellVulnerabilities.java      # 内存马漏洞
│       │                   ├── RceVulnerabilities.java          # 远程命令执行漏洞
│       │                   └── SqlInjectionVulnerabilities.java # SQL注入漏洞
│       └── resources/
│           └── f.fxml                                  # JavaFX界面布局文件
├── target/                                             # 编译输出目录
│   ├── classes/                                        # 编译后的类文件
│   ├── jeecg-exploit-scanner-4.3.0.jar                # 打包后的JAR文件
│   └── generated-sources/                              # 生成的源代码
├── pom.xml                                            # Maven配置文件
├── dependency-reduced-pom.xml                         # 精简的POM文件
├── jeecg-exploit-scanner.iml                          # IntelliJ IDEA项目文件
└── README.md                                          # 项目说明文档
```

## 功能特性

### 支持的漏洞检测

1. **远程命令执行漏洞**
   - jeecg-boot queryFieldBySql远程命令执行漏洞
   - jeecg-boot testConnection远程命令执行漏洞
   - JeecgBoot jmreport/loadTableData SSTI模板注入漏洞

2. **SQL注入漏洞**
   - jeecg-boot-queryTableData-sqli注入漏洞
   - jeecg-boot-getDictItemsByTable-sqli注入漏洞
   - Jeecg-Boot qurestSql-SQL注入漏洞

3. **文件上传漏洞**
   - jeecg-boot commonController 任意文件上传漏洞
   - jeecg-boot jmreport任意文件上传漏洞

4. **信息泄露漏洞**
   - jeecg-boot-querySysUser信息泄露漏洞
   - jeecg-boot-checkOnlyUser信息泄露漏洞
   - jeecg-boot-httptrace信息泄露漏洞
   - jeecg-boot-v2 api接口信息泄露

5. **其他漏洞**
   - jeecg-boot-任意文件下载漏洞
   - jeecg-boot-v2 P3 Biz Chat任意文件读取漏洞
   - jeecg-boot-v2 sys/duplicate/check注入漏洞
   - jeecg-boot-v2 AviatorScript表达式注入漏洞

### 主要功能

- **单目标检测**: 对单个目标进行漏洞检测
- **批量检测**: 支持从文件读取URL列表进行批量检测
- **命令执行**: 支持远程命令执行功能
- **文件上传**: 支持文件上传漏洞利用
- **代理配置**: 支持HTTP/HTTPS代理配置
- **自定义Header**: 支持自定义HTTP请求头
- **日志记录**: 自动保存检测日志到文件

## 环境要求

- JDK 1.8 或更高版本
- Maven 3.6 或更高版本

## 编译和运行

### 使用Maven编译

```bash
# 编译项目
mvn clean compile

# 打包项目
mvn clean package

# 运行项目
mvn exec:java -Dexec.mainClass="com.security.scanner.Main"
```

### 使用IDE运行

1. 在IntelliJ IDEA中打开项目
2. 确保Maven依赖已正确下载
3. 运行`Main.java`类

### 直接运行JAR文件

```bash
# 运行打包后的JAR文件
java -jar target/jeecg-exploit-scanner-4.3.0.jar
```

## 使用说明

1. **启动程序**: 运行Main类启动图形界面
2. **输入目标**: 在"目标"输入框中输入要检测的URL
3. **选择模块**: 从下拉菜单中选择要检测的漏洞类型
4. **开始检测**: 点击"检测"按钮开始漏洞检测
5. **查看结果**: 在"检测日志"标签页中查看检测结果

### 批量检测

1. 在项目根目录创建`urls.txt`文件
2. 每行输入一个要检测的URL
3. 选择"批量检查"标签页
4. 选择要检测的漏洞类型
5. 点击"开始检查"按钮

### 代理配置

1. 选择"代理配置"标签页
2. 选择代理类型（HTTP/HTTPS/Socket）
3. 输入代理服务器IP、端口、用户名和密码
4. 点击"设置代理"按钮

## 注意事项

⚠️ **重要提醒**:
- 本工具仅用于安全研究和授权测试
- 请勿用于非法用途
- 使用前请确保获得目标系统的授权
- 作者不承担因使用本工具而产生的任何法律责任

## 技术栈

- **Java**: 主要编程语言
- **JavaFX**: 图形用户界面框架
- **Maven**: 项目构建和依赖管理
- **OkHttp**: HTTP客户端库
- **JSON-lib**: JSON处理库
- **Apache Commons**: 通用工具库

## 更新日志

### v4.3.0
- 重构项目结构，采用标准Maven项目布局
- 修复依赖路径问题
- 优化代码组织结构
- 添加企业级包结构
- 修复JavaFX兼容性问题

## 许可证

本项目仅供学习和研究使用，请遵守相关法律法规。

## 联系方式

如有问题或建议，请通过GitHub Issues联系。









