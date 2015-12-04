
public class GreenHouseController extends Controller {
    //Light subsystem---------------------------------
    private boolean lightState = false;
    public class LightStateOn extends Event{
        public LightStateOn(long delatTime) {
            super(delatTime);
        }
        @Override
        public void action() {
            lightState=true;
        }
        @Override
        public String toString() {
            return "Light is On!";
        }
    }
    public class LightStateOff extends Event{
        public LightStateOff(long delatTime) {
            super(delatTime);
        }
        @Override
        public void action() {
            lightState=false;
        }
        @Override
        public String toString() {
            return "Light is Off!";
        }
    }
    //Water subsystem----------------------------------
    private boolean waterState = false;
    public class WaterStateOn extends Event{
        public WaterStateOn(long delatTime) {
            super(delatTime);
        }
        @Override
        public void action() {
            waterState=true;
        }

        @Override
        public String toString() {
            return "Water is On!";
        }
    }

    public class WaterStateOff extends Event{
        public WaterStateOff(long delatTime) {
            super(delatTime);
        }
        @Override
        public void action() {
            waterState=false;
        }

        @Override
        public String toString() {
            return "Water is Off!";
        }
    }
    //Thermostat subsystem--------------------------------
    private enum THERMOSTAT_STATE{
        MORNING, DAY, EVENING, NIGHT;
    }
    private THERMOSTAT_STATE thermostatState = THERMOSTAT_STATE.MORNING;
    public class ThermostatStateMorning extends Event{

        public ThermostatStateMorning(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            thermostatState = THERMOSTAT_STATE.MORNING;
        }

        @Override
        public String toString() {
            return "Thermostat State is Morning!";
        }
    }
    public class ThermostatStateDay extends Event{

        public ThermostatStateDay(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            thermostatState = THERMOSTAT_STATE.DAY;
        }

        @Override
        public String toString() {
            return "Thermostat State is Day!";
        }
    }
    public class ThermostatStateEvening extends Event{

        public ThermostatStateEvening(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            thermostatState = THERMOSTAT_STATE.EVENING;
        }

        @Override
        public String toString() {
            return "Thermostat State is Evening!";
        }
    }
    public class ThermostatStateNight extends Event{

        public ThermostatStateNight(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            thermostatState = THERMOSTAT_STATE.NIGHT;
        }

        @Override
        public String toString() {
            return "Thermostat State is Night!";
        }
    }
    //Fan subsystem----------------------------------------
    private enum FAN_STATE{
        OFF, LOW, MEDIUM, HIGH;
    }
    private FAN_STATE fanState = FAN_STATE.OFF;
    public class FanStateOff extends Event{

        public FanStateOff(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            fanState = FAN_STATE.OFF;
        }

        @Override
        public String toString() {
            return "Fan state is off!";
        }
    }
    public class FanStateLow extends Event{

        public FanStateLow(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            fanState = FAN_STATE.LOW;
        }

        @Override
        public String toString() {
            return "Fan state is Low!";
        }
    }
    public class FanStateMedium extends Event{

        public FanStateMedium(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            fanState = FAN_STATE.MEDIUM;
        }

        @Override
        public String toString() {
            return "Fan state is medium!";
        }
    }
    public class FanStateHigh extends Event{

        public FanStateHigh(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            fanState = FAN_STATE.HIGH;
        }

        @Override
        public String toString() {
            return "Fan state is high!";
        }
    }
    //Bell subsystem
    public class Bell extends Event{
        public Bell(long delatTime) {
            super(delatTime);
        }

        @Override
        public void action() {
            System.out.println("Beep!!!");
        }

        @Override
        public String toString() {
            return "Bang!!! Bang!!!";
        }
    }
    //Restart subsystem
    public class Restart extends Event{
        private Event[] eventList;
        public Restart(long delatTime, Event[] eventList) {
            super(delatTime);
            this.eventList=eventList;
            for(Event e : eventList)
                addEvent(e);
        }
        @Override
        public void action() {
            for(Event e : eventList){
                e.start();
                addEvent(e);
            }
        }
        @Override
        public String toString() {
            return "Restarting system!";
        }
    }
    //Terminate subsystem
    public static class Terminate extends Event{
        public Terminate(long delatTime) {
            super(delatTime);
        }
        @Override
        public void action() {
            System.exit(0);
        }
        @Override
        public String toString() {
            return "Terminating";
        }
    }
    //Execution
    public static void main(String... args){
        GreenHouseController gc = new GreenHouseController();
        gc.addEvent(gc.new Bell(100));
        gc.addEvent(gc.new LightStateOn(100));
        gc.addEvent(gc.new LightStateOff(100));
        gc.run();
        Event[] eventList = {
                gc.new ThermostatStateDay(150),
                gc.new LightStateOn(200),
                gc.new LightStateOff(400),
                gc.new WaterStateOn(600),
                gc.new WaterStateOff(800),
                gc.new ThermostatStateEvening(400),
                gc.new ThermostatStateMorning(400),
                gc.new ThermostatStateNight(400),
                gc.new FanStateHigh(1000)
        };
        gc.addEvent(gc.new Restart(2000, eventList));
        gc.addEvent(new Terminate(3000));
        gc.run();
    }
}
