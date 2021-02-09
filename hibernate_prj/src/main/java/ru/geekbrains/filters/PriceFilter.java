package ru.geekbrains.filters;

import javax.persistence.criteria.CriteriaBuilder;

public class PriceFilter {

    private Integer minPrice;

    private Integer maxPrice;

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
