package com.example.gonzagamapapp;

public class Classes {

    private int id;
    private String title;
    private String crnNumber;
    private String building;
    private String roomNumber;

    public Classes(){
        id = -1; // determined later by database table insertion order
        title = "Android App Development";
        crnNumber = "12345";
        building = "pacaar";
        roomNumber = "106";
    }

    public Classes(String title, String crnNumber, String building, String roomNumber){
        this();
        this.title = title;
        this.crnNumber = crnNumber;
        this.building = building;
        this.roomNumber = roomNumber;
    }

    public Classes(int id, String title, String crnNumber, String building, String roomNumber){
        this.id = id;
        this.title = title;
        this.crnNumber = crnNumber;
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

    public String getCrnNumber() {
        return crnNumber;
    }

    public void setCrnNumber(String crnNumber) {
        this.crnNumber = crnNumber;
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
