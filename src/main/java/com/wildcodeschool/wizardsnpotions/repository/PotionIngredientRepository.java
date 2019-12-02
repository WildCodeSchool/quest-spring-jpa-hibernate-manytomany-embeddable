package com.wildcodeschool.wizardsnpotions.repository;

import com.wildcodeschool.wizardsnpotions.entity.PotionIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PotionIngredientRepository extends JpaRepository<PotionIngredient, Long> {

    @Transactional
    void deleteByPotionIdAndIngredientId(Long potionId, Long ingredientId);
}
