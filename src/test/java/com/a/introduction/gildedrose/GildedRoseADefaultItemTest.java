package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

    public static final int NON_EXPIRING_SELL_IN = 14;
    public static final int DEFAULT_QUALITY = 3;
    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int EXPIRING_SELL_IN = -1;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int QUALITY_WITH_VALUE_FIFTY = 50;

    private void assertItem(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

    private GildedRose getGildedRoseWithOneItem(String itemName, int sellIn, int quality) {
        Item item = new Item(itemName, sellIn, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

    @Test
    public void shouldDecreaseQualityBy1ForNonExpiredItem() {

        GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, NON_EXPIRING_SELL_IN, DEFAULT_QUALITY);
        final Item expected = new Item(DEFAULT_ITEM, NON_EXPIRING_SELL_IN - 1, DEFAULT_QUALITY - 1);

        app.updateQuality();

        assertItem(expected, app.items[0]);
    }

    @Test
    public void shouldDecreaseQualityBy2ForExpiredItem() {

        GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRING_SELL_IN, DEFAULT_QUALITY);
        final Item expected = new Item(DEFAULT_ITEM, EXPIRING_SELL_IN - 1, DEFAULT_QUALITY - 2);

        app.updateQuality();

        assertItem(expected, app.items[0]);
    }


    @Test
    public void shouldIncreaseQualityBy1ForNonExpiredAgedBrie() {
        final GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, NON_EXPIRING_SELL_IN, DEFAULT_QUALITY);
        Item expected = new Item(AGED_BRIE, NON_EXPIRING_SELL_IN - 1, DEFAULT_QUALITY + 1);

        app.updateQuality();

        assertItem(expected, app.items[0]);
    }

    @Test
    public void shouldIncreaseQualityBy2ForNonExpiredAgedBrie() {
        final GildedRose app = getGildedRoseWithOneItem(AGED_BRIE, EXPIRING_SELL_IN, DEFAULT_QUALITY);
        Item expected = new Item(AGED_BRIE, EXPIRING_SELL_IN - 1, DEFAULT_QUALITY + 2);

        app.updateQuality();

        assertItem(expected, app.items[0]);
    }

    @Test
    public void shouldNotChangeQualityForNonExpiredAgedBrieWithQualityOverFifty() {
        final GildedRose app = getGildedRoseWithOneItem("Aged Brie", NON_EXPIRING_SELL_IN, QUALITY_WITH_VALUE_FIFTY);
        Item expected = new Item(AGED_BRIE, NON_EXPIRING_SELL_IN - 1, QUALITY_WITH_VALUE_FIFTY);

        app.updateQuality();

        assertItem(expected, app.items[0]);
    }
}