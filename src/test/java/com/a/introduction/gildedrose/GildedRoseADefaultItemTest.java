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
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SELL_IN_OVER_10_DAYS = 15;

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


    @Test
    public void shouldIncreaseQualityBy1ForLongestBackstagePasses() {
        final GildedRose app = getGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, SELL_IN_OVER_10_DAYS, DEFAULT_QUALITY);
        Item expected = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, SELL_IN_OVER_10_DAYS - 1, DEFAULT_QUALITY + 1);

        app.updateQuality();

        assertItem(expected, app.items[0]);
    }

    @Test
    public void testUpdateQualityBackstagePasses2() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 7, 3);
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert",
                app.items[0].name);
        assertEquals(6, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);
    }

    @Test
    public void testUpdateQualityBackstagePasses3() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 3);
        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert",
                app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

}