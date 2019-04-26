import entity.Student;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class StudentManagerTest extends TestCase {
    public StudentManager studentManager = new StudentManager();

    public void testAddStu() {
        System.out.println("添加测试");
        studentManager.addStu(new Student("2016012000","Test", 1, "19980718"));
        System.out.println(studentManager.getStuList().toString());
    }

    public void testDelStu() {
        studentManager.delStu("张航");
        System.out.println("删除测试");
        System.out.println(studentManager.getStuList().toString());
    }

    public void testSelStu() {
        System.out.println("查找测试");
        System.out.println(studentManager.selStu("张航").toString());
    }

    public void testSelALlStu() {
        System.out.println("全查测试");
        System.out.println(studentManager.selALlStu().toString());
    }

    public void testUpdateStu(){
        System.out.println("更新测试");
        studentManager.updateStu(new Student("2016012031","TTTT", 1, "19980718"));
        System.out.println(studentManager.selALlStu().toString());
    }

    public void testPrintList() {
        System.out.println("全部输出测试");
        studentManager.printList(studentManager.getStuList());
        System.out.print(studentManager.getStuList().get(0).getName());
    }
}