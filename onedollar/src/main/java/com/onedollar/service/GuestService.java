package com.onedollar.service;

import com.onedollar.model.Guest;
import com.onedollar.repo.GuestRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest createGuest(String ip, long roomId){
        return guestRepository.save(new Guest(ip, roomId));
    }

    public long getRoom(String email){
        return guestRepository.findById(email).get().getRoom_id();
    }
}
