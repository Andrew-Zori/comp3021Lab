package test;
public class casting {
    public static void main(String[] arg) {
        Sub a = new Sub();
        Sup b = a;
        b.shout();
        System.out.println(b instanceof Sub);
    }
}
