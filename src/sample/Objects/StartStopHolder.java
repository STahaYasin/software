package sample.Objects;

import java.time.Duration;
import java.time.LocalDateTime;

public class StartStopHolder {
    private LocalDateTime start;
    private LocalDateTime stop;

    public StartStopHolder(){
        start = LocalDateTime.now();
    }

    public boolean isRunning(){
        return stop == null;
    }

    public long getTime(){
        LocalDateTime a = null;

        if(stop == null){
            a = LocalDateTime.now();
        }
        else{
            a = stop;
        }

        Duration duration = Duration.between(start, a);
        return duration.getSeconds();
    }

    public void Stop(){
        stop = LocalDateTime.now();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(start.toString());
        sb.append(stop == null? " Running..": (" " + stop.toString()));

        return sb.toString();
    }
}
