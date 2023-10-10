package utils;

import second.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/02  18:12
 */
public class FindTool {
    public static final int MAX_RESULT_NUM = 5;   // 定义检索记录最大限制
    public static List<Student> database;

    // 静态加载，导入所有person信息
    static {
        database = DataBase.get();
    }


    public static List<Student> find(String data){
        List<Student> persons = null;
        //System.out.println("data="+data);
        // 遍历database模糊匹配信息
        for(int i=0;i<database.size();i++) {
            Student p = database.get(i);
            if(p.toString().contains(data)) {
                if(persons==null) {
                    persons = new ArrayList<Student>();
                }
                persons.add(p);
            }
        }

        return persons;
    }

}
