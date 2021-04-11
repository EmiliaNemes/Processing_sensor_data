package org;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class FileWriter {

    public void writeFileForTask1(List<MonitoredData> monitoredDataList) throws IOException {

        java.io.FileWriter fileWriter = new java.io.FileWriter("Task_1.txt");
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        PrintWriter file = new PrintWriter(buffWriter);
        monitoredDataList.forEach(file::println);
        file.close();

    }

    public void writeFileForTask2(long distinctDays) throws IOException {

        java.io.FileWriter fileWriter = new java.io.FileWriter("Task_2.txt");
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        PrintWriter file = new PrintWriter(buffWriter);
        file.println("Distinct days: " + distinctDays);
        file.close();

    }

    public void writeFileForTask3(Map<String, Integer> appearanceOfActivities) throws IOException {

        java.io.FileWriter fileWriter = new java.io.FileWriter("Task_3.txt");
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        PrintWriter file = new PrintWriter(buffWriter);
        appearanceOfActivities.forEach((key, value)-> file.println(key + " appears " + value + " times."));
        file.close();

    }

    public void writeFileForTask4(Map<Integer, Map<String, Integer>> appearanceOfActivitiesPerDay) throws IOException {

        java.io.FileWriter fileWriter = new java.io.FileWriter("Task_4.txt");
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        PrintWriter file = new PrintWriter(buffWriter);
        appearanceOfActivitiesPerDay.forEach((key, value)->{file.println("On " + key + " th day of the year");
        value.forEach((key2, value2) -> file.println("  the activity: " + key2 + " appears " + value2 + " times"));});
        file.close();

    }

    public void writeFileForTask5(Map<String, Integer> activitiesDuration) throws IOException {

        java.io.FileWriter fileWriter = new java.io.FileWriter("Task_5.txt");
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        PrintWriter file = new PrintWriter(buffWriter);
        activitiesDuration.forEach((key, value)-> file.println("Total duration of activity: " + key + " is " + value + " seconds (" + value/3600 + " hours " + (value - (value/3600 * 3600))/60 + " minutes " + ((value - (value/3600 * 3600) - (value - (value/3600 * 3600))/60*60)) + " seconds)"));
        file.close();

    }

    public void writeFileForTask6(List<String> shortActivities) throws IOException {

        java.io.FileWriter fileWriter = new java.io.FileWriter("Task_6.txt");
        BufferedWriter buffWriter = new BufferedWriter(fileWriter);
        PrintWriter file = new PrintWriter(buffWriter);
        shortActivities.forEach(file::println);
        file.close();

    }

}
