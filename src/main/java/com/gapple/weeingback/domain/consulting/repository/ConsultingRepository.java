package com.gapple.weeingback.domain.consulting.repository;

import com.gapple.weeingback.domain.consulting.entity.Consulting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConsultingRepository extends JpaRepository<Consulting, Long> {

    void removeById(UUID consultingId);
}
