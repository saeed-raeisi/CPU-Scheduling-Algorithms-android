package com.example.csa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;
import com.example.csa.algorithms.fifo;
import com.example.csa.algorithms.hrrn;
import com.example.csa.algorithms.parent_abs;
import com.example.csa.algorithms.sjf;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BarChart chart;
    private TextView avg_wt,avg_at;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setTitle("نمودار نتیجه ");

 //init
        avg_wt=findViewById(R.id.avg_wt_txt);
        avg_at=findViewById(R.id.avg_at_txt);
        chart = findViewById(R.id.chart1);
 // fifo
        ArrayList<input_process_model> input = new ArrayList<>();
        ArrayList<output_process_model> output = new ArrayList<>();
        input = test_data(input);
       // output = new fifo(input).out(output);
//sjf
         new sjf(input);
        output = sjf.getOutput();
//hrrn
         new hrrn(input);
         output= hrrn.getOutput();
// fifo,sjf result
        String s1=": میانگین زمان انتظار";
        String s2=": میانیگن زمان پاسخ";
        avg_wt.setText(String.valueOf(hrrn.get_avg()[0])+s1);
        avg_at.setText(String.valueOf(hrrn.get_avg()[1])+s2 );

            ArrayList<BarEntry> cbt = new ArrayList<>();
            ArrayList<BarEntry> wt = new ArrayList<>();
            ArrayList<BarEntry> at = new ArrayList<>();

            for (int j = 0; j < output.size(); j++) {
                cbt.add(new BarEntry(j, (float) output.get(j).getCbt()));
                wt.add(new BarEntry(j, (float) output.get(j).getWaiting_time()));
                at.add(new BarEntry(j, (float) output.get(j).getTurn_around_time()));
            }
            BarDataSet set1,set2,set3;
            set1 = new BarDataSet(cbt, "Turn_around_time");
            //set1.setColor(Color.rgb(104, 241, 175));
            set1.setColor(Color.GREEN);
            set2 = new BarDataSet(wt, "Waiting_time");
           // set2.setColor(Color.rgb(164, 228, 251));
            set2.setColor(Color.RED);
            set3 = new BarDataSet(at, "cbt");
           //set3.setColor(Color.rgb(242, 247, 158));
            set3.setColor(Color.BLUE);

            BarData data=new BarData(set1,set2,set3);

        data.setValueFormatter(new LargeValueFormatter());

        chart.setData(data);

        float groupSpace = 0.50f;
        float barSpace = 0.02f;
        float barWidth = 0.46f;

        int startYear = 0;
        int endYear = 8;
        // Disable zoom - SET Group - Refresh
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinValue(startYear);
        chart.getXAxis().setAxisMaximum(endYear);
        chart.groupBars(startYear, groupSpace, barSpace);
        chart.setFitBars(true);
        chart.invalidate();

            Log.i("1", "onCreate: 1");

            for (int i = 0; i < output.size(); i++) {

                Log.i("TAG", "onCreate: " + output.get(i).getName() + "  " + String.valueOf(output.get(i).getCbt()) +
                        "  " + String.valueOf(output.get(i).getArrival_time()) + "  " +
                        String.valueOf(output.get(i).getTurn_around_time()) + "  " +
                        String.valueOf(output.get(i).getWaiting_time()) + "  ");
            }
            Log.i("avg", "avg  " + String.valueOf(fifo.get_avg()[0]) + "  " + String.valueOf(fifo.get_avg()[1]));
            Log.i("2", "onCreate: 2");

        }

        // Just data Test!
        ArrayList<input_process_model> test_data(ArrayList<input_process_model> input)
        {
            // 2 1 0 3

            input_process_model p1 = new input_process_model("p3", 9, 0);
            input_process_model p2 = new input_process_model("p2", 4, 0);
            input_process_model p3 = new input_process_model("p1", 8, 0);
            input_process_model p4 = new input_process_model("p4", 5, 0);
            input.add(0, p1);
            input.add(1, p2);
            input.add(2, p3);
            input.add(3, p4);
            return input;
        }


}
