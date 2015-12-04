import java.util.LinkedList;
import java.util.List;

public class Controller {
    private List<Event> eventList= new LinkedList<>();
    public void addEvent(Event e){
        this.eventList.add(e);
    }
    public void run(){
        while(eventList.size() >0)
            for(Event e : new LinkedList<Event>(eventList))
                if(e.ready()){
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
    }
}

