package com.study.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 按照给出的数据，找出同时满足以下条件的用户，也即以下条件全部满足
 *      偶数ID且年龄大于24且用户名转为大写且用户名字母倒序排序
 *      只输出一个用户的名字
 */

@Data
@AllArgsConstructor
class User {
    private Integer id;
    private String name;
    private Integer age;
}

public class StreamDemo {

    public static void main(String[] args) {

        User u1 = new User(11, "a", 23);
        User u2 = new User(11, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", null);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            try {
                list.stream().map(user -> {
                    return Optional.ofNullable(user.getAge()).orElseThrow(NullPointerException::new);
                }).forEach(System.out::println);
            } catch (NullPointerException e) {
                return;
            }
        }

        Map<Integer, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getId));
        System.out.println(collect);

        list.stream().filter(user -> {
            return user.getId() % 2 == 0;
        }).filter(user -> {
            return user.getAge() > 24;
        }).map(user -> {
            return user.getName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);


        //========================================================================

        /*Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        Function<String, Integer> function = s -> {return s.length();};
        System.out.println(function.apply("abc"));

        Predicate<String> predicate = s -> {return s.isEmpty();};
        System.out.println(predicate.test("123"));

        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("hql");

        Supplier<String> supplier = () -> {return "java";};
        System.out.println(supplier.get());
         */
    }
}
