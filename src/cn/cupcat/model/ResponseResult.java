package cn.cupcat.model;

/**
 * ajax 返回实体
 * @author zxy
 */
public class ResponseResult {

    private int errorCode = 300;
    private String errorMessage = "";
    private Object data = new Object();

    public ResponseResult(int errorCode, String errorMessage, Object data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public ResponseResult(String errorMessage, Object data) {
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public ResponseResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ResponseResult() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
