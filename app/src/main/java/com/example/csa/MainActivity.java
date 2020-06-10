package com.example.csa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;
import com.example.csa.algorithms.fifo;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BarChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setTitle("CombinedChartActivity");


        ArrayList<input_process_model> input = new ArrayList<>();
        ArrayList<output_process_model> output = new ArrayList<>();
        input = test_data(input);
        output = new fifo(input).out(output);
        output = fifo.getOutput();

        chart = findViewById(R.id.chart1);
         List<BarDataSet> list=new ArrayList<>();

        for (int i = 0; i < output.size(); i++) {
            ArrayList<BarEntry> values1 = new ArrayList<>();
            ArrayList<BarEntry> values2 = new ArrayList<>();
            ArrayList<BarEntry> values3 = new ArrayList<>();
            values1.add(new BarEntry(i, (float) output.get(i).getTurn_around_time()));
            values2.add(new BarEntry(i, (float) output.get(i).getWaiting_time()));
            values3.add(new BarEntry(i, (float) output.get(i).getCbt()));
            BarDataSet set1,set2,set3;
            set1 = new BarDataSet(values1, "at");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(values2, "wt");
            set2.setColor(Color.rgb(164, 228, 251));
            set3 = new BarDataSet(values3, "cbt");
            set3.setColor(Color.rgb(242, 247, 158));
            list.add(set1);
            list.add(set2);
            list.add(set3);
        }
        BarData data = new BarData(list.get(0));
        for (int i = 1; i < list.size(); i++) {

            data.addDataSet(list.get(i));
        }
      //  BarData data = new BarData(list.get(0));
        data.setValueFormatter(new LargeValueFormatter());
        // data.setValueTypeface(tfLight);

        // data.setData(output.toArray());
        chart.setData(data);
        chart.invalidate();
//        ArrayList<BarEntry> values1 = new ArrayList<>();
//        ArrayList<BarEntry> values2 = new ArrayList<>();
//        ArrayList<BarEntry> values3 = new ArrayList<>();
//        ArrayList<BarEntry> values4 = new ArrayList<>();

        //float randomMultiplier = seekBarY.getProgress() * 100000f;
//
//        for (int i = 0; i < 2; i++) {
//            values1.add(new BarEntry(i, (float) output.get(i).getTurn_around_time()));
//            values2.add(new BarEntry(i, (float) output.get(i).getWaiting_time()));
//           // values3.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
//           // values4.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
//        }

//        BarDataSet set1, set2, set3, set4;
//
//        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
//
//            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
//            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
//            set3 = (BarDataSet) chart.getData().getDataSetByIndex(2);
//            set4 = (BarDataSet) chart.getData().getDataSetByIndex(3);
//            set1.setValues(values1);
//            set2.setValues(values2);
//           // set3.setValues(values3);
//           // set4.setValues(values4);
//            chart.getData().notifyDataChanged();
//            chart.notifyDataSetChanged();
//
//        } else {
//            // create 4 DataSets
//            set1 = new BarDataSet(values1, "Company A");
//            set1.setColor(Color.rgb(104, 241, 175));
//            set2 = new BarDataSet(values2, "Company B");
//            set2.setColor(Color.rgb(164, 228, 251));
           // set3 = new BarDataSet(values3, "Company C");
           // set3.setColor(Color.rgb(242, 247, 158));
           // set4 = new BarDataSet(values4, "Company D");
           // set4.setColor(Color.rgb(255, 102, 0));




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

        ArrayList<input_process_model> test_data(ArrayList<input_process_model> input)
        {

            input_process_model p1 = new input_process_model("p1", 8, 0);
            input_process_model p2 = new input_process_model("p2", 4, 1);
            input_process_model p3 = new input_process_model("p3", 9, 2);
            input_process_model p4 = new input_process_model("p4", 5, 3);
            input.add(0, p1);
            input.add(1, p2);
            input.add(2, p3);
            input.add(3, p4);


            return input;
        }


}
