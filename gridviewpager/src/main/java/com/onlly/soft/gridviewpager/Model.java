package com.onlly.soft.gridviewpager;

public class Model<TICON> {
    public String name;
    public String description;
    public TICON icon;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object obj;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model(String name,String description, TICON icon) {
        this.name = name;
        this.icon = icon;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TICON getIcon() {
        return this.icon;
    }

    public void setIcon(TICON icon) {
        this.icon = icon;
    }
}