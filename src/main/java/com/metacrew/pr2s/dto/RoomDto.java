package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.Institution;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private Long id;
    private Address address;
    private Institution institution;
    private String title;
    private String description;
    private int maximumPersonCount;

}
