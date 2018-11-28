package sample;

import sample.Objects.User;

public class TheMagicHolder {
    private User user;

    private static TheMagicHolder instance;

    private TheMagicHolder(){

    }
    public static TheMagicHolder getInstance(){
        if(instance == null) instance = new TheMagicHolder();

        return instance;
    }

    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return user;
    }
}
