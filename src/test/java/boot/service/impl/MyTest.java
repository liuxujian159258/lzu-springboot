package boot.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyTest {
    public static void main(String[] args) throws IOException {
        List<Map<String, Object>> list1 = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("competitionName", "实践技能提升竞赛AI挑战赛");
        map1.put("number", 88);
        map2.put("competitionName", "实践技能提升竞赛AI挑战赛1");
        map2.put("number", 99);
        list1.add(map1);
        list1.add(map2);
        List<Map<String, Object>> list2 = new ArrayList<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        map3.put("competitionName", "实践技能提升竞赛AI挑战赛");
        map3.put("number", 12);
        map4.put("competitionName", "实践技能提升竞赛AI挑战赛1");
        map4.put("number", 1);
        list2.add(map3);
        list2.add(map4);
        Map<String, Integer> map5 = new HashMap();
        list1.stream().collect(Collectors.groupingBy(it -> it.get("competitionName"))).forEach((k,v)->{
            map5.put((String) k,v.stream().mapToInt(e->(int)e.get("number")).sum());
        });
        System.out.println(map5);
        Map<String, Integer> map6 = new HashMap();
        list2.stream().collect(Collectors.groupingBy(it -> it.get("competitionName"))).forEach((k,v)->{
            map6.put((String) k,v.stream().mapToInt(e->(int)e.get("number")).sum());
        });
        System.out.println(map6);
        map5.forEach((k,v)->map6.merge(k,v, Integer::sum));
        System.out.println(map6);
        Map<String, Integer>  map7 = new HashMap<>();
        test();
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File("src/main/resources/static/image/").getAbsolutePath());
        Resource resource = new ClassPathResource("");
        System.out.println(resource.getFile().getAbsolutePath());
        System.out.println(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/image/"));
    }
    static void test(){
        Map<String,Integer> map1= new HashMap<String, Integer>();
        map1.put("one",1);
        map1.put("two",2);
        map1.put("three",3);
        Map<String,Integer> map2= new HashMap<String,Integer>();
        map2.put("one",1);
        map2.put("two",2);
        //map1合并到map2中
        map1.forEach((key,value) -> map2.merge(key,value,Integer::sum));
        System.out.println(JSON.toJSONString(map2));

    }
}
