package com.wildcodeschool.wizardsnpotions.repository;

import com.wildcodeschool.wizardsnpotions.entity.Effect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EffectRepository extends JpaRepository<Effect, Long> {
}
