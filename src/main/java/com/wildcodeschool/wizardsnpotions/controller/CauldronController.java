package com.wildcodeschool.wizardsnpotions.controller;

import com.wildcodeschool.wizardsnpotions.entity.*;
import com.wildcodeschool.wizardsnpotions.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CauldronController {

    @Autowired
    PotionRepository potionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EffectRepository effectRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    PotionIngredientRepository potionIngredientRepository;

    @GetMapping("/")
    public String init() {

        // remove all categories and potions
        potionRepository.deleteAll();
        categoryRepository.deleteAll();
        effectRepository.deleteAll();
        ingredientRepository.deleteAll();
        potionIngredientRepository.deleteAll();

        Category category = categoryRepository.save(new Category("Random"));
        Potion potion = potionRepository.save(new Potion("A weird potion", 1, category));

        // create to effect
        Effect shrink = effectRepository.save(new Effect("You shrink to half your size for 24 hours"));
        Effect green = effectRepository.save(new Effect("Your skin turns green"));

        // add the effects to the potion
        potion.getEffects().add(shrink);
        potion.getEffects().add(green);

        // create two ingredients
        Ingredient sphinx = ingredientRepository.save(new Ingredient("Sphinx's Blood"));
        Ingredient kobold = ingredientRepository.save(new Ingredient("Kobold Gland"));

        // add two ingredients to the potion
        potion.getPotionIngredients().add(new PotionIngredient(potion, sphinx, 12));
        potion.getPotionIngredients().add(new PotionIngredient(potion, kobold, 4));
        potionRepository.save(potion);

        return "redirect:/potion?idPotion=" + potion.getId();
    }

    @GetMapping("/potion")
    public String getPotions(Model out,
                             @RequestParam(required = false) Long idPotion) {

        if (idPotion == null) {
            return "redirect:/";
        }

        Optional<Potion> optionalPotion = potionRepository.findById(idPotion);
        if (optionalPotion.isPresent()) {
            Potion potion = optionalPotion.get();

            out.addAttribute("potion", potion);
            out.addAttribute("effects", potion.getEffects());
            out.addAttribute("potionIngredients", potion.getPotionIngredients());
        }

        return "potion";
    }

    @GetMapping("/potion/effect/remove")
    public String removeEffectFromPotion(@RequestParam(required = false) Long idPotion,
                                         @RequestParam(required = false) Long idEffect) {

        if (idPotion == null || idEffect != null) {
            Optional<Potion> optionalPotion = potionRepository.findById(idPotion);
            Potion potion = null;
            if (optionalPotion.isPresent()) {
                potion = optionalPotion.get();
            }
            Optional<Effect> optionalEffect = effectRepository.findById(idEffect);
            Effect effect = null;
            if (optionalEffect.isPresent()) {
                effect = optionalEffect.get();
            }
            if (potion != null && effect != null) {
                potion.getEffects().remove(effect);
                potionRepository.save(potion);
            }
        }

        return "redirect:/potion?idPotion=" + idPotion;
    }

    @GetMapping("/potion/ingredient/remove")
    public String removeIngredientFromPotion(@RequestParam(required = false) Long idPotion,
                                             @RequestParam(required = false) Long idIngredient) {

        if (idPotion != null && idIngredient != null) {
            potionIngredientRepository.deleteByPotionIdAndIngredientId(idPotion, idIngredient);
        }

        return "redirect:/potion?idPotion=" + idPotion;
    }
}
