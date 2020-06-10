package com.example.csa.algorithms;

import android.annotation.SuppressLint;
import android.os.Build;


import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;
import java.util.ArrayList;
import java.util.Collections;


public class fifo {

    float average_waiting_time= (float) 0.0;
    float average_response_time= (float) 0.0;

    static float[] avg_output= new float[2];


    public static ArrayList<output_process_model> getOutput() {
        return output;
    }

    // ArrayList<input_process_model> input=new ArrayList<>();
    static ArrayList<output_process_model> output=new ArrayList<>();

    public fifo(ArrayList<input_process_model> input)
    {
          int num=input.size();
          output=set_data(input);

          int prevEnd=0;
        output=compare(output);

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

    public ArrayList<output_process_model> out(ArrayList<output_process_model> output)
    {
        return output;
    }

      public static float[] get_avg()
    {
        return avg_output;
    }


    ArrayList<output_process_model> compare(ArrayList<output_process_model> input)
    {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size()-1; j++) {
                if(input.get(j).getArrival_time() > input.get(j+1).getArrival_time())
                {
                    Collections.swap(input,j,j+1);
                }

            }
        }
        return input;

    }

    int max(int a,int b)
    {
        if(a>b)
            return a;
        else
            return b;
    }

    ArrayList<output_process_model> set_data(ArrayList<input_process_model> input)
    {
        ArrayList<output_process_model> output=new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            output_process_model temp=new output_process_model(
                    input.get(i).getName(),
                    input.get(i).getCbt(),
                    input.get(i).getArrival_time()
            );
            output.add(i,temp);
        }
        return output;
    }
}
