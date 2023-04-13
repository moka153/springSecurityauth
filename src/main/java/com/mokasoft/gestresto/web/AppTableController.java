package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.AppTableRequest;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;
import com.mokasoft.gestresto.responses.ResponseHandler;
import com.mokasoft.gestresto.services.AppTableService;
import com.mokasoft.gestresto.utils.WaiterTableForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class AppTableController {
    private final AppTableService appTableService;

    @PostMapping
    public ResponseEntity<Object> saveTable(@Valid @RequestBody AppTableRequest appTableRequest){
        return ResponseHandler.responseBuilder("Table created", HttpStatus.CREATED,
                appTableService.saveTable(appTableRequest));
    }

    @PutMapping("/{tableId}")
    public ResponseEntity<Object> updateTable(@Valid @RequestBody AppTableRequest appTableRequest,
                                              @PathVariable Long tableId){
        return ResponseHandler.responseBuilder("Table updated", HttpStatus.OK,
                appTableService.updateTable(appTableRequest,tableId));
    }

    @DeleteMapping("/{tableId}")
    public void deleteTable(@PathVariable Long tableId){
        appTableService.deleteTable(tableId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAllTablesByUser(@PathVariable AppUser userId){
        return ResponseHandler.responseBuilder("All tables by user",HttpStatus.FOUND,
                appTableService.getAllTablesByUser(userId));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<Object> getAllTablesByRoom(@PathVariable Room roomId){
        return ResponseHandler.responseBuilder("All tables by room",HttpStatus.FOUND,
                appTableService.getAllTables(roomId));
    }

    @PostMapping("/userTable")
    public void affectTableToUser(@RequestBody WaiterTableForm waiterTableForm){
        appTableService.affectTableToUser(waiterTableForm.getUserName(), waiterTableForm.getTableName());
    }
}
