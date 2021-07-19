package boot.service.impl.myAnnotation;

import boot.service.impl.bean.MyClassBean;
import boot.service.impl.bean.MyClassStudentBean;
import boot.service.impl.bean.MyTestBean;

import java.util.List;

public class MyClass {
    private String myClass;
    private Integer id;
    private String name;
    private String date;

    public static void main(String[] args) {
        for (String s : MySqlUtil.getSqlAndType(MyTestBean.class)) {
            System.out.println(s);
        }
        List<String[]> sqlAndTypes = MySqlUtil.getSqlAndTypes(MyClassBean.class, MyClassStudentBean.class);
        for (String s : sqlAndTypes.get(0)) {
            System.out.println(s);
        }
        for (String s : sqlAndTypes.get(1)) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "myClass='" + myClass + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MyClass() {
    }

    public MyClass(String myClass, Integer id, String name, String date) {
        this.myClass = myClass;
        this.id = id;
        this.name = name;
        this.date = date;
    }
}
