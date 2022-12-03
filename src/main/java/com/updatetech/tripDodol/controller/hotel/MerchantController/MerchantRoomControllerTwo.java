package com.updatetech.tripDodol.controller.hotel.MerchantController;


import com.updatetech.tripDodol.model.Room;
import com.updatetech.tripDodol.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantRoomControllerTwo {

    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/find/rooms/{roomCategoryId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Room> getRoomsByRoomCategoryId(@PathVariable("roomCategoryId") Long roomCategoryId){
        return roomService.findRoomByRoomCategoryId(roomCategoryId);
    }
}
