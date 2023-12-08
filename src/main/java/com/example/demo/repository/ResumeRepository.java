package com.example.demo.repository;

import com.example.demo.entities.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query(nativeQuery = true, value = """
            select *
            from resumes
            where specialist_id in (SELECT specialists.id
                                    from specialists
                                    where user_id in (select users.id
                                                      FROM users
                                                      WHERE enabled = true));
            """)
    Page<Resume> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = """
            select *
            from resumes
            where specialist_id = :id and specialist_id in (SELECT specialists.id
                                    from specialists
                                    where user_id in (select users.id
                                                      FROM users
                                                      WHERE enabled = true));
            """)
    List<Resume> findAllBySpecialist_Id(Long id);

    @Query(nativeQuery = true, value = """
            select exists(
            select *
            from resumes
            where specialist_id = :id and id = :resumeid and specialist_id in (SELECT specialists.id
                                    from specialists
                                    where user_id in (select users.id
                                                      FROM users
                                                      WHERE enabled = true)));
            """)
    Boolean findResumeBySpecialistIdAndId(long specialistId,long resumeId);

    @Query(nativeQuery = true, value = """
            select *
            from resumes
            where category_id = :categoryId and specialist_id in (SELECT specialists.id
                                    from specialists
                                    where user_id in (select users.id
                                                      FROM users
                                                      WHERE enabled = true));
            """)
    List<Resume> findByCategoryId(Long categoryId);

}
