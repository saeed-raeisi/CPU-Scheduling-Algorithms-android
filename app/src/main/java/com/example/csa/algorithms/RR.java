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

        int i, total = 0, x, counter = 0, time_quantum=1;
        int wait_time = 0, turnaround_time = 0;
        //arrival_time[10], burst_time[10],
        int[] temp=new int[num];
        for(int j=0;j<num;j++)
        {
            temp[j]=output.get(j).getCbt();
        }
        x=num;
        for(total = 0, i = 0; x != 0;)
        {
            if(temp[i] <= time_quantum && temp[i] > 0)
            {
                total = total + temp[i];
                temp[i] = 0;
                counter = 1;
            }
            else if(temp[i] > 0)
            {
                temp[i] = temp[i] - time_quantum;
                total = total + time_quantum;
            }
            if(temp[i] == 0 && counter == 1)
            {
                x--;
                output.get(i).setWaiting_time(total - output.get(i).getArrival_time() - output.get(i).getCbt());
                output.get(i).setTurn_around_time(total - output.get(i).getArrival_time());
                output.get(i).setCompleted(total);
                wait_time = wait_time + total - output.get(i).getArrival_time() - output.get(i).getCbt();
                turnaround_time = turnaround_time + total - output.get(i).getArrival_time();
                counter = 0;
            }
            if(i == num - 1)
            {
                i = 0;
            }
            else if( output.get(i+1).getArrival_time() <= total)
            {
                i++;
            }
            else
            {
                i = 0;
            }
        }

        average_waiting_time = ((float)(wait_time * 1.0 / num));
        average_response_time = ((float)(turnaround_time * 1.0 / num));

//        while(true)
//        {
//            boolean flag=true;
//            for(int i=0 ;i<num;i++)
//            {
//                if(input.get(i).getArrival_time()<=t)
//                {
//                    if(input.get(i).getArrival_time()<=q)
//                    {
//                        if(input.get(i).getCbt()>0)
//                        {
//                            flag=false;
//                            if(input.get(i).getCbt()>q)
//                            {
//                                t+=q;
//                                input.get(i).setCbt(input.get(i).getCbt()-q);
//                                input.get(i).setArrival_time(input.get(i).getArrival_time()+q);
//                            }
//                            else
//                            {
//                                // last time
//                                t+=input.get(i).getCbt();
//                                output.get(i).setTurn_around_time(t- output.get(i).getArrival_time());
//                                output.get(i).setWaiting_time(t-output.get(i).getCbt()- output.get(i).getArrival_time());
//                                input.get(i).setCbt(0);
//                            }
//                        }
//                    }
//                    else if(input.get(i).getArrival_time()>q)
//                    {
//                        for(int j=0;j<num;j++)
//                    {
//                        if(input.get(j).getArrival_time() < input.get(i).getArrival_time())
//                        {
//                            if(input.get(j).getCbt()>0)
//                            {
//                                flag=false;
//                                if(input.get(j).getCbt()>q)
//                                {
//                                    t+=q;
//                                    input.get(j).setCbt(input.get(i).getCbt()-q);
//                                    input.get(j).setArrival_time(input.get(i).getArrival_time()+q);
//                                }
//                                else{
//                                    t+=input.get(j).getCbt();
//                                    output.get(j).setTurn_around_time(t- output.get(j).getArrival_time());
//                                    output.get(j).setWaiting_time(t-output.get(j).getCbt()- output.get(j).getArrival_time());
//                                    input.get(j).setCbt(0);
//                                }
//                            }
//                        }
//                    }
//
//                        if(input.get(i).getCbt()>0)
//                        {
//                            flag=false;
//
//                            if(input.get(i).getCbt()>q)
//                            {
//                                t+=q;
//                                input.get(i).setCbt(input.get(i).getCbt()-q);
//                                input.get(i).setArrival_time(input.get(i).getArrival_time()+q);
//                            }
//                            else
//                            {
//                                t+=input.get(i).getCbt();
//                                output.get(i).setTurn_around_time(t- output.get(i).getArrival_time());
//                                output.get(i).setWaiting_time(t-output.get(i).getCbt()- output.get(i).getArrival_time());
//                                input.get(i).setCbt(0);
//                            }
//
//                        }
//
//                    }
//                }
//                else if(input.get(i).getArrival_time()>t)
//                {
//                    t++;
//                    i--;
//                }
//            }
//            if(flag)
//            {
//                break;
//            }
//        }
//        for (int i = 0; i < num; i++) {
//            average_waiting_time += output.get(i).getWaiting_time();
//            average_response_time += output.get(i).getTurn_around_time();
//        }

        avg_output[0] = (float)average_waiting_time;
        avg_output[1] = (float)average_response_time;
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
