package com.onedollar.model;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Table(name = "rooms_guests")
public class RoomGuest {
    private Room room_id;
    private Guest guests_ip;

    @ManyToOne
    @JoinColumn(name = "guests", nullable = false)
    public Guest getGuest() {
        return this.guests_ip;
    }

    @OneToMany
    @JoinColumn(name = "rooms", nullable = false)
    public Room getRoom() {
        return this.room_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomGuest)) return false;
        RoomGuest roomGuest = (RoomGuest) o;
        return Objects.equals(room_id, roomGuest.room_id) &&
                Objects.equals(guests_ip, roomGuest.guests_ip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(room_id, guests_ip);
    }
}
