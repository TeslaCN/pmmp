package ltd.scau.dto;

public class ResultDto {

    protected int code = 0;

    protected String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
