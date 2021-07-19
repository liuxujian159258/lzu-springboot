package boot.service.impl.bean;

import boot.service.impl.myAnnotation.MyRelated;

public class MyClassStudentBean {
    private String my_class_student;
    @MyRelated
    private Long classId;
    @MyRelated
    private String className;
    private String studentName;

    public MyClassStudentBean() {
    }
}
