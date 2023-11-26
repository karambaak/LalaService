package com.example.demo.service;

import com.example.demo.dto.ContactsDto;
import com.example.demo.dto.SpecialistShortInfoDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Resume;
import com.example.demo.entities.Specialist;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final SpecialistRepository specialistRepository;
    private final ResumeRepository resumeRepository;
    private final CategoryRepository categoryRepository;
    private final SpecialistService specialistService;

    public List<ContactsDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<Resume> resumes = resumeRepository.findAll();

        List<ContactsDto> list = new ArrayList<>();
        for (Category c : categories) {
            String cc = c.getCategoryName();
            Set<SpecialistShortInfoDto> ss = new HashSet<>();
            Set<Long> specialistIds = new HashSet<>();
            for (Resume r : resumes) {
                if(specialistService.isFullAuthority(r.getSpecialist())) {
                    if (r.getCategory().getCategoryName().equalsIgnoreCase(c.getCategoryName()) && !specialistIds.contains(r.getSpecialist().getId())) {
                        ss.add(SpecialistShortInfoDto.builder()
                                .id(r.getSpecialist().getId())
                                .name(specialistService.findSpecialistName(r.getSpecialist()))
                                .build());
                        specialistIds.add(r.getSpecialist().getId());
                    }
                }
            }
            list.add(ContactsDto.builder()
                    .category(cc)
                    .specialists(new ArrayList<>(ss))
                    .build());
        }
        return list;
    }

    public List<SpecialistShortInfoDto> getSearchResultItems() {
        List<Resume> resumes = resumeRepository.findAll();
        List<Specialist> specialists = specialistRepository.findAll();
        List<SpecialistShortInfoDto> list = new ArrayList<>();
        for (Resume r :
                resumes) {
            if(specialistService.isFullAuthority(r.getSpecialist())) {
                list.add(SpecialistShortInfoDto.builder()
                        .id(r.getSpecialist().getId())
                        .name(r.getName())
                        .build());
                list.add(SpecialistShortInfoDto.builder()
                        .id(r.getSpecialist().getId())
                        .name(r.getResumeDescription())
                        .build());
            }
        }
        for (Specialist s :
                specialists) {
            if(specialistService.isFullAuthority(s)) {
                String name = specialistService.findSpecialistName(s);
                list.add(SpecialistShortInfoDto.builder()
                        .id(s.getId())
                        .name(name)
                        .build());
            }
        }
        return list;
    }
}
