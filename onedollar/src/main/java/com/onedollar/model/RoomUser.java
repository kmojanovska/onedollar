package com.onedollar.model;


import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Table(name = "rooms_users")
public class RoomUser {

    private Room room_id;
    private User users_phone;

    @ManyToOne
    @JoinColumn(name = "users", nullable = false)
    public User getUser() {
        return this.users_phone;
    }

    @OneToMany
    @JoinColumn(name = "rooms", nullable = false)
    public Room getRoom() {
        return this.room_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomUser roomUser = (RoomUser) o;
        return Objects.equals(room_id, roomUser.room_id) &&
                Objects.equals(users_phone, roomUser.users_phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_id, users_phone);
    }
}
