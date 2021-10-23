package Model;

import java.time.DayOfWeek;

public class WorkSchedule {
    private DayOfWeek startDay;
    private DayOfWeek endDay;
    private int startHour;
    private int endHour;

    public WorkSchedule(DayOfWeek startDay, DayOfWeek endDay, int startHour, int endHour){
        return;
    }

    public String toString(){
        return "";
    }

    public DayOfWeek getStartDay() {
        return this.startDay;
    }

    public void setStartDay(DayOfWeek startDay) {
        this.startDay = startDay;
    }

    public DayOfWeek getEndDay() {
        return this.endDay;
    }

    public void setEndDay(DayOfWeek endDay) {
        this.endDay = endDay;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }


}
