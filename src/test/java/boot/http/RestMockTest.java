package boot.http;

import boot.http.bean.LoginTokenBean;
import boot.http.bean.StudentInfo;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RestMockTest {

    @Resource
    private RestMock restMock;

    @Test
    void sendPost() {
        Map<String, Object> jsonMap = new HashMap<>(6);
        jsonMap.put("app_os", 2);
        jsonMap.put("name", "xjliu17");
        jsonMap.put("pwd", "199809");
        String sendPost = restMock.sendPost(jsonMap);
        LoginTokenBean loginTokenBean = JSONObject.parseObject(sendPost, LoginTokenBean.class);
        String sendGet = restMock.sendGet(loginTokenBean.getData().getLogin_token());
        StudentInfo studentInfo = JSONObject.parseObject(sendGet, StudentInfo.class);
        System.out.println(studentInfo.getData());
    }

    @Test
    void sendGet() {
    }
}
