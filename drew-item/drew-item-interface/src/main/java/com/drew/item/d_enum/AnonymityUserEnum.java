package com.drew.item.d_enum;

import java.util.Random;

public enum AnonymityUserEnum {
    james(1,"詹姆斯","/static/images/avatar/james.jpg"),
    kobe(2,"科比","/static/images/avatar/kobe.jpg"),
    wade(3,"韦德","/static/images/avatar/wade.jpeg"),
    curry(4,"库里","/static/images/avatar/curry.jpeg"),
    harden(5,"哈登","/static/images/avatar/harden.jpeg"),
    anthony(6,"安东尼","/static/images/avatar/anthony.jpeg"),
    paul(7,"保罗","/static/images/avatar/paul.jpeg"),
    jordge(8,"乔治","/static/images/avatar/jordge.jpeg"),
    westbrook(9,"韦斯特布鲁克","/static/images/avatar/Westbrook2.jpg"),
    tompson(10,"汤普森","/static/images/avatar/tompson.jpeg"),
    druant(11,"杜兰特","/static/images/avatar/durant.jpeg");

    AnonymityUserEnum(int id,String nickName,String avatar_url){
        this.id = id;
        this.nick_name = nickName;
        this.avatar_url = avatar_url;
    }



    private int id;
    private String nick_name;
    private String avatar_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public static AnonymityUserEnum getRandomAnonymityUser(){
        Random random = new Random();
        int ra = random.nextInt(10)+1;

        for (AnonymityUserEnum userEnum:AnonymityUserEnum.values()) {

            if(userEnum.getId() == ra){
                return userEnum;
            }

        }

        return null;

    }
}
