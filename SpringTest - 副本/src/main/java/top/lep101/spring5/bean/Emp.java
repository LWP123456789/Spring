package top.lep101.spring5.bean;

//员工类
public class Emp {
    private String ename;
    private String gender;

    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void add() {
        System.out.println(ename+"::"+gender+"::"+dept);
    }
}
