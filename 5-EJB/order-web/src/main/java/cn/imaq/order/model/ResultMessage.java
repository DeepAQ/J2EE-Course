package cn.imaq.order.model;

public class ResultMessage<T> {
    private boolean success;

    private T result;

    private String message;

    private ResultMessage(boolean success, T result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public static <T> ResultMessage<T> success(T result) {
        return new ResultMessage<>(true, result, "");
    }

    public static <T> ResultMessage<T> failure(String message) {
        return new ResultMessage<>(false, null, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
