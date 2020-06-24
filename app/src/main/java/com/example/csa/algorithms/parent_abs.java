package com.example.csa.algorithms;

import android.util.Log;

import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class parent_abs {

    float average_waiting_time= (float) 0.0;
    float average_response_time= (float) 0.0;

    static float[] avg_output= new float[2];

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

    public ArrayList<output_process_model> out(ArrayList<output_process_model> output)
    {
        return output;
    }

    class SortbyAT implements Comparator<output_process_model>
    {

        // Used for sorting in ascending order of

        // roll number

        public int compare(output_process_model a, output_process_model b)

        {

            return a.getArrival_time() - b.getArrival_time();

        }

    }
    class SortbyAT_input implements Comparator<input_process_model>
    {

        // Used for sorting in ascending order of

        // roll number

        public int compare(input_process_model a, input_process_model b)

        {

            return a.getArrival_time() - b.getArrival_time();

        }

    }

    class Sortbycbt implements Comparator<output_process_model>
    {

        // Used for sorting in ascending order of

        // roll number

        public int compare(output_process_model a, output_process_model b)

        {

            return a.getCbt() - b.getCbt();

        }

    }

    class Sortbycbt_input implements Comparator<input_process_model>
    {

        // Used for sorting in ascending order of

        // roll number

        public int compare(input_process_model a, input_process_model b)

        {

            return a.getCbt() - b.getCbt();

        }

    }

   public float max(float a,float b)
    {
        if(a>b)
            return a;
        else
            return b;
    }

    public static float[] get_avg()
    {
        return avg_output;
    }


}
