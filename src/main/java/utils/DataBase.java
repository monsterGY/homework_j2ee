package utils;

import second.Student;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/02  18:05
 */
public class DataBase {
    public static List<Student> database;

    public static List<Student> get(){
        database = new ArrayList<Student>();
        String path = getRealPath();
        try {
            FileInputStream fis = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while((line=br.readLine())!=null) {
                Student tempPerson = new Student();
                // 将一行组装成一个Person
                String[] info = line.split(" ");
                for(int i=0;i<info.length;i++) {
                    if(i==0) {
                        tempPerson.setId(info[i]);
                    }else if(i==1) {
                        tempPerson.setName(info[i]);
                    }else if(i==2) {
                        tempPerson.setGender(info[i]);
                    }else if(i==3) {
                        tempPerson.setGrade(info[i]);
                    }else if(i==4) {
                        tempPerson.setMobile(info[i]);
                    }else if(i==5){
                        tempPerson.setEmail(info[i]);
                    }
                }
                database.add(tempPerson);
            }
            System.out.println("总共多少人："+database.size());
            br.close();
            return database;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getRealPath() {
        String path = Thread.currentThread().
                getContextClassLoader().getResource("").toString();
        path=path.replace('/', '\\'); // 将/换成\
        path=path.replace("file:", ""); //去掉file:
        path=path.replace("classes\\", ""); //去掉class\
        path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...
        path+="contacts\\database.txt";
        System.out.println(path);
        return path;
    }
}
