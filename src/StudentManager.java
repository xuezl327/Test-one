import Utils.DrawMenu;
import entity.Student;

import java.util.*;

public class StudentManager{
    private List<Student> stuList = new ArrayList<Student>();
    private DrawMenu drawMenu = new DrawMenu();

    public StudentManager() {
        addStu(new Student("2016012049","张航", 1,"19971011"));
        addStu(new Student("2016012067","茅梓军", 1, "19970814"));
        addStu(new Student("2016012031","师浩然", 1, "19980718"));
        drawMenu.printTitle();
        drawMenu.printMenu();
    }

    /**
     * 添加一个学生
     * @param student
     * @return
     */
    public boolean addStu(Student student){
        if(student.getId().length()!= 10 && !(student.getBirDate().contains("199")||student.getBirDate().contains("200"))) {
            System.out.println("违法输入:\n" + student.toString());
            return false;
        }

        if(selStu(student.getName()).size() == 0 &&
           selStuByID(student.getId()).size() ==0){
            stuList.add(student);
            Collections.sort(stuList,Collections.reverseOrder(new Comparator<Student>() {
                public int compare(Student o1, Student o2) {
                    return o2.getId().compareTo(o1.getId());
                }
            }));
            return true;
        }else {
            System.out.println("重复添加");
            return false;
        }
    }

    public boolean delStu(String name){
        List<Student> list = selStu(name);
        if(list.size() != 0){
            stuList.remove(list.get(0));
            return true;
        }
        else
            return false;
    }

    public List<Student> selStu(String name){
        List<Student> list = new ArrayList<Student>();
        for (Student s:
                this.stuList) {
            if(s.getName().contains(name)){
                list.add(s);
            }
        }
        return list;
    }

    public List<Student> selStuByID(String id){
        List<Student> list = new ArrayList<Student>();
        for (Student s:
                this.stuList) {
            if(s.getId().contains(id)){
                list.add(s);
            }
        }
        return list;
    }
    public boolean updateStu(Student student){
        delStu(selStuByID(student.getId()).get(0).getName());
        addStu(student);
        return true;
    }

    protected void printList(List<Student> list){
        drawMenu.printList(list);
    }

    public List<Student> selALlStu(){
        return stuList;
    }

    public List<Student> getStuList() {
        return stuList;
    }

    public void App(){
        Scanner input = new Scanner(System.in);
        boolean temp = true;
        while(temp) {
            String arg = input.nextLine();
            String[] args = arg.split(" ");
            try {
                int num = Integer.parseInt(args[0]);
                switch (num) {
                    case 0:
                        drawMenu.printHelp();
                        continue;
                    case 1:
                        if (addStu(new Student(args[1], args[2], args[3].contains("男") ? 1 : 2, args[4])))
                            System.out.println("插入成功");
                        continue;
                    case 2:
                        List<Student> list = selStu(args[1]);
                        if (list.size() == 0)
                            System.out.println("查无此人");
                        else
                            printList(list);
                        continue;
                    case 3:
                        if (delStu(args[1]))
                            System.out.println("删除成功");
                        else
                            System.out.println("删除失败，请确认操作是否正确");
                        continue;
                    case 4:
                        updateStu(new Student(args[1], args[2], args[3].contains("男") ? 1 : 2, args[4]));
                        continue;
                    case 5:
                        printList(this.stuList);
                        drawMenu.printMenu();
                        continue;
                    case 6:
                        temp =  false;
                        continue;
                    default:
                        System.out.println("错误命令请重试");
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("输入命令格式错误，请按照“帮助”中所要求格式输入");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("输入命令格式错误，请按照“帮助”中所要求格式输入");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
