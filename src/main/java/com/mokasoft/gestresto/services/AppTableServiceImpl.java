package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Sale;
import com.mokasoft.gestresto.mappers.RoomTableMapper;
import com.mokasoft.gestresto.repositories.AppTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppTableServiceImpl implements AppTableService {
    private final AppTableRepository appTableRepository;
    private final RoomTableMapper roomTableMapper;
    @Override
    public AppTableDto saveTable(AppTableDto appTableDto) {
        AppTable appTable =  roomTableMapper.fromAppTableDto(appTableDto);
        AppTable savedTable = appTableRepository.save(appTable);
        return roomTableMapper.fromAppTable(savedTable);
    }

    @Override
    public AppTableDto updateTable(AppTableDto appTableDto) {
        AppTable appTable =  roomTableMapper.fromAppTableDto(appTableDto);
        //appTable.setTableId(appTableDto.getTableId());
        Sale s = new Sale();
        s.setSaleId(appTableDto.getSaleId());
        appTable.setSale(s);
        AppTable savedTable = appTableRepository.save(appTable);
        return roomTableMapper.fromAppTable(savedTable);
    }

    @Override
    public List<AppTable> getTables() {
        List<AppTable> appTables = appTableRepository.findAll();
        //List<AppTableDto> appTableDtos = appTables.stream()
                //.map(appTable -> roomTableMapper.fromAppTable(appTable))
                //.collect(Collectors.toList());
        return appTables;
    }

    @Override
    public List<AppTableDto> getTablesByUser(AppUser appUser) {
        List<AppTable> appTables = appTableRepository.findByAppUser(appUser);
        /*List<AppTableDto> appTableDtos = appTables.stream()
                .map(appTable -> roomTableMapper.fromAppTable(appTable))
                .collect(Collectors.toList());*/
        List<AppTableDto> appTableDtos = appTables.stream()
                .map(appTable -> appTableToAppTableDto(appTable))
                .collect(Collectors.toList());
        return appTableDtos;
    }

    public AppTableDto appTableToAppTableDto(AppTable appTable){
        AppTableDto appTableDto = new AppTableDto();
        appTableDto.setTableId(appTable.getTableId());
        appTableDto.setTableNumber(appTable.getTableNumber());
        appTableDto.setCustomerNumber(appTable.getCustomerNumber());
        appTableDto.setAvailable(appTable.isAvailable());
        //Long saleId = appTable.getSale().getSaleId();
        Sale s = appTable.getSale();
        if(s != null){
            appTableDto.setSaleId(s.getSaleId());
        }

        //System.out.println(s.getSaleId());

        return appTableDto;
    }
}
