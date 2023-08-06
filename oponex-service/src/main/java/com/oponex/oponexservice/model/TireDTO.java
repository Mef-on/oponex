package com.oponex.oponexservice.model;


import com.oponex.oponexservice.model.dao.Brand;
import com.oponex.oponexservice.model.dao.Season;

public record TireDTO(String name, Brand brand, String width, String profile, Season season, String volume,
                      String dateProduction) {
}
