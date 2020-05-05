package main.java.uitls;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * 异常时，重试
 * @author ljj
 * 2019/10/10 0010 16:26
 */
public class RetryHandler implements HttpRequestRetryHandler {

    @Override
    public boolean retryRequest(IOException exception
                                , int executionCount
                                , HttpContext context) {

        Integer retryTimes = (Integer) context.getAttribute("retryTimes");
        if (retryTimes == null) {
            return false;
        }

        if (executionCount >= retryTimes) {
            // Do not retry if over max retry count
            return false;
        }

        if (exception instanceof InterruptedIOException) {
            // Timeout
            return false;
        }

        if (exception instanceof UnknownHostException) {
            // Unknown host
            return false;
        }

        if (exception instanceof SSLException) {
            // SSL handshake exception
            return false;
        }

        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
        // Retry if the request is considered idempotent
        return idempotent;
    }
}
