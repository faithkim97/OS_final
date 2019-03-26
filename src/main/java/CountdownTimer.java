public class CountdownTimer {

    private Thread thread;
    private int timeInterval;
    private int sleepInterval;

    public CountdownTimer(int timeInterval) {
        sleepInterval = 1000;
        thread = new Thread();
        this.timeInterval = timeInterval;
    }

    public CountdownTimer(int timeInterval, int sleepInterval) {
        this.sleepInterval = sleepInterval;
        this.timeInterval = timeInterval;
        thread = new Thread();
    }

    public int getTimeInterval() { return timeInterval; }

    public void setTimeInterval(int timeInterval) { this.timeInterval = timeInterval; }

    public void setSleepInterval(int sleepInterval) { this.sleepInterval = sleepInterval; }

    public void setTimer() {
        try {
            while (timeInterval > 0) {
                System.out.println(timeInterval--);
                thread.sleep(sleepInterval);
            }
        } catch(InterruptedException e) {
            System.out.println("Something went wrong with the timer");
            System.exit(-1);
        }
    }




}
