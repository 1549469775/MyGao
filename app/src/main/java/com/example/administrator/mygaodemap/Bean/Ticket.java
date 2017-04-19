package com.example.administrator.mygaodemap.Bean;

/**
 * Created by John on 2017/3/31.
 */

public class Ticket {

    private long id;
    private String createAt;
    private String weight;
//    private int weight;
    private double price;
    private String fromAt;
    private String toAt;
    private double distance;
    private String type;
    private String wantTime;
    private String realStartTime;
    private String realAtTime;
    private int wasteTime;
    private String airplaneType;
    private String note;
    private String bookState;
    private String fromWho;
    private String toWho;
    private String number;
//    private long number;

//    public Ticket(long id, String createAt, int weight, double price, String fromAt, String toAt, double distance, String type, String wantTime, String realStartTime, String realAtTime, int wasteTime, String airplaneType, String note, String bookState, String fromWho, String toWho, long number) {
//        this.id = id;
//        this.createAt = createAt;
//        this.weight = weight;
//        this.price = price;
//        this.fromAt = fromAt;
//        this.toAt = toAt;
//        this.distance = distance;
//        this.type = type;
//        this.wantTime = wantTime;
//        this.realStartTime = realStartTime;
//        this.realAtTime = realAtTime;
//        this.wasteTime = wasteTime;
//        this.airplaneType = airplaneType;
//        this.note = note;
//        this.bookState = bookState;
//        this.fromWho = fromWho;
//        this.toWho = toWho;
//        this.number = number;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFromAt() {
        return fromAt;
    }

    public void setFromAt(String fromAt) {
        this.fromAt = fromAt;
    }

    public String getToAt() {
        return toAt;
    }

    public void setToAt(String toAt) {
        this.toAt = toAt;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWantTime() {
        return wantTime;
    }

    public void setWantTime(String wantTime) {
        this.wantTime = wantTime;
    }

    public String getRealStartTime() {
        return realStartTime;
    }

    public void setRealStartTime(String realStartTime) {
        this.realStartTime = realStartTime;
    }

    public String getRealAtTime() {
        return realAtTime;
    }

    public void setRealAtTime(String realAtTime) {
        this.realAtTime = realAtTime;
    }

    public int getWasteTime() {
        return wasteTime;
    }

    public void setWasteTime(int wasteTime) {
        this.wasteTime = wasteTime;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBookState() {
        return bookState;
    }

    public void setBookState(String bookState) {
        this.bookState = bookState;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
