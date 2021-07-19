package boot.vo;

public class ResultVO<T> {
    private T data;
    private MetaVO meta;

    @Override
    public String toString() {
        return "ResultVO{" +
                "data=" + data +
                ", meta=" + meta +
                '}';
    }

    public ResultVO() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MetaVO getMeta() {
        return meta;
    }

    public void setMeta(MetaVO meta) {
        this.meta = meta;
    }

    public ResultVO(T data, MetaVO meta) {
        this.data = data;
        this.meta = meta;
    }
}
