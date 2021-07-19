package boot.vo.admin;

public class StatisticsDataVO<T> {

    private T value;
    private String name;

    @Override
    public String toString() {
        return "StatisticsDataVO{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public StatisticsDataVO() {
    }

    public StatisticsDataVO(String name, T value) {
        this.name = name;
        this.value = value;
    }
}
