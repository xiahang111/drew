package com.drew.item.d_enum;

import java.util.Random;

public enum PlantUserEnum {
    mercury(0,"水星"),
    venus(1,"金星"),
    earth(2,"地球"),
    mars(3,"火星"),
    jupiter(4,"木星"),
    saturn(5,"土星"),
    uranus(6,"天王星"),
    neptune(7,"海王星"),
    ;

    PlantUserEnum(int id,String plant){
        this.id = id;
        this.plant = plant;
    }

    private int id;
    private String plant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public static PlantUserEnum getRandomPlant(){
        Random random = new Random();
        int ra = random.nextInt(7);

        for (PlantUserEnum plantUserEnum:PlantUserEnum.values()) {

            if(plantUserEnum.getId() == ra){
                return plantUserEnum;
            }
        }

        return null;
    }
}
