package com.onedollar.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

    @Id
    private String phone_number;

    private String email;

    private String fullName;

    private long room_id;

    private String ip;

    public User(){}

    public User(String phone_number, String ip) {
        this.phone_number = phone_number;
        this.ip = ip;
    }

    public User(String phone_number, String email, long room_id, String ip) {
        this.phone_number = phone_number;
        this.email = email;
        this.room_id = room_id;
        this.ip = ip;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
