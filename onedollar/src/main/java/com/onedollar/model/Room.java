package com.onedollar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int capacity = 5;
    @Column(name = "guests")
    @OneToMany
    public List<Guest> guests;
    @Column(name="users")
    @OneToMany
    public List<User> users;
    private int noUsers;
    private int noGuests;

    public Room(){
        guests = new ArrayList<Guest>();
        users = new ArrayList<User>();
        noGuests=0;
        noUsers=0;
    }
    public Room(long id, List<Guest> guests, List<User> users){
        this.id = id;
        this.guests = guests;
        this.users = users;
    }

    public int getNoUsers() {
        return noUsers;
    }

    public void setNoUsers(int noUsers) {
        this.noUsers = noUsers;
    }

    public int getNoGuests() {
        return noGuests;
    }

    public void setNoGuests(int noGuests) {
        this.noGuests = noGuests;
    }

    public long getId() {
        return id;
    }

    public void addAGuest(Guest guest){
        guests.add(guest);
        noGuests++;
    }

    public void addAUser(User user) { users.add(user); noUsers++; }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", guests=" + guests.toString() +
                ", users=" + users.toString() +
                '}';
    }
}
