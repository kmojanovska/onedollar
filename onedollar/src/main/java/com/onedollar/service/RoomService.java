package com.onedollar.service;

import com.onedollar.model.Guest;
import com.onedollar.model.Room;
import com.onedollar.model.User;
import com.onedollar.repo.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final Logger logger = LoggerFactory.getLogger(RoomService.class);
    private final RoomRepository roomRepository;
    public long activeRoom;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        activeRoom = 1;
        roomRepository.save(new Room());
    }

    public void declareAWinner(Room room){

    }

    public long addAGuest(Guest guest){
        Room room = roomRepository.getOne(activeRoom);
        logger.info(room.toString());
        if(room.users.size()==room.getCapacity()*activeRoom){
            //tuka vidi dali treba da se proglasi pobednik
            activeRoom++;
            Room roomNew = new Room();
            roomRepository.save(roomNew);
            room = roomNew;
        }
        room.addAGuest(guest);
        roomRepository.save(room);
        return activeRoom;
    }

    public long addUser(User user){
        Room room = roomRepository.getOne(activeRoom);
        logger.info(room.toString());
        if(room.users.size()==room.getCapacity()*activeRoom){
            //tuka vidi dali treba da se proglasi pobednik
            activeRoom++;
            Room roomNew = new Room();
            roomRepository.save(roomNew);
            room = roomNew;
        }
        room.addAUser(user);
        roomRepository.save(room);
        return activeRoom;
    }

    public Room findRoom(long id){
        return roomRepository.getOne(id);
    }

}
