package com.figueroaluis.finalproject271;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by luisfigueroa on 4/19/18.
 */

public class TaskListsDefault extends AppCompatActivity{

    // instance fields
    ArrayList<TaskList> defaultLists;
    ArrayList<String> defaultListsNames;

    public TaskListsDefault() {
        defaultLists = new ArrayList<>();

        final TaskList INBOX_LIST = new TaskList("All Tasks");
        final TaskList TODAY = new TaskList("Today");
        final TaskList THIS_WEEK = new TaskList("This Week");
        final TaskList ACADEMIC_CALENDAR = new TaskList("Academic Calendar");
        final TaskList SOMEDAY = new TaskList("Someday");

        defaultListsNames = new ArrayList<>();
     
        // this is just a place holder
        //final TaskList ADD_NEW_LIST = new TaskList("Create List...", new ArrayList<Task>());

        defaultLists.add(INBOX_LIST);
        defaultLists.add(TODAY);
        defaultLists.add(THIS_WEEK);
        defaultLists.add(ACADEMIC_CALENDAR);
        defaultLists.add(SOMEDAY);
        // testing and placeholder

        // defaultLists.add(defaultLists.size()-1, THIS_WEEK2);

        for(int i = 0; i < defaultLists.size(); i++){
            defaultListsNames.add(defaultLists.get(i).getTaskListName());
        }

    }


}
