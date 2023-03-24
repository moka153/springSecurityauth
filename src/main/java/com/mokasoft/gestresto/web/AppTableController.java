package com.mokasoft.gestresto.web;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.services.AppTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class AppTableController {
    private final AppTableService appTableService;

    @PostMapping
    public AppTableDto saveTable(@RequestBody AppTableDto appTableDto){
        return appTableService.saveTable(appTableDto);
    }

    @GetMapping
    public List<AppTableDto> getAllTables(){
        return appTableService.getTables();
    }

    @GetMapping("/{userId}")
    public List<AppTableDto> getAllTablesByUSer(@PathVariable(name = "userId")AppUser appUser){
        return appTableService.getTablesByUser(appUser);
    }

    @PutMapping
    public AppTableDto updateTable(@RequestBody AppTableDto appTableDto){
        return appTableService.updateTable(appTableDto);
    }
}
