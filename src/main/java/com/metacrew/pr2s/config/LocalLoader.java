package com.metacrew.pr2s.config;

import com.metacrew.pr2s.dto.AddressDto;
import com.metacrew.pr2s.dto.InstitutionCreateDto;
import com.metacrew.pr2s.dto.JoinMemberDto;
import com.metacrew.pr2s.dto.WorkdaysDto;
import com.metacrew.pr2s.entity.*;
import com.metacrew.pr2s.entity.enums.FileType;
import com.metacrew.pr2s.repository.AddressRepository;
import com.metacrew.pr2s.repository.FileInfoRepository;
import com.metacrew.pr2s.repository.institutionrepository.InstitutionRepository;
import com.metacrew.pr2s.repository.joininforepository.JoinInfoRepository;
import com.metacrew.pr2s.repository.memberrepository.MemberRepository;
import com.metacrew.pr2s.repository.workdaysrepository.WorkdaysRepository;
import com.metacrew.pr2s.service.storageservice.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalLoader implements CommandLineRunner {
    private final StorageService storageService;
    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
