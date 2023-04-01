package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.dtos.SaleDto;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;

import java.util.List;

public interface AppTableService {
    AppTableDto saveTable(AppTableDto appTableDto);
    AppTableDto updateTable(AppTableDto appTableDto);
    List<AppTable> getTables();
    List<AppTableDto> getTablesByUser(AppUser appUser);

}
