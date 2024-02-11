package com.flight.app;

import java.util.Arrays;
import java.util.List;

public class SearchModuleImpl implements SearchModule{
    @Override
    public List<String> getListOfFlightsByDate(String dateString) {

        // TODO: it will take date as input
        // TODO: it will create a query for the database
        // TODO: it will call the database
        // TODO: it will validate the response.

        return Arrays.asList("Indigo", "SpiceJet", "AsianAirways");

    }



}
