package org.example.learning.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jonydom
 * @description 实现浅拷贝，待拷贝对象中的引用对象（拷贝地址，不拷贝内容）
 * @date 2024-09-05 11:57
 */
public class testShallowCopy {
    public static void main(String[] args) {
        Person person1 = new Person(new Address("武汉"));
        Person person1Copy = person1.clone();
        // true
        System.out.println(person1.getAddress() == person1Copy.getAddress());
    }

    @Data
    @AllArgsConstructor
    static class Address implements Cloneable {
        private String name;
        @Override
        public Address clone() {
            try {
                return (Address) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
    @Data
    @AllArgsConstructor
    static class Person implements Cloneable {
        private Address address;

        // 省略构造函数、Getter&Setter方法
        @Override
        public Person clone() {
            try {
                Person person = (Person) super.clone();
                return person;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
