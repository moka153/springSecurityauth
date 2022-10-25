package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.services.RoomService;
import com.mokasoft.gestresto.utils.WaiterTableForm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RoomController {
    private RoomService roomService;

    @PostMapping(path = ("/affectTableToUser"))
    public void affectWaiterToUser(@RequestBody WaiterTableForm waiterTableForm){
        roomService.affectWaiterToTable(waiterTableForm.getWaiterName(),waiterTableForm.getTabelName());
    }
}
