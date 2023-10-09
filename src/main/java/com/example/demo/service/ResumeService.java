package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import com.example.demo.entities.Resume;
import com.example.demo.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public Page<ResumeDto> getAllResumes(int start, int end) {
        Sort sort = Sort.by(Sort.Order.desc("timeOfResume"));
        Pageable pageable = PageRequest.of(start, end, sort);
        Page<Resume> resumes = resumeRepository.findAll(pageable);
        Page<ResumeDto> resumeDtoPages = resumes.map(resume -> {
            return ResumeDto.builder()
                    .id(resume.getId())
                    .specialistId(resume.getSpecialist().getId())
                    .timeOfResume(resume.getTimeOfResume())
                    .resumeDescription(resume.getResumeDescription())
                    .build();
        });
        return resumeDtoPages;
    }
}
