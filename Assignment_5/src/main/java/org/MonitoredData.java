package org;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class MonitoredData {

    private Date start_time;
    private Date end_time;
    private String activity_label;

    public MonitoredData(Date start_time, Date end_time, String activity_label) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.activity_label = activity_label;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public String getActivity_label() {
        return activity_label;
    }

    public Integer getStartDay() {
        SimpleDateFormat dateFormatDay = new SimpleDateFormat("dd");
        return Integer.parseInt(dateFormatDay.format(start_time));
    }

    public Integer getEndDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        return Integer.parseInt(dateFormat.format(end_time));
    }

    public Integer activityDuration() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        String startDate = dateFormat.format(start_time);
        String[] fields = startDate.split(":");
        LocalTime startTime = LocalTime.of(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));

        String endDate = dateFormat.format(end_time);
        fields = endDate.split(":");
        LocalTime endTime = LocalTime.of(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));

        if(getStartDay().equals(getEndDay())){ // activitatea se incepe si se termina in aceeasi zi
            long duration = Duration.between(startTime, endTime).getSeconds();
            return  Integer.parseInt(duration+""); // duration in secunde
        } else {
            LocalTime midnight = LocalTime.of(23,59,59);
            long untilMidnight = Duration.between(startTime, midnight).getSeconds();
            long afterMidnight = Integer.parseInt(fields[0]) * 3600 + Integer.parseInt(fields[1]) * 60 + Integer.parseInt(fields[2]);

            long duration = untilMidnight + afterMidnight;
            return Integer.parseInt(duration+"");
        }
    }

    public String toString(){
        return start_time + "\t" + end_time + "\t" + activity_label;
    }
}
