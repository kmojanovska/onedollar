package com.onedollar.service;

import com.onedollar.model.Guest;
import com.onedollar.model.User;
import com.onedollar.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String phone_number, String email, long room_id, String ip){
        Pattern p = Pattern.compile("^\\w+@\\w+\\..{2,3}(.{2,3})?$");
        Matcher m = p.matcher(email);
        if(m.matches()){
            return userRepository.save(new User(phone_number, email, room_id, ip));
        }
        else return null;
    }

    public long getRoom(String phone){
        return userRepository.findById(phone).get().getRoom_id();
    }
}
