package com.onedollar.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="guests")
public class Guest{
    @Id
    private String ip;

    private long room_id;

    @OneToMany
    private List<User> users;

    public Guest(){
        users = new ArrayList<User>();
    }

    public Guest(String ip, long room_id) {
        this.ip = ip;
        this.room_id = room_id;
    }

    public String getIp() {
        return ip;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "ip='" + ip + '\'' +
                ", room_id=" + room_id +
                ", users=" + users.toString() +
                '}';
    }
}
