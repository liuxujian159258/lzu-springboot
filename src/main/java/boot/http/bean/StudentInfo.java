package boot.http.bean;

public class StudentInfo {
    private Integer code;
    private String message;
    private StudentData data;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public StudentInfo(Integer code, String message, StudentData data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentData getData() {
        return data;
    }

    public void setData(StudentData data) {
        this.data = data;
    }
}
