package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {

	public static final int NON_EXPIRING_SELL_IN = 14;
	public static final int DEFAULT_QUALITY = 3;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int EXPIRING_SELL_IN = -1;

	private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}

	private GildedRose getGildedRoseWithOneItem(int sellInBeforeVariation) {
		Item item = new Item(GildedRoseADefaultItemTest.DEFAULT_ITEM, sellInBeforeVariation, GildedRoseADefaultItemTest.DEFAULT_QUALITY);
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}

	@Test
	public void shouldDecreaseQualityBy1ForNonExpiredItem() {

		GildedRose app = getGildedRoseWithOneItem(NON_EXPIRING_SELL_IN);
		final Item expected = new Item(DEFAULT_ITEM, NON_EXPIRING_SELL_IN - 1, DEFAULT_QUALITY - 1);

		app.updateQuality();

		assertItem(expected, app.items[0]);
	}

	@Test
	public void shouldDecreaseQualityBy2ForExpiredItem() {

		GildedRose app = getGildedRoseWithOneItem(EXPIRING_SELL_IN);
		final Item expected = new Item(DEFAULT_ITEM, EXPIRING_SELL_IN - 1, DEFAULT_QUALITY - 2);

		app.updateQuality();

		assertItem(expected, app.items[0]);
	}
}