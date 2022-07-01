package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.Address;
import com.metacrew.pr2s.entity.File;
import com.metacrew.pr2s.entity.base.BaseEntity;
import com.metacrew.pr2s.entity.embedded.Period;
import com.metacrew.pr2s.entity.enums.WorkDay;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
public class InstitutionDto extends BaseEntity {
    private Long id;

    private String name;

    private Boolean isApprovedRegistration;

    private String telNumber;

    private Address address;

    private WorkDay workday;

    private File file;

    private Period period;
}
