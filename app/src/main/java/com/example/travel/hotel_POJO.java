package com.example.travel;

public class hotel_POJO {
    private String name,pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public hotel_POJO() {
    }

    public hotel_POJO(String name, String pic) {
        this.name = name;
        this.pic = pic;
    }
}
