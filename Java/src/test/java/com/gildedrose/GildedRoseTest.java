package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("Quality down to zero each time we run the updateQuality function")
    void qualityDecreasesEachTimeWeUpdateQualityDownToZero() {
        assertEquals(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0),
                qualityOverTime(10, 10, 12));
    }

    @Test
    @DisplayName("Quality decreases 2x when the sell date is expired")
    void qualityDecreasesTwiceAsFastWhenSellDateIsExpired() {
        assertEquals(Arrays.asList(20, 19, 18, 17, 16, 15, 13, 11, 9, 7, 5, 3, 1),
                qualityOverTime(5, 20, 12));
    }

    @Test
    @DisplayName("Aged Brie actually increases in Quality the older it gets")
    void agedBrieIncreaseInQualityOverTime() {
        List<Integer> agedBrieQualityOverTime = productQualityOverTime("Aged Brie", 5, 20, 12);
        assertEquals(Arrays.asList(20, 21, 22, 23, 24, 25, 27, 29, 31, 33, 35, 37, 39), agedBrieQualityOverTime);
    }

    @Test
    @DisplayName("The Quality of an item cannot increase beyond 50")
    void qualityCannotBeMoreThan50() {
        List<Integer> agedBrieQualityOverTime = productQualityOverTime("Aged Brie", 5, 45, 12);
        assertEquals(Arrays.asList(45, 46, 47, 48, 49, 50, 50, 50, 50, 50, 50, 50, 50), agedBrieQualityOverTime);
    }

    @Test
    @DisplayName("A product can start with a quality over 50")
    void qualityStartingOver50() {
        List<Integer> startAt52 = qualityOverTime(5, 52, 3);
        assertEquals(Arrays.asList(52, 51, 50, 49), startAt52);
    }

    private List<Integer> qualityOverTime(int sellIn, int quality, int times) {
        return productQualityOverTime("foo", sellIn, quality, times);
    }

    private List<Integer> productQualityOverTime(String productName, int sellIn, int quality, int times) {
        Item[] items = new Item[]{new Item(productName, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        ArrayList<Integer> results = new ArrayList<>();

        results.add(app.items[0].quality);
        for (int i = 0; i < times; i++) {
            app.updateQuality();
            results.add(app.items[0].quality);
        }

        return results;
    }

}
