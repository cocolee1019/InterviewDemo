package main.java.uitls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


/**
 * http工具类
 *
 * @author ljj
 * 2019/10/10 14:28
 */
public class HttpUtils {

    private static CloseableHttpClient httpclient                       = HttpClients
                                                                            .custom()
                                                                            .setRetryHandler(new RetryHandler())
                                                                            .build();
    private final static Logger logger                                  = LogManager.getLogger("util.HttpUtils");
    /** 默认重试次数 */
    private final static int DEFAULT_RETRY_TIMES                        = 3;
    /** 默认超时时间 */
    private final static int DEFAULT_TIMEOUT_MILLS                      = 30000;
    /** 默认contextType */
    private final static String DEFAULT_CONTENTTYPE                     = "text/plain";
    /** 默认contextType编码 */
    private final static String DEFAULT_CONTENTTYPE_CHARACTERSET        = "UTF-8";

    public static Request generateRequest(String uri) throws URISyntaxException {
        return new Request(uri);
    }

    public static Request generateRequest(String host, Integer port, String path) throws URISyntaxException {
        return new Request(host, port, path);
    }

    public static class Request {

        private URI uri;
        private Integer retryTimes              = DEFAULT_RETRY_TIMES;
        private Integer timeoutMills            = DEFAULT_TIMEOUT_MILLS;
        private Map<String, String> params      = new HashMap<>(1);
        private Map<String, String> headers     = new HashMap<>(1);


        //设置响应字符集
        private String responseCharset          = DEFAULT_CONTENTTYPE_CHARACTERSET;

        // 以下body信息

        private String body;
        private String bodyContentType          = DEFAULT_CONTENTTYPE;
        private String bodyContentTypeCharset   = DEFAULT_CONTENTTYPE_CHARACTERSET;

        private Request(String host, Integer port, String path) throws URISyntaxException {
            String scheme = "http";

            if (host.startsWith("http")) {
                String[] split = host.split("://");
                scheme = split[0];
                host = split[1];
            }

            this.uri = new URIBuilder()
                            .setScheme(scheme)
                            .setHost(host)
                            .setPort(port)
                            .setPath(path)
                            .build();
        }

        private Request(String uri) throws URISyntaxException {
            this.uri = new URI(uri);
        }

        /*public Request setHost(String host) {
            this.host = host;
            return this;
        }

        public Request setPort(Integer port) {
            this.port = port;
            return this;
        }

        public Request setPath(String path) {
            this.path = path;
            return this;
        }*/

        public Request addParam(String name, String value) {
            params.put(name, value);
            return this;
        }

        public Request addHeader(String name, Object value) {
            headers.put(name, value.toString());
            return this;
        }

        public Request setRetryTimes(Integer retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }

        public Request setTimeoutMills(Integer timeoutMills) {
            this.timeoutMills = timeoutMills;
            return this;
        }

        public String getResponseCharset() {
            return responseCharset;
        }

        public Request setResponseCharset(String responseCharset) {
            this.responseCharset = responseCharset;
            return this;
        }

        public Request setBody(String body) {
            this.body = body;
            return this;
        }

        public Request setBody(String body, String contentType) {
            this.body = body;
            this.bodyContentType = contentType;
            return this;
        }

        public String doGet() {
            return HttpUtils.sendGet(uri, params, headers, retryTimes, timeoutMills, responseCharset);
        }

        public String doPost() {
            StringEntity entity = null;
            if (this.body != null) {
                entity = new StringEntity(this.body, ContentType.create(this.bodyContentType, this.bodyContentTypeCharset));
            }
            return HttpUtils.sendPost(uri, entity, params, headers, retryTimes, timeoutMills, responseCharset);
        }
    }

    private static String sendPost(URI uri, StringEntity body, Map<String, String> params, Map<String, String> headers, int retryTimes, int timeoutMills, String responseCharset) {

        param2Uri(params, uri);

        if (uri == null) {
            return null;
        }

        CloseableHttpResponse response = null;
        try {
            //发送post请求
            HttpPost httpPost = new HttpPost(uri);
            RequestConfig config = RequestConfig
                    .custom()
                    .setSocketTimeout(timeoutMills)
                    .setConnectTimeout(timeoutMills)
                    .build();
            httpPost.setConfig(config);
            if (headers != null && !headers.isEmpty()) {
                headers.keySet().forEach(key -> httpPost.addHeader(key, headers.get(key)));
            }
            httpPost.setEntity(body);
            HttpContext context = HttpClientContext.create();
            context.setAttribute("retryTimes", retryTimes);
            response = httpclient.execute(httpPost, context);
        } catch (IOException e) {
            logger.error("发起post请求出现异常", e);
        }

        return response2String(response, uri, responseCharset);
    }


    private static String sendGet(URI uri, Map<String, String> params, Map<String, String> headers, int retryTimes, int timeoutMills, String responseCharset) {

        param2Uri(params, uri);

        if (uri == null) {
            return null;
        }

        CloseableHttpResponse response = null;
        try {
            //发送get请求
            HttpGet httpget = new HttpGet(uri);
            RequestConfig config = RequestConfig
                    .custom()
                    .setSocketTimeout(timeoutMills)
                    .setConnectTimeout(timeoutMills)
                    .build();

            httpget.setConfig(config);
            if (headers != null && !headers.isEmpty()) {
                headers.keySet().forEach(key ->httpget.addHeader(key, headers.get(key)));
            }

            HttpContext context = HttpClientContext.create();
            context.setAttribute("retryTimes", retryTimes);
            response = httpclient.execute(httpget, context);
        } catch (IOException e) {
            logger.error("发起get请求出现异常", e);
            e.printStackTrace();
        }

        return response2String(response, uri, responseCharset);
    }

    private static String response2String(CloseableHttpResponse response, URI uri, String charset) {
        String result = null;

        if (response != null) {
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity, charset);
                } else{
                    logger.error(uri.toString() + "地址请求失败，响应代码为：" + response.getStatusLine().getStatusCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("解析respons出现异常", e);
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    //skip
                }
            }
        }

        return result;
    }

    // ==== 私有工具方法 ====

    /**
     * 封装uri
     */
    private static URI param2Uri(Map<String, String> params, URI uri) {
        try {
            uri = new URIBuilder(uri)
                    .addParameters(param2Paris(params))
                    .build();
        } catch (URISyntaxException e) {
            logger.error("构造uri出现异常", e);
        }
        return uri;
    }

    private static List<NameValuePair> param2Paris(Map<String, String> params) {

        if (params == null || params.isEmpty()) {
            return Collections.emptyList();
        }

        List<NameValuePair> pairs = new ArrayList<>();
        int i = 0;
        for (String key : params.keySet()) {
            NameValuePair pair = new BasicNameValuePair(key, params.get(key));
            pairs.add(pair);
        }

        return pairs;
    }
}