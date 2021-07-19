package boot.service.impl.myAnnotation;

import boot.service.impl.MyTest;
import boot.service.impl.RandomValue;
import boot.service.impl.bean.MyClassBean;
import boot.service.impl.bean.MyClassStudentBean;
import boot.service.impl.bean.MyTestBean;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUtil {
    // 通过反射获取多个sql和关联属性个数(暂时设定为两个)
    public static List<String[]> getSqlAndTypes(Class<?>cl1, Class<?>cl2) {
        List<String[]> sqlAndTypes = new ArrayList();
        Class c1 = cl1;
        Class c2 = cl2;
        Field[] fields1 = c1.getDeclaredFields();
        // 属性名称
        String[] typeNames1 = new String[fields1.length];
        // 属性类型
        String[] types1 = new String[fields1.length];
        // 返回的sql和类型
        String[] returnString1 = new String[fields1.length+1];
        // 记录关联属性的个数
        int myRelated1 = 0;
        for (int i = 0; i < fields1.length; i++) {
            if (fields1[i].isAnnotationPresent(MyRelated.class)) {
                myRelated1++;
            }
            String name = fields1[i].toString();
            typeNames1[i] = name.substring(name.lastIndexOf(".")+1);
            String type = fields1[i].getGenericType().toString();
            types1[i] = type.substring(type.lastIndexOf(".")+1);
        }
        StringBuffer sb1 = new StringBuffer("INSERT INTO ").append(typeNames1[0]).append("(");
        for (int i = 1; i < typeNames1.length; i++) {
            if (i+2 > typeNames1.length) {
                sb1.append(typeNames1[i]).append(")VALUES (");
            }else {
                sb1.append(typeNames1[i]).append(",");
            }
        }
        for (int i = 1; i < types1.length; i++) {
            if (i+2 > types1.length) {
                sb1.append("?)");
            }else {
                sb1.append("?,");
            }
            returnString1[i] = types1[i];
        }
        returnString1[0] = sb1.toString();
        returnString1[returnString1.length-1] = String.valueOf(myRelated1);
        sqlAndTypes.add(returnString1);

        // 第二个
        Field[] fields2 = c2.getDeclaredFields();
        String[] typeNames2 = new String[fields2.length];
        String[] types2 = new String[fields2.length];
        String[] returnString2 = new String[fields2.length];
        for (int i = 0; i < fields2.length; i++) {
            String name = fields2[i].toString();
            typeNames2[i] = name.substring(name.lastIndexOf(".")+1);
            String type = fields2[i].getGenericType().toString();
            types2[i] = type.substring(type.lastIndexOf(".")+1);
        }
        StringBuffer sb = new StringBuffer("INSERT INTO ").append(typeNames2[0]).append("(");
        for (int i = 1; i < typeNames2.length; i++) {
            if (i+2 > typeNames2.length) {
                sb.append(typeNames2[i]).append(")VALUES (");
            }else {
                sb.append(typeNames2[i]).append(",");
            }
        }
        for (int i = 1; i < types2.length; i++) {
            if (i+2 > types2.length) {
                sb.append("?)");
            }else {
                sb.append("?,");
            }
            returnString2[i] = types2[i];
        }
        returnString2[0] = sb.toString();
        sqlAndTypes.add(returnString2);
        return sqlAndTypes;
    }
    // 通过反射获取sql和相应的属性类型
    public static String[] getSqlAndType(Class<?>clz) {
        Class c = clz;
        Field[] fields = c.getDeclaredFields();
        String[] typeNames = new String[fields.length];
        String[] types = new String[fields.length];
        String[] returnString = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].toString();
            typeNames[i] = name.substring(name.lastIndexOf(".")+1);
            String type = fields[i].getGenericType().toString();
            types[i] = type.substring(type.lastIndexOf(".")+1);
        }
        StringBuffer sb = new StringBuffer("INSERT INTO ").append(typeNames[0]).append("(");
        for (int i = 1; i < typeNames.length; i++) {
            if (i+2 > typeNames.length) {
                sb.append(typeNames[i]).append(")VALUES (");
            }else {
                sb.append(typeNames[i]).append(",");
            }
        }
        for (int i = 1; i < types.length; i++) {
            if (i+2 > types.length) {
                sb.append("?)");
            }else {
                sb.append("?,");
            }
            returnString[i] = types[i];
        }
        returnString[0] = sb.toString();
        return returnString;
    }
    // 判断类型，进行赋值添加
    public static void prepareStatementSetType(PreparedStatement preparedStatement, String type, int index, long id) throws SQLException {
        switch (type){
            case "Long":
                preparedStatement.setLong(index, id);
                System.out.println(index+ ": "+id);
                break;
            case "String":
                preparedStatement.setString(index, RandomValue.getChineseName());
                System.out.println(index+": "+RandomValue.getChineseName());
                break;
        }
    }

    // 单表插入数据
    public static void insertBigData(Class<?> clz, long begin, long end, int times, String url, String user, String password) {
        //定义连接、statement对象
        Connection conn;
        PreparedStatement pstm;
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            conn.setAutoCommit(false);
            // 获取关联表的相应的值
            String[] sqlAndType = getSqlAndType(clz);
            //编写sql
            String sql = sqlAndType[0];
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();
            //循环10次，每次十万数据，一共100万
            for(int i=0;i<times;i++) {
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //遍历sqlAndType进行赋值
                    for (int i1 = 1; i1 < sqlAndType.length; i1++) {
                        System.out.println(sqlAndType[i1]);
                        prepareStatementSetType(pstm, sqlAndType[i1], i1, begin);
                    }
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
                //提交事务
                conn.commit();
                //边界值自增10W
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时："+(eTime-bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时："+(eTime1-bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
    // 两个关联表插入数据
    public static void insertBigDatas(Class<?> cl1,Class<?> cl2, long begin, long end, int times, String url, String user, String password) {
        //定义连接、statement对象
        Connection conn;
        PreparedStatement pstm;
        PreparedStatement pstm1;
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            conn.setAutoCommit(false);
            // 获取关联表的相应的值
            List<String[]> sqlAndTypes = getSqlAndTypes(cl1, cl2);
            // 获取sql语句和类型
            String[] sqlAndType = sqlAndTypes.get(0);
            for (String s : sqlAndType) {
                System.out.println(s);
            }
            String[] sqlAndType1 = sqlAndTypes.get(1);
            for (String s : sqlAndType1) {
                System.out.println(s);
            }
            // 获取关联属性个数
            int myRelated = Integer.parseInt(sqlAndType[sqlAndType.length - 1]);
            //编写sql
            String sql = sqlAndType[0];
            String sql1 = sqlAndType1[0];
            //预编译sql
            pstm = conn.prepareStatement(sql);
            pstm1 = conn.prepareStatement(sql1);
            //开始总计时
            long bTime1 = System.currentTimeMillis();
            //循环10次，每次十万数据，一共100万
            for (int i = 0; i < times; i++) {
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    // 关联属性
                    for (int i1 = 0; i1 < myRelated; i1++) {
                        switch (sqlAndType[i1 + 1]) {
                            case "Long":
                                pstm.setLong(i1 + 1, begin);
                                pstm1.setLong(i1 + 1, begin);
                                break;
                            case "String":
                                String s = RandomValue.getChineseName();
                                System.out.println(s);
                                pstm.setString(i1 + 1, s);
                                pstm1.setString(i1 + 1, s);
                                break;
                        }
                    }
                    //遍历sqlAndType进行赋值
                    for (int i1 = myRelated+1; i1 < sqlAndType.length - 1; i1++) {
                        System.out.println(sqlAndType[i1]);
                        prepareStatementSetType(pstm, sqlAndType[i1], i1, begin);
                    }
                    for (int i1 = myRelated+1; i1 < sqlAndType1.length; i1++) {
                        System.out.println(sqlAndType1[i1]);
                        prepareStatementSetType(pstm1, sqlAndType1[i1], i1, begin);
                    }
//                    pstm.setLong(1, begin);
//                    pstm.setString(2, RandomValue.getChineseName());
//                    pstm.setString(3, "2012-12-15 12:30:59");
                    //添加到同一个批处理中
                    pstm.addBatch();
                    pstm1.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
                pstm1.executeBatch();
                //提交事务
                conn.commit();
                //边界值自增10W
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时：" + (eTime - bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时：" + (eTime1 - bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
    // 读取txt文件中内容并返回
    public static List<String> readTxt(String filePath) {
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
        return sqlAndTypes;
    }
    // 传入文件路径单表插入数据
    public static void readAndInsert(String filePath, long begin, long end, int times, String url, String user, String password) {
        //定义连接、statement对象
        Connection conn;
        PreparedStatement pstm;
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            conn.setAutoCommit(false);
            // 获取关联表的相应的值
            List<String> list = readTxt(filePath);
            String[] strings = new String[list.size()];
            String[] sqlAndType = list.toArray(strings);
            //编写sql
            String sql = sqlAndType[0];
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();
            //循环10次，每次十万数据，一共100万
            for(int i=0;i<times;i++) {
                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                while (begin < end) {
                    //遍历sqlAndType进行赋值
                    for (int i1 = 1; i1 < sqlAndType.length; i1++) {
                        System.out.println(sqlAndType[i1]);
                        prepareStatementSetType(pstm, sqlAndType[i1], i1, begin);
                    }
                    //添加到同一个批处理中
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
                //提交事务
                conn.commit();
                //边界值自增10W
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时："+(eTime-bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时："+(eTime1-bTime1));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
