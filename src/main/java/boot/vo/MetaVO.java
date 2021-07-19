package boot.vo;

public class MetaVO<T> {
    private String msg;
    private T status;

    @Override
    public String toString() {
        return "meta{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getStatus() {
        return status;
    }

    public void setStatus(T status) {
        this.status = status;
    }

    public MetaVO(String msg, T status) {
        this.msg = msg;
        this.status = status;
    }
}
