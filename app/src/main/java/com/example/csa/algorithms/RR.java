package com.example.csa.algorithms;

import com.example.csa.Models.input_process_model;
import com.example.csa.Models.output_process_model;

import java.util.ArrayList;
import java.util.Collections;

public class RR extends parent_abs {

    public    float average_waiting_time= (float) 0.0;
    public   float average_response_time= (float) 0.0;
    public   static float[] avg_output= new float[2];
    static ArrayList<output_process_model> output=new ArrayList<>();

    public RR(ArrayList<input_process_model> input)
    {
        int num=input.size();
        int t=0;
        int q=2;
        output=set_data(input);
        Collections.sort(output,new parent_abs.SortbyAT());

        while(true)
        {
            boolean flag=true;
            for(int i=0 ;i<num;i++)
            {
                if(input.get(i).getArrival_time()<=t)
                {
                    if(input.get(i).getArrival_time()<=q)
                    {
                        if(input.get(i).getCbt()>0)
                        {
                            flag=false;
                            if(input.get(i).getCbt()>q)
                            {
                                t+=q;
                                input.get(i).setCbt(input.get(i).getCbt()-q);
                                input.get(i).setArrival_time(input.get(i).getArrival_time()+q);
                            }
                            else
                            {
                                // last time
                                t+=input.get(i).getCbt();
                                output.get(i).setTurn_around_time(t- output.get(i).getArrival_time());
                                output.get(i).setWaiting_time(t-output.get(i).getCbt()- output.get(i).getArrival_time());
                                input.get(i).setCbt(0);
                            }
                        }
                    }
                    else if(input.get(i).getArrival_time()>q)
                    {
                        for(int j=0;j<num;j++)
                    {
                        if(input.get(j).getArrival_time() < input.get(i).getArrival_time())
                        {
                            if(input.get(j).getCbt()>0)
                            {
                                flag=false;
                                if(input.get(j).getCbt()>q)
                                {
                                    t+=q;
                                    input.get(j).setCbt(input.get(i).getCbt()-q);
                                    input.get(j).setArrival_time(input.get(i).getArrival_time()+q);
                                }
                                else{
                                    t+=input.get(j).getCbt();
                                    output.get(j).setTurn_around_time(t- output.get(j).getArrival_time());
                                    output.get(j).setWaiting_time(t-output.get(j).getCbt()- output.get(j).getArrival_time());
                                    input.get(j).setCbt(0);
                                }
                            }
                        }
                    }

                        if(input.get(i).getCbt()>0)
                        {
                            flag=false;

                            if(input.get(i).getCbt()>q)
                            {
                                t+=q;
                                input.get(i).setCbt(input.get(i).getCbt()-q);
                                input.get(i).setArrival_time(input.get(i).getArrival_time()+q);
                            }
                            else
                            {
                                t+=input.get(i).getCbt();
                                output.get(i).setTurn_around_time(t- output.get(i).getArrival_time());
                                output.get(i).setWaiting_time(t-output.get(i).getCbt()- output.get(i).getArrival_time());
                                input.get(i).setCbt(0);
                            }

                        }

                    }
                }
                else if(input.get(i).getArrival_time()>t)
                {
                    t++;
                    i--;
                }
            }
            if(flag)
            {
                break;
            }
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
