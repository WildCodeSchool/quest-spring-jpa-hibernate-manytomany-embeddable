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

    @OneToMany(mappedBy = "potion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PotionIngredient> potionIngredients = new ArrayList<>();

    public Potion() {
    }

    public Potion(String name, Integer power) {
        this.name = name;
        this.power = power;
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

    public List<PotionIngredient> getPotionIngredients() {
        return potionIngredients;
    }

    public void setPotionIngredients(List<PotionIngredient> potionIngredients) {
        this.potionIngredients = potionIngredients;
    }
}
