package com.mokasoft.gestresto.services;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.dtos.RoomDto;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Room;
import com.mokasoft.gestresto.mappers.RoomTableMapper;
import com.mokasoft.gestresto.repositories.AppTableRepository;
import com.mokasoft.gestresto.repositories.AppUserRepository;
import com.mokasoft.gestresto.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{
    private RoomRepository roomRepository;
    private RoomTableMapper roomMapper;
    private AppTableRepository appTableRepository;

    private AppUserRepository appUserRepository;
    @Override
    public RoomDto saveRoom(RoomDto roomDto) {
        log.info("saving new Room");
        Room room = roomMapper.fromRoomDto(roomDto);
        Room saveRoom = roomRepository.save(room);
        return roomMapper.fromRoom(saveRoom);
    }

    @Override
    public List<RoomDto> getRooms() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> roomDtos = rooms.stream().map(room -> roomMapper.fromRoom(room))
                .collect(Collectors.toList());
        return roomDtos;
    }

    @Override
    public AppTableDto saveTable(AppTableDto appTableDto) {
        log.info("saving new Table");
        AppTable appTable = roomMapper.fromAppTableDto(appTableDto);
        AppTable saveTable = appTableRepository.save(appTable);
        return roomMapper.fromAppTable(saveTable);
    }

    @Override
    public void affectWaiterToTable(String waiterName, String tableName) {
        AppUser appUser = appUserRepository.findByUsername(waiterName);
        AppTable appTable = appTableRepository.findByTableNumber(tableName);
        appTable.setAppUser(appUser);
    }


    @Override
    public List<AppTable> getUsersTable(AppUser userName) {
        return appTableRepository.findByAppUser(userName);
    }


}
