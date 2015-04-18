package com.example.jianlongguo.abs.Entities;

/**
 * Created by jianlongguo on 3/4/15.
 */
public class NavItem {
    public String mTitle;
    public int mIcon;

    public NavItem(String title, int icon){
        this.mTitle = title;
        this.mIcon = icon;
    }

    public String getTitle(){
        return this.mTitle;
    }

    public int getmIcon(){
        return this.mIcon;
    }

    public NavItem(String title){
        this.mTitle=title;
    }

    public void setmTitle(String title){
        this.mTitle = title;
    }

    public void setmIcon(int icon){
        this.mIcon = icon;
    }
}

