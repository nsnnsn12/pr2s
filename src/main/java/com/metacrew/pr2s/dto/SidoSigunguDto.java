package com.metacrew.pr2s.dto;

import com.metacrew.pr2s.entity.enums.SidoSigungu;
import lombok.Getter;

@Getter
public class SidoSigunguDto {
    String sidoNm;
    String[] sigunguNm;
    String name;
    public SidoSigunguDto(SidoSigungu value){
        this.sidoNm = value.getSidoNm();
        this.sigunguNm = value.getSigunguNm();
        this.name = value.name();
    }
}
