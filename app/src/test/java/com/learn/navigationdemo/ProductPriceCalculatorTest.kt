package com.learn.navigationdemo

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class ProductPriceCalculatorTest {
    private lateinit var calculator: ProductPriceCalculator

    @Before
    fun setUp() {
        calculator = ProductPriceCalculator()
    }

    @Test
    fun calculateDiscountedPrice_withTenPercentDiscount_returnsCorrectPrice() {
        val result = calculator.calculateDiscountedPrice(
            price = 40000.0,
            discountPercentage = 10.0
        )
        assertEquals(36000.0, result, 0.001)
    }

    @Test
    fun calculateDiscountedPrice_withZeroDiscount_returnsOriginalPrice() {
        val result = calculator.calculateDiscountedPrice(
            price = 40000.0,
            discountPercentage = 0.0
        )
        assertEquals(40000.0, result, 0.001)
    }

    @Test
    fun calculateDiscountedPrice_withHundredPercentDiscount_returnsZero() {
        val result = calculator.calculateDiscountedPrice(
            price = 40000.0,
            discountPercentage = 100.0
        )
        assertEquals(0.0, result, 0.001)
    }

    @Test
    fun calculateDiscountedPrice_withNegativePrice_throwsException() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.calculateDiscountedPrice(
                price = -1000.0,
                discountPercentage = 10.0
            )
        }
    }

    @Test
    fun `Discounted Price with Discount Above 100 percent should throws Exception`() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.calculateDiscountedPrice(
                price = 40000.0,
                discountPercentage = 110.0
            )
        }
    }
}
