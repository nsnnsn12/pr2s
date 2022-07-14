package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.File;
import com.metacrew.pr2s.entity.Institution;
import com.metacrew.pr2s.entity.Room;
import com.metacrew.pr2s.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoomDto {

    private Long id;

    private Address address;

    private Institution institution;

    private File file;

    private String title;

    private String description;

    private int maximumPersonCount;




}

