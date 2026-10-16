package com.example.timer;

public class ShowTimer {

    private String status = "NOT STARTED";
    private int soldNow;
    private int totalTicketsSold;

    public void startShow() {
        status = "SHOW RUNNING";
        System.out.println("Starting movie show");
    }

    public synchronized void sellTickets(int tickets) {
        soldNow = tickets;
        totalTicketsSold += tickets;
    }

    public String getStatus() {
        return status;
    }

    public synchronized int getSoldNow() {
        return soldNow;
    }

    public synchronized int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public void stopShow() {
        status = "SHOW STOPPED";
        System.out.println("Stopping movie show");
    }
}
