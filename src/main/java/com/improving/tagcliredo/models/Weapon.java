package com.improving.tagcliredo.models;

public class Weapon {

    private int id;
    private String name;
    private String area;
    private String itemType;

    public Weapon(int id, String name, String area, String itemType) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getItemType() {
        return itemType;
    }
}
