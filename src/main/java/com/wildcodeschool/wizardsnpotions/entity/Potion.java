package com.wildcodeschool.wizardsnpotions.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Potion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer power;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(name = "potion_effect",
            joinColumns = @JoinColumn(name = "potion_id"),
            inverseJoinColumns = @JoinColumn(name = "effect_id"))
    private List<Effect> effects = new ArrayList<>();

    @OneToMany(mappedBy = "potion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PotionIngredient> potionIngredients = new ArrayList<>();

    public Potion() {
    }

    public Potion(String name, Integer power, Category category) {
        this.name = name;
        this.power = power;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<PotionIngredient> getPotionIngredients() {
        return potionIngredients;
    }

    public void setPotionIngredients(List<PotionIngredient> potionIngredients) {
        this.potionIngredients = potionIngredients;
    }
}
