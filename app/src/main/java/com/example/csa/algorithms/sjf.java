package com.example.csa.algorithms;

import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class sjf extends parent_abs {

    public    float average_waiting_time= (float) 0.0;
    public   float average_response_time= (float) 0.0;
    public   static float[] avg_output= new float[2];
    static ArrayList<output_process_model> output=new ArrayList<>();

    public static ArrayList<output_process_model> getOutput()
    {
        return output;
    }

    public sjf(ArrayList<input_process_model> input)
    {
        int num=input.size();
        output=set_data(input);

        int prevEnd=0;
        Collections.sort(output,new parent_abs.Sortbycbt());
        Collections.sort(output,new parent_abs.SortbyAT());
        Collections.sort(input,new parent_abs.Sortbycbt_input());
        Collections.sort(input,new parent_abs.SortbyAT_input());

        for(int i=0 ; i<num ;i++)
        {
            output.get(i).setCompleted(max(prevEnd,output.get(i).getArrival_time()) + output.get(i).getCbt());
            output.get(i).setTurn_around_time(output.get(i).getCompleted()-output.get(i).getArrival_time());
            output.get(i).setWaiting_time(output.get(i).getTurn_around_time()-output.get(i).getCbt());
            prevEnd=output.get(i).getCompleted();

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

}
