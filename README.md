# Embeddable composite primary key

## 1. Create the composite primary key

``` java
@Embeddable
public class PotionIngredientId implements Serializable {

    @Column(name = "potion_id")
    private Long potionId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    public PotionIngredientId() {
    }

    // hashcode and equals are necessary
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
    
    // getters and setters omitted for brevity
}
```

* [What is Serialization?](https://dev.to/njnareshjoshi/what-is-serialization-everything-you-need-to-know-about-java-serialization-explained-with-example-9mj)
* [Java equals() and hashCode() Contracts](https://www.baeldung.com/java-equals-hashcode-contracts)
* [Eclipse : generate Java hashCode and equals methods](https://alvinalexander.com/blog/post/eclipse-ide/eclipse-faq-create-hashcode-equals-methods)
* [IntelliJ IDEA : generate equals() and hashCode() methods](https://www.jetbrains.com/help/idea/generating-code.html#generate-equals-hashcode)

## 2. Embbed the composite primary key

``` java
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

    // getters and setters omitted for brevity
}
```

* [Annotation Type EmbeddedId](https://docs.jboss.org/hibernate/jpa/2.1/api/javax/persistence/EmbeddedId.html)

## 3. Create the Many-to-Many relation

``` java
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

    // getters and setters omitted for brevity
}
```

``` java
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PotionIngredient> potionIngredients = new ArrayList<>();

    public Ingredient() {
    }

    // getters and setters omitted for brevity
}
```

* [Orphan Removal in Relationships](https://docs.oracle.com/cd/E19798-01/821-1841/giqxy/)

## 4. Remove an element from and Many-to-Many with a composite primary key

The *repository* :

``` java
@Repository
public interface PotionIngredientRepository extends JpaRepository<PotionIngredient, Long> {

    @Transactional
    void deleteByPotionIdAndIngredientId(Long potionId, Long ingredientId);
}
```

Do not forget the `@Transactional` annotation, which is necessary for a `deleteBy` query.

How to remove an element with both ids :

``` java
potionIngredientRepository.deleteByPotionIdAndIngredientId(idPotion, idIngredient);
```