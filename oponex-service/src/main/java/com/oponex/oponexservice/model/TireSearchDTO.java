package com.oponex.oponexservice.model;

import com.oponex.oponexservice.model.dao.Brand;
import com.oponex.oponexservice.model.dao.Season;

public record TireSearchDTO(Brand brand, String width, String profile, Season season) {
}
