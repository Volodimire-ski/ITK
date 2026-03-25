package Core;

public class Main {
    public static void main(String[] args) {
        MyStringBuilder myStringBuilder = new MyStringBuilder(new StringBuilder("abcde"));
        System.out.println(myStringBuilder.reverse());
        System.out.println(myStringBuilder.append(5));
        System.out.println(myStringBuilder.append(9));
        System.out.println(myStringBuilder.undo());
        System.out.println(myStringBuilder.undo());
        System.out.println(myStringBuilder.undo());
        System.out.println(myStringBuilder.undo());
        System.out.println(myStringBuilder.undo());
    }
}