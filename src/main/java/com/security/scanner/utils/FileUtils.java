package com.security.scanner.utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    
    public static List<String> readTextFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void saveLogToFile(String content, String fileName) {
        if (content == null || content.trim().isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String logFileName = now.format(formatter) + "_" + fileName + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName, true))) {
            // 添加时间戳分隔符，区分不同次扫描的记录
            writer.println("\n" + "==================================================");
            writer.println("扫描时间: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("==================================================");
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
