package com.example.csa.algorithms;

import android.util.Log;

import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class sjf extends parent_abs {

    public float average_waiting_time = (float) 0.0;
    public float average_response_time = (float) 0.0;
    public static float[] avg_output = new float[2];
    static ArrayList<output_process_model> output = new ArrayList<>();

    public static ArrayList<output_process_model> getOutput() {
        return output;
    }

    public sjf(ArrayList<input_process_model> input) {
        int num = input.size(), temp = 0, val = -1;
        int avg_wt=0,avg_tt=0;
        int[] arr = new int[num];
        output = set_data(input);
        arr[0] = input.get(0).getArrival_time() + input.get(0).getCbt();
        output.get(0).setTurn_around_time((int) (arr[0] - output.get(0).getArrival_time()));
        output.get(0).setWaiting_time(output.get(0).getTurn_around_time() - output.get(0).getCbt());
        output.get(0).setCompleted(arr[0]);
        avg_wt += output.get(0).getWaiting_time();
        avg_tt += output.get(0).getTurn_around_time();


        float prevEnd = 0;
        Collections.sort(output, new parent_abs.SortbyAT());
//        Collections.sort(output,new parent_abs.Sortbycbt());


        for (int i = 1; i < num; i++) {
            temp = arr[i - 1];
            int low = output.get(i).getCbt();
            val = i;
            for (int j = i; j < num; j++) {
                if (temp >= output.get(j).getArrival_time() && low >= output.get(j).getCbt()) {
                    low = output.get(j).getCbt();
                    val = j;
                }
            }

            arr[val] = temp + output.get(val).getCbt();
            output.get(val).setTurn_around_time(arr[val] - output.get(val).getArrival_time());
            output.get(val).setWaiting_time(output.get(val).getTurn_around_time() - output.get(val).getCbt());
           output.get(val).setCompleted(arr[val]);

            Collections.swap(output,val,i);

            arr[i] = arr[val];
            avg_wt += output.get(i).getWaiting_time();
            avg_tt += output.get(i).getTurn_around_time();


            out(output);
        }
        avg_output[0] = (float) avg_wt / num;
        avg_output[1] = (float) avg_tt / num;
        Log.i("TAG", "sjf: "+avg_output[0]);
        Log.i("TAG", "sjf: "+avg_output[1]);
    }

        public static float[] get_avg ()
        {
            return avg_output;
        }
    }

