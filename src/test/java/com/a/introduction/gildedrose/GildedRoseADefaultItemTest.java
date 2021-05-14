package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

    public static final int NON_EXPIRING_SELL_IN = 14;
    public static final int DEFAULT_QUALITY = 3;
    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    public static final int EXPIRING_SELL_IN = -1;
    public static final String AGED_BRIE = "Aged Brie";

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
    public void testUpdateQualityAgedBrie2() {
        Item item = new Item("Aged Brie", -1, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);
    }

    @Test
    public void testUpdateQualityAgedBrie3() {
        Item item = new Item("Aged Brie", 4, 50);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }
}