package com.example.csa.algorithms;

import com.example.csa.Models.output_process_model;
import com.example.csa.Models.input_process_model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class hrrn extends parent_abs {


    float average_waiting_time= (float) 0.0;
    float average_response_time= (float) 0.0;
    static float[] avg_output= new float[2];
    static ArrayList<output_process_model> output=new ArrayList<>();

    public hrrn(ArrayList<input_process_model> input)
    {
            int num=input.size();
            int sum_bt=0,t;
            output=set_data(input);
           Collections.sort(output,new parent_abs.SortbyAT());
        for(int i=0;i<num;i++)
    {
        output.get(i).setCompleted(0);
        sum_bt+= output.get(i).getCbt();
    }

            for (t = output.get(0).getArrival_time(); t < sum_bt;) {

        // Set lower limit to response ratio
        float hrr = -9999;

        // Response Ratio Variable
        float temp;

        // Variable to store next processs selected
        int loc = 0;
        for (int i = 0; i < num; i++) {

            // Checking if process has arrived and is Incomplete
            if (output.get(i).getArrival_time() <= t && output.get(i).getCompleted() != 1) {

                // Calculating Response Ratio
                temp = (output.get(i).getCbt() + output.get(i).getArrival_time()) / output.get(i).getCbt();

                // Checking for Highest Response Ratio
                if (hrr < temp) {

                    // Storing Response Ratio
                    hrr = temp;

                    // Storing Location
                    loc = i;
                }
            }
        }


        // Updating time value
        t += output.get(loc).getCbt();

        // Calculation of waiting time
                output.get(loc).setWaiting_time( t - output.get(loc).getArrival_time() - output.get(loc).getCbt());

        // Calculation of Turn Around Time
                output.get(loc).setTurn_around_time(t -output.get(loc).getArrival_time());
        output.get(loc).setCompleted(1);

        // Sum Turn Around Time for average
        //avgtt += p[loc].tt;

        // Calculation of Normalized Turn Around Time
        //p[loc].ntt = ((float)p[loc].tt / p[loc].bt);

        // Updating Completion Status
        //p[loc].completed = 1;

    }
        for (int i = 0; i < output.size(); i++) {
            average_waiting_time += output.get(i).getWaiting_time();
            average_response_time += output.get(i).getTurn_around_time();
        }
        avg_output[0] = (float)average_waiting_time/num;
        avg_output[1] = (float)average_response_time/num;
        out(output);

    }

    public static float[] get_avg()
    {
        return avg_output;
    }
    public static ArrayList<output_process_model> getOutput()
    {
        return output;
    }

}
