package com.flight.app;

import java.util.List;

public interface SearchModule {
    List<String> getListOfFlightsByDate(String dateString);
}
