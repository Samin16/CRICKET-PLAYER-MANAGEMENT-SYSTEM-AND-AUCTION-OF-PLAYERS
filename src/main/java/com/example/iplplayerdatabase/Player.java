package com.example.iplplayerdatabase;

import java.io.Serializable;

public class Player implements Serializable
{
    private  static  final long serialVersionUID=12345L;
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int jurseyNumber;
    private int weeklySalary;

    
    

    public Player(String name, String country, int age, double height, String club, String position, int jurseyNumber,int weeklySalary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.jurseyNumber = jurseyNumber;
        this.weeklySalary = weeklySalary;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String getClub() {
        return club;
    }
    public void setClub(String club) {
        this.club = club;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public int getJurseyNumber() {
        return jurseyNumber;
    }
    public void setJurseyNumber(int jurseyNumber) {
        this.jurseyNumber = jurseyNumber;
    }
    public int getWeeklySalary() {
        return weeklySalary;
    }
    public void setWeeklySalary(int weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public void displayPlayer(){
        System.out.println("Name : " + name);
        System.out.println("Country : " + country);
        System.out.println("Age : " + age+" years");
        System.out.println("Height : " + height+" meters");
        System.out.println("Club : "+ club);
        System.out.println("Position : " + position);
        System.out.println("JurseyNumber : " + jurseyNumber);
        System.out.println("WeeklySalary : " + weeklySalary+" Rs");
        System.out.println();
    }

    @Override
    public String toString() {
        return name + "," + country + "," + age + "," + height + ","
                + club + "," + position + "," + jurseyNumber + "," + weeklySalary;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)return false;
        if(obj==this)return true;
        if(obj instanceof Player){
            Player x=(Player)obj;
            return name.equalsIgnoreCase(x.name);
        }
        return false;
    }
}