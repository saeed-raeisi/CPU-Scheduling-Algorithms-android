package com.example.csa.algorithms;

import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;

import java.util.ArrayList;
import java.util.Collections;

public class srt extends parent_abs {

    public    float average_waiting_time= (float) 0.0;
    public   float average_response_time= (float) 0.0;
    public   static float[] avg_output= new float[2];
    static ArrayList<output_process_model> output=new ArrayList<>();


    public srt(ArrayList<input_process_model> input)
    {
        int num=input.size();
        int complete = 0, t = 0, minm = 9999;
        int shortest = 0, finish_time;
        boolean check = false;
        output=set_data(input);
        Collections.sort(output,new parent_abs.SortbyAT());

        while (complete != num) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < num; j++) {
                if ((input.get(j).getArrival_time() <= t) &&
                        (input.get(j).getCbt() < minm) && input.get(j).getCbt()  > 0) {
                    minm = input.get(j).getCbt();
                    shortest = j;
                    check = true;
                    output.get(j).setCompleted(t);
                }
            }

            if (!check) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            input.get(shortest).setCbt(input.get(shortest).getCbt()-1);

            // Update minimum
            minm =input.get(shortest).getCbt();
            if (minm == 0)
                minm = 9999;

            // If a process gets completely
            // executed
            if (input.get(shortest).getCbt()== 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time

                output.get(shortest).setWaiting_time(finish_time -
                        output.get(shortest).getCbt() - output.get(shortest).getArrival_time());

                if (output.get(shortest).getWaiting_time() < 0)
                    output.get(shortest).setWaiting_time(0);
            }
            // Increment time
            t++;

        }
        for (int i = 0; i < num; i++)
        {
            output.get(i).setTurn_around_time(output.get(i).getCbt() + output.get(i).getWaiting_time());
        }

        for (int i = 0; i < num; i++) {
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
