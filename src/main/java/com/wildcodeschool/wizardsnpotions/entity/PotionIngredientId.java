package com.wildcodeschool.wizardsnpotions.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PotionIngredientId implements Serializable {

    @Column(name = "potion_id")
    private Long potionId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    public PotionIngredientId() {
    }

    public PotionIngredientId(Long potionId, Long ingredientId) {
        this.potionId = potionId;
        this.ingredientId = ingredientId;
    }

    public Long getPotionId() {
        return potionId;
    }

    public void setPotionId(Long potionId) {
        this.potionId = potionId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PotionIngredientId that = (PotionIngredientId) o;
        return Objects.equals(potionId, that.potionId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(potionId, ingredientId);
    }
}
