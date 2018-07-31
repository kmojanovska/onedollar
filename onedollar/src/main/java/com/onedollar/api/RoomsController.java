package com.onedollar.api;

import com.onedollar.model.Guest;
import com.onedollar.model.Room;
import com.onedollar.model.User;
import com.onedollar.service.GuestService;
import com.onedollar.service.RoomService;
import com.onedollar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RoomsController {

    private final Logger logger = LoggerFactory.getLogger(RoomsController.class);
    private final GuestService guestService;
    private final UserService userService;
    private final RoomService roomService;
    Map<String, Long> mapa = new TreeMap<>();
    public RoomsController(GuestService guestService, UserService userService, RoomService roomService) {
        this.guestService = guestService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @GetMapping("/room/ip")
    public Map<String, Long> TellMeMyIp(HttpServletRequest request){
        if( mapa.containsKey(request.getRemoteAddr()) ){
            Map<String, Long> tmp = new TreeMap<String, Long>();
            tmp.put("Already used or not acceptable format for email",(long)0);
            return tmp;
        }
        Guest guest = guestService.createGuest(request.getRemoteAddr(),roomService.activeRoom);
        long room = roomService.addAGuest(guest);
        mapa.put(guest.getIp(),room);
        return mapa;
    }

    @GetMapping("/roomp/{phone}/{email}")
    public Map<String, Long> ReserveARoom(@PathVariable String phone, @PathVariable String email){
        if( mapa.containsKey(phone) ){
            Map<String, Long> tmp = new TreeMap<String, Long>();
            tmp.put("Already used or not acceptable format for email",(long)0);
            return tmp;
        }
        User user = userService.createUser(phone,email,roomService.activeRoom, "0:0:0:0:1");
        if(user == null){
            Map<String, Long> tmp = new TreeMap<String, Long>();
            tmp.put("Already used or not acceptable format for email",(long)0);
            return tmp;
        }
        long room = roomService.addUser(user);
        mapa.put(String.format("%s %s %s",user.getEmail(),user.getPhone_number(),user.getIp()),room);
        return mapa;
    }

    @GetMapping("/room/CountPayingCustomersPerGivenRoom/{noRoom}")
    public long CountPayingCustomers(@PathVariable long noRoom){
        Room room = roomService.findRoom(noRoom);
        logger.info(ShowThisText(noRoom, "paying customers ", room.getNoUsers()));
        return room.getNoUsers();
    }


    @GetMapping("/room/CountGuestsPerGivenRoom/{noRoom}")
    public long CountGuests(@PathVariable long noRoom){
        Room room = roomService.findRoom(noRoom);
        logger.info(ShowThisText(noRoom, "guests ", room.getNoGuests()));
        return room.getNoGuests();
    }

    @GetMapping("/room/CountPresentPeoplePerGivenRoom/{noRoom}")
    public long CountPresent(@PathVariable long noRoom){
        Room room = roomService.findRoom(noRoom);
        logger.info(ShowThisText(noRoom, "present people (both paying customers and guests)", room.getNoUsers()+room.getNoGuests()));
        return room.getNoUsers()+room.getNoGuests();
    }

    @GetMapping("/findroom/{phone}")
    public long FindTheRoom(@PathVariable String phone){
        return userService.getRoom(phone);
    }

    public String ShowThisText(long noRoom, String textOfKind, int number){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("You currently have %d %s in the %dth room ", number,textOfKind,noRoom));
        return sb.toString();
    }


}
