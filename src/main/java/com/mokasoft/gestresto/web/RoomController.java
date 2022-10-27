package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.repositories.AppTableRepository;
import com.mokasoft.gestresto.repositories.AppUserRepository;
import com.mokasoft.gestresto.services.RoomService;
import com.mokasoft.gestresto.utils.WaiterTableForm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoomController {
    private RoomService roomService;

    private AppTableRepository appTableRepository;
    private AppUserRepository appUserRepository;
    @PostMapping(path = ("/affectTableToUser"))
    public void affectWaiterToUser(@RequestBody WaiterTableForm waiterTableForm){
        roomService.affectWaiterToTable(waiterTableForm.getWaiterName(),waiterTableForm.getTabelName());
    }
    @GetMapping("/usersTable/{id}")
    public List<AppTable> findByWaiter(@PathVariable Long id){
        AppUser appUser = appUserRepository.findById(id).orElse(null);

        return roomService.getUsersTable(appUser);
    }


}
