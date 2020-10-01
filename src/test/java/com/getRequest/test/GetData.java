package com.getRequest.test;

import com.getRequest.DeckAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetData {
    //@Test
    public void testCode() {
        List<String> getParamValues = DeckAPI.addJokerToDeck();
        int remainingCards = Integer.valueOf(getParamValues.get(1));
        int excepectedCardCount = 54;
        System.out.println("Actual remaining card: " + remainingCards +"\n" + "Excepected Card Count: "+ excepectedCardCount);
        Assert.assertEquals(remainingCards,excepectedCardCount);
    }
    @Test
    public void testDeckID()
    {
        DeckAPI.addJokerToDeck();
        List<String> getParamValues = DeckAPI.addJokerToDeck();
        String deck_ID = getParamValues.get(3);
        int drawCardCount = 4;
        int actualRemainigCard = Integer.valueOf(getParamValues.get(2)) - drawCardCount;
        int expectedValue = Integer.valueOf(DeckAPI.drawCard(deck_ID,drawCardCount));
        Assert.assertEquals(actualRemainigCard,expectedValue);
    }
}
