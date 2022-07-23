package com.metacrew.pr2s.service.roomservice;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.RoomDto;
import com.metacrew.pr2s.entity.File;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.roomrepository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class RoomManagerServiceTest {
}