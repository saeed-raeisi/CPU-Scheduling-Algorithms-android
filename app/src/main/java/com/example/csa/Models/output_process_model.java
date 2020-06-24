package com.example.csa.Models;

public class output_process_model {


    String name;
    int cbt;
    int arrival_time;
    float completed;

    public float getCompleted() {
        return completed;
    }

    public void setCompleted(float completed) {
        this.completed = completed;
    }

    public output_process_model(String name, int cbt, int arrival_time, float completed, int waiting_time, int turn_around_time) {
        this.name = name;
        this.cbt = cbt;
        this.arrival_time = arrival_time;
        this.completed = completed;
        this.waiting_time = waiting_time;
        this.turn_around_time = turn_around_time;
    }

    int  waiting_time;
    int turn_around_time;

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

    public int getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }

    public int getTurn_around_time() {
        return turn_around_time;
    }

    public void setTurn_around_time(int turn_around_time) {
        this.turn_around_time = turn_around_time;
    }

    public output_process_model(String name, int cbt, int arrival_time) {
        this.name = name;
        this.cbt = cbt;
        this.arrival_time = arrival_time;
    }

    public output_process_model(String name, int cbt, int arrival_time, int waiting_time, int turn_around_time) {
        this.name = name;
        this.cbt = cbt;
        this.arrival_time = arrival_time;
        this.waiting_time = waiting_time;
        this.turn_around_time = turn_around_time;
    }
}
