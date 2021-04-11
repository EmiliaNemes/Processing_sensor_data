package org;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {
    private List<MonitoredData> monitoredDataList;
    private FileWriter fileWriter = new FileWriter();

    private List<MonitoredData> computeMonitoredDataList(List<String> lines) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return lines
                .stream()
                .map(line -> {
                    String[] fields = line.split("\t\t");
                    Date start_date = null, end_date = null;
                    try {
                        start_date = dateFormat.parse(fields[0] + "");
                        end_date = dateFormat.parse(fields[1] + "");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return new MonitoredData(start_date, end_date, fields[2]);
                })
                .collect(Collectors.toList());
    }

    public void performTask1() throws IOException {
        String fileName = "Activities.txt";
        monitoredDataList = new ArrayList<>();

        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> lines = stream.collect(Collectors.toList());
        monitoredDataList = computeMonitoredDataList(lines);

        fileWriter.writeFileForTask1(monitoredDataList);
    }

    private List<Date> makeDaysList() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Date> days = monitoredDataList // punem in lista days zilele care apar ca si start_time
                .stream()
                .map(data -> {
                    try {
                        return dateFormat.parse(dateFormat.format(data.getStart_time()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());

        days.addAll(monitoredDataList // punem in lista days zilele care apar ca si end_time
                .stream()
                .map(data -> {
                    try {
                        return dateFormat.parse(dateFormat.format(data.getEnd_time()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList()));

        return days;
    }

    public void performTask2() throws IOException {
        List<Date> days = makeDaysList();
        long distinctDays = days
                .stream()
                .distinct()
                .count();
        fileWriter.writeFileForTask2(distinctDays);
    }

    public void performTask3() throws IOException {
        Map<String, Long> activitiesAppearance = monitoredDataList
                                            .stream()
                                            .collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));

        Map<String, Integer> appearanceOfActivities = activitiesAppearance // schimbam tipul de date al valorii din map (de la Long la Integer)
                                                .entrySet().stream()
                                                .collect(Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        e->e.getValue().intValue()
                                                ));

        fileWriter.writeFileForTask3(appearanceOfActivities);
    }

    public void performTask4() throws IOException {
        Map<Integer, Map<String, Long>> activitiesAppearancePerDay = monitoredDataList
                .stream()
                .collect(Collectors.groupingBy(MonitoredData::getStartDay, Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting())));

        Map<Integer, Map<String, Integer>> appearanceOfActivitiesPerDay = activitiesAppearancePerDay // schimbam tipul de date al valorii din mapul interior (de la Long la Integer)
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e->e.getValue().entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        f->f.getValue().intValue()))
                ));

        fileWriter.writeFileForTask4(appearanceOfActivitiesPerDay);
    }

    public void performTask5() throws IOException {
        Map<String, Integer> activitiesDuration = monitoredDataList
                .stream()
                .collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.summingInt(MonitoredData::activityDuration)));

        fileWriter.writeFileForTask5(activitiesDuration);
    }

    private boolean isShortActivity(String activityLabel){
        long activitiesAppearance = monitoredDataList
                .stream()
                .filter(data->data.getActivity_label().equals(activityLabel))
                .count();

        long activitiesShortAppearance = monitoredDataList
                .stream()
                .filter(data->data.getActivity_label().equals(activityLabel))
                .filter(data->data.activityDuration() < 5*60)
                .count();

        return activitiesShortAppearance * 100 / activitiesAppearance > 90;
    }

    public void performTask6() throws IOException {
        List<String> shortActivities = monitoredDataList
                .stream()
                .filter(d -> isShortActivity(d.getActivity_label()))
                .map(MonitoredData::getActivity_label)
                .distinct()
                .collect(Collectors.toList());

        fileWriter.writeFileForTask6(shortActivities);
    }

}
