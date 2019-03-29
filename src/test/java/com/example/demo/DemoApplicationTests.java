package com.example.demo;

import com.example.demo.Entity.Address;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }


    @Test
    public void addUserTest() {
        User u = new User("wupeng");
        u = userRepository.addUser(u);
        System.out.println(u.getName() + " " + u.getId());
    }

    @Test
    public void addAddressTest() {
        Address add = new Address("754");
        add = userRepository.addAddress(add, 2);
        System.out.println(add.getDetail() + " " + add.getId());
    }

    @Test
    public void updateUserTest() {
        User u = userRepository.updateUser(2, "WUPENG2");
        System.out.println(u.getName() + " " + u.getId());
    }

    @Test
    public void updateAddressTest() {
        Address add = userRepository.updateAddress(3, 1);
        System.out.println(add.getDetail() + " " + add.getId());
    }


    @Test
    public void listAddressesTest() {
        List<Address> adds = userRepository.listAddresses(2);
        adds.forEach(add -> {
            System.out.println(add.getDetail() + " " + add.getId());
        });
    }

    @Test
    public void RemoveUserTest() {
        userRepository.remaveUser(3);
    }
}
