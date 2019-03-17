package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) {
        List<Item> result = new ArrayList<>();
        List<String> itemList = splitStringWithRegexPattern("##", valueToParse.toLowerCase());
        for (int i = 0; i < itemList.size(); i++) {
            String itemString = itemList.get(i);
            try {
                Item item = parseSingleItem(itemString);
                result.add(item);
            } catch (ItemParseException e) {
                continue;
            }
        }
        return result;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        try {
            return getItem(singleItem);
        } catch(Throwable t) {
            throw new ItemParseException();
        }
    }

    private Item getItem(String singleItem) throws ItemParseException {
        List<String> itemFields = splitStringWithRegexPattern("[;|^]", singleItem);
        String nameValue = null;
        Double priceValue = null;
        String typeValue = null;
        String expirationValue = null;
        for (String itemKeyValue : itemFields) {
            List<String> itemKeyAndValue = splitStringWithRegexPattern("[:]", itemKeyValue);
            String key = itemKeyAndValue.get(0);
            String value = itemKeyAndValue.get(1).replaceAll("##$", "");
            if (key.equalsIgnoreCase("name")) {
                nameValue = value;
            } else if (key.equalsIgnoreCase("price")) {
                priceValue = Double.parseDouble(value);
            } else if (key.equalsIgnoreCase("type")) {
                typeValue = value;
            } else if (key.equalsIgnoreCase("expiration")) {
                expirationValue = value;
            } else {
                throw new ItemParseException();
            }
        }
        return new Item(nameValue, priceValue, typeValue, expirationValue);
    }

    private List<String> splitStringWithRegexPattern(String regexPattern, String inputString){
        return Arrays.asList(inputString.toLowerCase().split(regexPattern));
    }
}
