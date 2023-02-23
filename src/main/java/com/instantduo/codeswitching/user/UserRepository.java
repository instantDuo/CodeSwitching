package com.instantduo.codeswitching.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByLoginId(String loginId);
}
