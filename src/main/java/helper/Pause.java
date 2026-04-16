package helper;

public class Pause {
    public static void pauseMS(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("InterruptedException : %s%n", e);
        }
    }
}
