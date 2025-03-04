package com.study.stream;

import com.study.thread3.juc.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Field;
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
@NoArgsConstructor
class User {
    private Integer id;
    private String name;
    private Integer age;
}

public class StreamDemo {

    public static void main(String[] args) throws NoSuchFieldException {

        User u1 = new User(11, "a", 22);
        User u2 = new User(13, "b", 24);
        User u3 = new User(12, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(13, "e", 23);


        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        LinkedHashMap<Integer, Long> collect = list.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.groupingBy(User::getId, LinkedHashMap::new, Collectors.counting()));
        System.out.println(collect);
        //list.stream()
        //        .sorted(Comparator.comparing(User::getAge))
        //        .collect(Collectors.groupingBy(User::getId, LinkedHashMap::new, Collectors.toCollection(LinkedList::new)))
        //        .values().stream().map(LinkedList::getFirst).collect(Collectors.toList()).forEach(item -> {
        //            System.out.println(item);
        //        });

        list.stream().sorted(Comparator.comparing(User::getId, Comparator.reverseOrder()).thenComparing(User::getAge, Comparator.reverseOrder())).forEach(user -> {
            System.out.println(user);
        });

        System.out.println("=========================================");

        list.stream().sorted(Comparator.comparing(User::getId).thenComparing(User::getAge).reversed()).forEach(user -> {
            System.out.println(user);
        });

        //Map<Integer, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getId));
        //System.out.println(collect);
        //
        //list.stream().filter(user -> {
        //    return user.getId() % 2 == 0;
        //}).filter(user -> {
        //    return user.getAge() > 24;
        //}).map(user -> {
        //    return user.getName().toUpperCase();
        //}).sorted((o1, o2) -> {
        //    return o2.compareTo(o1);
        //}).limit(1).forEach(System.out::println);


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
