package boot.service.impl.bean;

import boot.service.impl.myAnnotation.MyRelated;

public class MyClassBean {
    private String my_class;
    @MyRelated
    private Long id;
    @MyRelated
    private String name;
    private String date;
    private String room;

    public MyClassBean() {
    }
}
