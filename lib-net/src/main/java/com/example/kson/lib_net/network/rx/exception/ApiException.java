package com.example.kson.lib_net.network.rx.exception;

import com.example.kson.lib_net.NetConstants;
import com.facebook.stetho.common.LogUtil;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.io.IOException;
import java.io.NotSerializableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

import static com.example.kson.lib_net.network.rx.exception.ApiException.ERROR.CAST_ERROR;
import static com.example.kson.lib_net.network.rx.exception.ApiException.ERROR.ILLEGAL_STATE_ERROR;
import static com.example.kson.lib_net.network.rx.exception.ApiException.ERROR.NULL_POINTER_EXCEPTION;
import static com.example.kson.lib_net.network.rx.exception.ApiException.ERROR.PARSE_ERROR;
import static com.example.kson.lib_net.network.rx.exception.ApiException.ERROR.SSL_ERROR;
import static com.example.kson.lib_net.network.rx.exception.ApiException.ERROR.TIMEOUT_ERROR;
import static com.example.kson.lib_net.network.rx.exception.ApiException.ERROR.UNKNOWN;

/**
 * <pre>
 * </pre>
 */
public class ApiException extends Exception {
    private int code;
    private String msg = "";

    private ApiException(Throwable throwable, int errorCode) {
        super(throwable);
        this.code = errorCode;
        this.msg = throwable.getMessage();
    }

    public static ApiException handlerException(Throwable throwable) {
        ApiException exception = null;
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            exception = new ApiException(httpException, httpException.code());
            try {
                exception.setMsg(httpException.response().errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
                exception.setMsg(e.getMessage());
            }
        } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException ||
                throwable instanceof ConnectTimeoutException || throwable instanceof UnknownHostException) {
            exception = new ApiException(throwable, TIMEOUT_ERROR);
            exception.setMsg("网络连接超时，请检查您的网络状态，稍后重试！");
        } else if (throwable instanceof NullPointerException) {
            exception = new ApiException(throwable, NULL_POINTER_EXCEPTION);
            exception.setMsg("空指针异常");
        } else if (throwable instanceof SSLHandshakeException) {
            exception = new ApiException(throwable, SSL_ERROR);
            exception.setMsg("证书验证失败");
        } else if (throwable instanceof ClassCastException) {
            exception = new ApiException(throwable, CAST_ERROR);
            exception.setMsg("类型转换错误");
        } else if (throwable instanceof IllegalStateException) {
            exception = new ApiException(throwable, ILLEGAL_STATE_ERROR);
            exception.setMsg(throwable.getMessage());
        } else if (throwable instanceof JsonParseException || throwable instanceof JSONException
                || throwable instanceof JsonSyntaxException || throwable instanceof JsonSerializer
                || throwable instanceof NotSerializableException || throwable instanceof ParseException) {
            exception = new ApiException(throwable, PARSE_ERROR);
            exception.setMsg("解析错误");
        } else if (throwable instanceof ServerException) {
            String errorCode = ((ServerException) throwable).getErrorCode();
            String msg = ((ServerException) throwable).getErrorMsg();
            exception = new ApiException(throwable, Integer.parseInt(errorCode));
            exception.setMsg(msg);
        } else {
            LogUtil.e("throwable====="+throwable);
            exception = new ApiException(throwable, UNKNOWN);
            exception.setMsg("未知错误");
        }
        return exception;
    }

    private static String getErrorMsgByErrorCode(int errorCode) {
        String msg = "";
        switch (errorCode) {
            case NetConstants.EXPIRED_TOKEN:
                msg = "用户在其他设备登录";
                return msg;
            default:
                return "未知错误";
        }
    }

    public int getErrorCode() {
        return code;
    }

    public void setErrorCode(int errorCode) {
        this.code = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "errorCode=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    /**
     * 约定异常
     */
    public static class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1001;
        /**
         * 空指针错误
         */
        public static final int NULL_POINTER_EXCEPTION = 1002;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1003;

        /**
         * 类转换错误
         */
        public static final int CAST_ERROR = 1004;

        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1005;

        /**
         * 非法数据异常
         */
        public static final int ILLEGAL_STATE_ERROR = 1006;
    }

    /**
     * 服务端异常
     */
//    public static class ServerException extends RuntimeException {
//        private int status;
//        private String message;
//
//        public ServerException(int errorCode, String errorMsg) {
//            this.status = errorCode;
//            this.message = errorMsg;
//        }
//
//        public int getErrorCode() {
//            return this.status;
//        }
//
//        public String getErrorMsg() {
//            return this.message;
//        }
//    }
    /**
     * 服务端异常
     */
    public static class ServerException extends RuntimeException {
        private String status;
        private String message;

        public ServerException(String errorCode, String errorMsg) {
            this.status = errorCode;
            this.message = errorMsg;
        }

        public String getErrorCode() {
            return this.status;
        }

        public String getErrorMsg() {
            return this.message;
        }
    }
}
