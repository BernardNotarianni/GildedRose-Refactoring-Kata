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

    private List<Integer> qualityOverTime(int sellIn, int quality, int times) {
        Item[] items = new Item[]{new Item("foo", sellIn, quality)};
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
