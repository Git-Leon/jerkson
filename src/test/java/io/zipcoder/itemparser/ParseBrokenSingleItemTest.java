package io.zipcoder.itemparser;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;
import io.zipcoder.ItemParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParseBrokenSingleItemTest {
    @Test(expected = ItemParseException.class)
    public void test1() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe;price:3.23;type:Food;expiration:1/25/2016##";

        // when
        itemParser.parseSingleItem(valueToParse); // throws exception
    }

    @Test(expected = ItemParseException.class)
    public void test2() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe:eggS;price;type:Food;expiration:1/25/2016##";
        Item expected = new Item("eggs", 1.25, "food", "1/25/2016");

        // when
        Item actual = itemParser.parseSingleItem(valueToParse);

        // then
        assertEquals(expected.toString(), actual.toString());
    }


    @Test(expected = ItemParseException.class)
    public void test3() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe:teleVision;price:323.5;type;expiration:3/25/2019##";
        Item expected = new Item("television", 323.5, "electronics", "3/25/2019");

        // when
        Item actual = itemParser.parseSingleItem(valueToParse);

        // then
        assertEquals(expected.toString(), actual.toString());
    }



    @Test(expected = ItemParseException.class)
    public void test4() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "naMe:teleVision;price:323.5;type:electRoniCs;expiration";
        Item expected = new Item("television", 323.5, "electronics", "3/25/2019");

        // when
        Item actual = itemParser.parseSingleItem(valueToParse);

        // then
        assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ItemParseException.class)
    public void test5() throws ItemParseException {
        // given
        ItemParser itemParser = new ItemParser();
        String valueToParse = "that damn brown fox jumping over the lazy dog again";
        Item expected = new Item("television", 323.5, "electronics", "3/25/2019");

        // when
        Item actual = itemParser.parseSingleItem(valueToParse);

        // then
        assertEquals(expected.toString(), actual.toString());
    }
}
