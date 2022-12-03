package com.updatetech.tripDodol.model.packageModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class MealItem implements Serializable {
    private static final Long serialVersionUID = 132L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mealItemName;
    @Nullable
    private String mealItemPrice;
    private boolean status = false;

    @ManyToOne
    @JoinColumn(name = "package_meal_id")
    @JsonIgnore
    private PackageMeal packageMeal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMealItemName() {
        return mealItemName;
    }

    public void setMealItemName(String mealItemName) {
        this.mealItemName = mealItemName;
    }

    @Nullable
    public String getMealItemPrice() {
        return mealItemPrice;
    }

    public void setMealItemPrice(@Nullable String mealItemPrice) {
        this.mealItemPrice = mealItemPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PackageMeal getPackageMeal() {
        return packageMeal;
    }

    public void setPackageMeal(PackageMeal packageMeal) {
        this.packageMeal = packageMeal;
    }
}
