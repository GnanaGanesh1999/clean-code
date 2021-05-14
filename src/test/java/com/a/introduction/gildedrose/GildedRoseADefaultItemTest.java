package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

	public static final int NON_EXPIRING_SELL_IN = 14;
	public static final int DEFAULT_QUALITY = 3;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";


	private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}

	private GildedRose getGildedRoseWithOneItem(String itemName, int sellInBeforeVariation, int qualityBeforeVariation) {
		Item item = new Item(itemName, sellInBeforeVariation, qualityBeforeVariation);
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
	public void testUpdateQualityForExpiredItem() {

		final int sellInBeforeVariation = -1;
		final int qualityBeforeVariation = 3;
		final String expectedItemName = "DEFAULT_ITEM";
		final int expectedSellIn = -2;
		final int expectedQuality = 1;

		GildedRose app = getGildedRoseWithOneItem(DEFAULT_ITEM, sellInBeforeVariation, qualityBeforeVariation);
		app.updateQuality();

		final String actualItemName = app.items[0].name;
		final int actualSellIn = app.items[0].sellIn;
		final int actualQuality = app.items[0].quality;
		assertEquals(expectedItemName, actualItemName);
		assertEquals(expectedSellIn, actualSellIn);
		assertEquals(expectedQuality, actualQuality);
	}
}