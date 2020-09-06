package com.video.video.repository;

import com.video.video.model.Cassette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassetteRepository extends JpaRepository<Cassette, Long> {
}
