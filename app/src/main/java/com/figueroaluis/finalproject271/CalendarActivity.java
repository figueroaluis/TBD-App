package com.figueroaluis.finalproject271;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {


    private CalendarView calendarView;
    private Context mContext;
    private Button backButton;
    private ArrayList<Task> taskList;
    private ListView calendarListView;
    private TaskDAO taskDAO;
    private TaskItemAdapter adapter;
    public boolean isInFront;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        mContext = this;
        taskList = new ArrayList<Task>();

        calendarView = findViewById(R.id.calendarView);
        calendarListView = findViewById(R.id.calendar_listview);
        adapter = new TaskItemAdapter(mContext, taskList);
        calendarListView.setAdapter(adapter);
        startList();
        backButton = findViewById(R.id.calendar_back_button);
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "db_tasks").allowMainThreadQueries().build();
        taskDAO = database.getTaskDAO();

        // On click equivalent

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
                month++;
                String monthOutput = String.format(Locale.getDefault(),"%02d", month);
                String dayOutput = String.format(Locale.getDefault(),"%02d", dayOfMonth);
                //Toast.makeText(mContext, "Year: " + year + " Month: " + month + " Day: " + dayOfMonth,Toast.LENGTH_LONG).show();
                if(taskDAO.getTaskBySingleDate(year+"-"+monthOutput+"-"+dayOutput) != null){
                    //Toast.makeText(mContext, "getTask not null", Toast.LENGTH_SHORT).show();
                    taskList.clear();
                    taskList.addAll(taskDAO.getTaskBySingleDate(year+"-"+monthOutput+"-"+dayOutput));
                    //taskList.addAll(taskDAO.getTasks());
                    //Testing
                    calendarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Task selectedTask = taskList.get(position);
                            Intent detailIntent = new Intent(mContext, TaskDetailActivity.class);

                            detailIntent.putExtra("taskID", selectedTask.getTaskID());
                            detailIntent.putExtra("isInFrontCal", isInFront);

                            startActivity(detailIntent);
                        }
                    });
                    adapter.notifyDataSetChanged();

                }



            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent goBack = new Intent(mContext, MainActivity.class);
                startActivity(goBack);
            }

        });


    }

    @Override
    public void onResume(){
        super.onResume();
        taskList.clear();
        startList();
        adapter.notifyDataSetChanged();

        isInFront = true;
    }

    @Override
    public void onPause(){
        super.onPause();

        isInFront = false;
    }


    public void startList(){
        Task task1 = new Task();
        task1.setTitle("Select a day to see tasks");
        taskList.add(task1);
        calendarListView.setOnItemClickListener(null);
    }

}
