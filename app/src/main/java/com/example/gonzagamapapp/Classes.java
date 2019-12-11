package com.example.gonzagamapapp;

/**
 * Class that holds information about a users class/schedule
 */

public class Classes {

    private int id;
    private String title;
    private String crnNumber;
    private String building;
    private String roomNumber;

    public Classes(){
        id = -1; // determined later by database table insertion order
        title = "Android App Development";
        building = "pacaar";
        roomNumber = "106";
    }

    public Classes(String title, String building, String roomNumber){
        this();
        this.title = title;
        this.building = building;
        this.roomNumber = roomNumber;
    }

    public Classes(int id, String title, String crnNumber, String building, String roomNumber){
        this.id = id;
        this.title = title;
        this.building = building;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
