package boot.service.impl;

import boot.service.impl.bean.MyClassBean;
import boot.service.impl.bean.MyClassStudentBean;
import boot.service.impl.bean.MyTestBean;
import boot.service.impl.myAnnotation.MySqlUtil;

import java.util.List;

public class MySqlTest {
    private long begin = 1;//起始id
    private long end = begin+100000;//每次循环插入的数据量
    private String url = "jdbc:mysql://localhost:3306/lzumanagement?serverTimezone=UTC&useSSL=false&useAffectedRows=true";
    private String user = "root";
    private String password = "159258jb";
    private String filePath = "D:\\idea学习项目\\lzu-springboot\\src\\test\\java\\boot\\service\\impl\\properties\\test.txt";


    @org.junit.Test
    public void insertBigData() {
        // MySqlUtil.insertBigData(MyTestBean.class,begin, end,1, url, user, password);
        // MySqlUtil.insertBigDatas(MyClassBean.class, MyClassStudentBean.class,begin, end,1, url, user, password);
       MySqlUtil.readAndInsert(filePath, begin, end, 2, url, user, password);
    }

}
