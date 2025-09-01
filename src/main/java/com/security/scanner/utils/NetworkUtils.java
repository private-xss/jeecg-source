package com.security.scanner.utils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import okhttp3.OkHttpClient;

public class NetworkUtils {

    public static final X509TrustManager IGNORE_SSL_TRUST_MANAGER_X509 = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    };

    public static SSLContext getIgnoreInitedSslContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[] { IGNORE_SSL_TRUST_MANAGER_X509 }, new SecureRandom());
        return sslContext;
    }

    public static HostnameVerifier getIgnoreSslHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };
    }

    public static void configureSSL() {
        try {
            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    return true;
                }
            };
            TrustManager[] trustAllCerts = new TrustManager[]{IGNORE_SSL_TRUST_MANAGER_X509};
            SSLContext sc = SSLContext.getInstance("SSL");
            SSLSessionContext sslsc = sc.getServerSessionContext();
            sslsc.setSessionTimeout(0);
            sc.init(null, trustAllCerts, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sendPostRequest(String urlString, String jsonData, String userAgent) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", userAgent);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
        OutputStream os = connection.getOutputStream();
        os.write(input, 0, input.length);
        os.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }

    public static String sendGetRequest(String urlString, String userAgent) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", userAgent);

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }

    public static boolean isValidUrl(String url) {
        String urlRegex = "^(http|https)://[^\\s]*$";
        return url.matches(urlRegex);
    }

    public static String normalizeUrl(String url) {
        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }

    public OkHttpClient createOkHttpClient() {
        try {
            return new OkHttpClient.Builder()
                    .sslSocketFactory(getIgnoreInitedSslContext().getSocketFactory(), IGNORE_SSL_TRUST_MANAGER_X509)
                    .hostnameVerifier(getIgnoreSslHostnameVerifier())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            // 如果SSL配置失败，返回默认的OkHttpClient
            return new OkHttpClient.Builder().build();
        }
    }
}
