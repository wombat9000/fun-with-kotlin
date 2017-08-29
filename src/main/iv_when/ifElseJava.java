package iv_when;

public class ifElseJava {

    public void hereWeBranch(int x) {
        if (x == 1) {
            System.out.println(x + " is one");
        } else if (x >= 100) {
            System.out.println(x + " greater than or equal 100");
        } else if (x > 0 && x <= 10) {
            System.out.println(x + " between 1 and 10");
        }
    }
}
