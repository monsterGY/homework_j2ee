package second;

import java.util.Objects;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/09/19  9:18
 */
import java.io.Serializable;

public class Student implements Serializable,Comparable<Student>{
    private String id;
    private String name;
    private String gender;
    private String grade;
    private String mobile;
    private String email;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(grade, student.grade) &&
                Objects.equals(mobile, student.mobile) &&
                Objects.equals(email, student.email);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", grade='" + grade + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, grade, mobile, email);
    }

    public Student(String id, String name, String gender, String grade, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.mobile = mobile;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public Student() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Student o) {
        if(o!=null) {
            int i = (this.id).compareTo(o.getId());
            if(i>0) {
                return 1;
            }else if(i<0){
                return -1;
            }else {
                return 0;
            }
        }
        return 0;
    }
}
