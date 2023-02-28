package com.instantduo.codeswitching.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayDataRepository extends JpaRepository<PlayData, Long> {
    List<PlayData> findAllByUser(User user);
}
