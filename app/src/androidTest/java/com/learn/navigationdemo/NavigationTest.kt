package com.learn.navigationdemo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeRule =
        createAndroidComposeRule<MainActivity>()

    // Profile navigation and name
    @Test
    fun clickingProfileButton_opensProfileScreen() {
        // step 1 : verify HomeScreen is displayed
        composeRule
            .onNodeWithText("Navigation 3 Demo")
            .assertIsDisplayed()

        // step 2 : Find and click Profile button
        composeRule
            .onNodeWithText("Open Profile")
            .performClick()

        // Step 3: Verify that the Profile screen is displayed.
        composeRule
            .onNodeWithText("Profile Screen")
            .assertIsDisplayed()

        // Step 4: Verify that the correct name is displayed.
        composeRule
            .onNodeWithText("Welcome Sunny!")
            .assertIsDisplayed()
    }

    // Product navigation and product data
    @Test
    fun clickingProductButton_opensProductDetails() {

        // Click the Product button.
        composeRule
            .onNodeWithText("Open Product Details")
            .performClick()

        // Verify the Product screen.
        composeRule
            .onNodeWithText("Product Details")
            .assertIsDisplayed()

        // Verify the product information.
        composeRule
            .onNodeWithText("Product ID: 101")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Product Name: Lenovo Laptop")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Product Category: Electronics")
            .assertIsDisplayed()
    }

    // Settings navigation and screen content
    @Test
    fun clickingSettingsButton_opensSettingsScreen() {
        composeRule
            .onNodeWithText("Open Settings")
            .performClick()
        composeRule
            .onNodeWithText("Settings Screen")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("Press Back to return to Home")
            .assertIsDisplayed()
    }
}