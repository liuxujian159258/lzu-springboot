package boot.service.impl.properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyPro {
    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        List<String> list = new ArrayList<>();
        List<String> type = new ArrayList<>();
        List<String> sqlAndTypes = new ArrayList<>();
        try {
            String str;
            fis = new FileInputStream("D:\\idea学习项目\\lzu-springboot\\src\\test\\java\\boot\\service\\impl\\properties\\test.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                String[] split = str.split(",");
                for (String s : split) {
                    list.add(s);
                }
            }
            StringBuffer sb = new StringBuffer("INSERT INTO ").append(list.get(0)).append("(");
            for (int i = 1; i < list.size(); i = i+2) {
                if (i+2 == list.size()) {
                    sb.append(list.get(i)).append(")VALUES (");
                }else {
                    sb.append(list.get(i)).append(",");
                }
            }
            for (int i = 2; i < list.size(); i = i+2) {
                type.add(list.get(i));
                if (i+2 > list.size()) {
                    sb.append("?)");
                }else {
                    sb.append("?,");
                }
            }
            sqlAndTypes.add(sb.toString());
            for (String s : type) {
                sqlAndTypes.add(s);
            }
            String[] strings = new String[sqlAndTypes.size()];
            String[] strings1 = sqlAndTypes.toArray(strings);
            System.out.println(sqlAndTypes);
            for (String s : strings1) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
