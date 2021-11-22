package top.lep101.spring5.dao;

public class UserDaoImpl implements UserDao {

    private String name = "lwp";

    @Override
    public void update() {
        System.out.println("dao update......");
    }

    public void test() {
        System.out.println(name);
    }
}
