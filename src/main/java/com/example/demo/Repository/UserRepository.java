package com.example.demo.Repository;

import com.example.demo.Entity.Address;
import com.example.demo.Entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    /**
     * 添加用户，并返回包括数据库时间戳的用户对象
     * @param user
     * @return
     */
    public User addUser(User user) {
        em.persist(user);
        em.refresh(user);
        return user;
    }

    /**
     * 添加地址，并指定地址对应的用户
     * @param address
     * @param uid
     * @return
     */
    public Address addAddress(Address address, int uid) {
        User u = em.find(User.class, uid);
        address.setUser(u);
        em.persist(address);
        em.refresh(address);
        return address;
    }

    /**
     * 更新指定ID用户的姓名
     * @param uid
     * @param newName
     * @return
     */
    // me
    public User updateUser(int uid, String newName) {
        User u = new User();
        u.setId(uid);
        User u2 = em.merge(u);
        em.refresh(u2);
        u2.setName(newName);
        return u2;
    }

    /**
     * 尝试使用merge()，以及find()2种方法分别实现
     * 更新指定地址为指定用户
     * @param aid
     * @param uid
     * @return
     */
    public Address updateAddress(int aid, int uid) {
        // 1
        User u = new User(); // 新对象
        u.setId(uid); // 模拟脱管
        User user2 = em.merge(u); // 从数据库中拿到只有Id的受管对象
        em.refresh(user2); // 从数据库再拿一次

        Address add = new Address();
        add.setId(aid);
        Address address2 = em.merge(add);
        em.refresh(address2);
        System.out.println(address2.getDetail() + " " + address2.getId());

        address2.setUser(user2);
        return address2;
        // 2
        /*
        User u = em.find(User.class, uid);
        Address add = em.find(Address.class, aid);
        add.setUser(u);
        return add;
        */
    }

    /**
     * 返回指定用户的全部地址，没有返回空集合，而非null
     * @param uid
     * @return
     */
    public List<Address> listAddresses(int uid) {
        User u = em.find(User.class, uid);
        List<Address> list = u.getAddresses();
        List.of(list);
        return list;
    }

    public void removeAddress(int aid) {
        Address add = em.find(Address.class, aid);
        em.remove(add);
    }

    /**
     * 删除用户，设置级联操作或手动删除相关地址
     * @param uid
     */
    public void remaveUser(int uid) {
        User u = em.find(User.class, uid);
        em.remove(u);
    }
}

