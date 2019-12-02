package com.wildcodeschool.wizardsnpotions.entity;

import javax.persistence.*;

@Entity
public class PotionIngredient {

    @EmbeddedId
    private PotionIngredientId id;

    @ManyToOne
    @MapsId("potion_id")
    @JoinColumn(name = "potion_id")
    private Potion potion;

    @ManyToOne
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Integer quantity;

    public PotionIngredient() {
    }

    public PotionIngredient(Potion potion, Ingredient ingredient, Integer quantity) {
        this.id = new PotionIngredientId(potion.getId(), ingredient.getId());
        this.potion = potion;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public PotionIngredientId getId() {
        return id;
    }

    public void setId(PotionIngredientId id) {
        this.id = id;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
