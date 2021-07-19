package boot.service.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Test {
    private Long id;
    private String name;
    private String time;

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }

    public Test() {
    }

    public Test(Long id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static void main(String[] args) {
        Class c = new Test().getClass();
        Field[] fields = c.getDeclaredFields();
        List fieldsType = new LinkedList();
        for (Field field : fields) {
            System.out.println(field);
            System.out.println(field.getGenericType());
        }
    }
}
