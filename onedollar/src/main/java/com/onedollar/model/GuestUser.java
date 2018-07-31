package com.onedollar.model;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Table(name = "guests_users")
public class GuestUser {

    private Guest guests_ip;
    private User users_phone;


    @OneToMany
    @JoinColumn(name = "guest", nullable = false)
    public Guest getRoom() {
        return guests_ip;
    }

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    public User getGuest() {
        return users_phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestUser guestUser = (GuestUser) o;
        return Objects.equals(guests_ip, guestUser.guests_ip) &&
                Objects.equals(users_phone, guestUser.users_phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guests_ip, users_phone);
    }
}
