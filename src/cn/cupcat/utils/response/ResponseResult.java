package cn.cupcat.utils.response;

/**
 * 该类是对ajax请求数据的封装,这里只是对有返回的ajax数据的封装
 *
 * @author zxy
 * @version 1.0
 * @since 2017年11月1日09:18:11
 */
public class ResponseResult {

    private int statusCode = 300; //错误码；300-失败；200-成功
    private String message = "";//返回消息
    private Object data;//返回数据

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
