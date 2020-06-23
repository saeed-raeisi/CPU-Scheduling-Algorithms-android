package com.example.csa.Models;

public class input_process_model {

    String name;
    int cbt;
    int arrival_time;
    int completed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCbt() {
        return cbt;
    }

    public void setCbt(int cbt) {
        this.cbt = cbt;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public input_process_model() {
        this.name = "";
        this.cbt = 0;
        this.arrival_time = 0;
    }


    public input_process_model(String name, int cbt, int arrival_time) {
        this.name = name;
        this.cbt = cbt;
        this.arrival_time = arrival_time;
    }



    public input_process_model(String name, int cbt, int arrival_time, int completed) {
        this.name = name;
        this.cbt = cbt;
        this.arrival_time = arrival_time;
        this.completed = completed;
    }
}
