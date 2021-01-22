package juc;

interface Foo {
//    public void sayHello();

    public int add(int x, int y);

    default int div(int x, int y) {
        return x/y;
    }

    public static int mu(int x, int y) {
        return x * y;
    }
}

/**
 * Lamda Express
 * 1.口诀：拷贝小括号，写死右箭头，落地大括号
 *
 * 2.@FunctionalInterface
 *
 * 3.default
 *
 * 4.静态方法实现
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
        /*Foo foo = () -> {
            System.out.println("*****hello*****");
        };
        foo.sayHello();
        */

        Foo foo = (x, y) -> {return x + y;};
        System.out.println(foo.add(1, 2));

        System.out.println(foo.div(10, 5));

        System.out.println(Foo.mu(3, 5));
    }
}
