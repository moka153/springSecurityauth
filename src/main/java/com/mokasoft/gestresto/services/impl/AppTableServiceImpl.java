package com.mokasoft.gestresto.services.impl;

import com.mokasoft.gestresto.dtos.AppTableRequest;
import com.mokasoft.gestresto.dtos.AppTableResponse;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;
import com.mokasoft.gestresto.exceptions.ConflictException;
import com.mokasoft.gestresto.exceptions.NotFoundException;
import com.mokasoft.gestresto.mappers.AppTableMapper;
import com.mokasoft.gestresto.repositories.AppTableRepository;
import com.mokasoft.gestresto.repositories.AppUserRepository;
import com.mokasoft.gestresto.repositories.RoomRepository;
import com.mokasoft.gestresto.services.AppTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AppTableServiceImpl implements AppTableService {
    private final AppTableRepository appTableRepository;
    private final AppTableMapper appTableMapper;
    private final RoomRepository roomRepository;
    private final AppUserRepository appUserRepository;
    @Override
    public AppTableResponse saveTable(AppTableRequest appTableRequest) {
        if(!roomRepository.findById(appTableRequest.getRoomId()).isPresent()){
            throw new NotFoundException("Room not found");
        }
        try{
            AppTable appTable = appTableMapper.appTableRequestToAppTable(appTableRequest);
            AppTable savedAppTable = appTableRepository.save(appTable);
            return appTableMapper.appTableToAppTableResponse(savedAppTable);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Table name already exists");
        }
    }

    @Override
    public AppTableResponse updateTable(AppTableRequest appTableRequest,Long tableId) {
        if(!roomRepository.findById(appTableRequest.getRoomId()).isPresent()){
            throw new NotFoundException("Room not found");
        }
        try{
            AppTable appTable = appTableMapper.appTableRequestToAppTable(appTableRequest);
            appTable.setTableId(tableId);
            AppTable savedAppTable = appTableRepository.save(appTable);
            return appTableMapper.appTableToAppTableResponse(savedAppTable);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Table name already exists");
        }
    }

    @Override
    public void deleteTable(Long tableId) {
        if(!appTableRepository.findById(tableId).isPresent()){
            throw new NotFoundException("Table with id: "+tableId+" does not exists");
        }
        try{
            appTableRepository.deleteById(tableId);
        }catch (DataIntegrityViolationException ex){
            throw new ConflictException("Table with id: "+tableId+" is already used");
        }
    }

    @Override
    public List<AppTableResponse> getAllTables(Room room) {
        if(!roomRepository.findById(room.getRoomId()).isPresent()){
            throw new NotFoundException("Room not found");
        }
        List<AppTable> appTables = appTableRepository.findByRoom(room);
        List<AppTableResponse> appTableResponses =appTables.stream()
                .map(appTable -> appTableMapper.appTableToAppTableResponse(appTable))
                .collect(Collectors.toList());
        return appTableResponses;
    }

    @Override
    public AppTableResponse getTable(Long tableId) {
        AppTable table = appTableRepository.findById(tableId).get();
        AppTableResponse tableResponse = appTableMapper.appTableToAppTableResponse(table);
        return tableResponse;
    }

    @Override
    public List<AppTableResponse> getAllTablesByUser(AppUser appUser) {
        // revoir la gestion des exceptions
        if(appUserRepository.findById(appUser.getId()) == null){
            throw new NotFoundException("User not found");
        }
        List<AppTable> appTables = appTableRepository.findByAppUser(appUser);
        List<AppTableResponse> appTableResponses =appTables.stream()
                .map(appTable -> appTableMapper.appTableToAppTableResponse(appTable))
                .collect(Collectors.toList());
        return appTableResponses;
    }

    @Override
    public void affectTableToUser(String userName, String tableName) {
        if(appUserRepository.findByUsername(userName) == null){
            throw new NotFoundException("user not found");
        }
        if(appTableRepository.findByTableNumber(tableName) == null){
            throw new NotFoundException("table not found");
        }
        AppUser appUser = appUserRepository.findByUsername(userName);
        AppTable appTable = appTableRepository.findByTableNumber(tableName);
        appTable.setAppUser(appUser);
    }
}
