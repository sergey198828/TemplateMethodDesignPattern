public abstract class Event {
    private long eventTime;
    protected final long delayTime;
    public Event(long delatTime){
        this.delayTime=delatTime;
        this.start();
    }

    public void start() {
        this.eventTime=System.nanoTime() + this.delayTime;
    }

    public boolean ready(){
        return System.nanoTime()>=eventTime;
    }
    public abstract void action();
    @Override
    public abstract String toString();
}
