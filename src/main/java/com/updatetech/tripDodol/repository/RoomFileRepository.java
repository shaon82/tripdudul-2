package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.RoomFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomFileRepository extends JpaRepository<RoomFile, Long> {
        List<RoomFile>findFilesByRoomId(Long roomId);

    RoomFile findFileById(Long id);
}
