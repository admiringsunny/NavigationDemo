package com.learn.navigationdemo

class ProductPriceCalculator {

    fun calculateDiscountedPrice(
        price: Double,
        discountPercentage: Double
    ): Double {

        require(price >= 0) {
            "Price cannot be negative"
        }

        require(discountPercentage in 0.0..100.0) {
            "Discount must be between 0 and 100"
        }

        val discountAmount =
            price * discountPercentage / 100

        return price - discountAmount
    }
}
