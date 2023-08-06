package com.oponex.oponexservice.model.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tire")
public class Tire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "Name must not be blank")
    @Column(name = "name", nullable = false)
    private String name;
    @NotBlank(message = "Brand must not be blank")
    @Column(name = "brand", nullable = false)
    private String brand;
    @NotBlank(message = "Width must not be blank")
    @Column(name = "width", nullable = false)
    private String width;
    @NotBlank(message = "Profile must not be blank")
    @Column(name = "profile", nullable = false)
    private String profile;
    @NotBlank(message = "Season must not be blank")
    @Column(name = "season", nullable = false)
    private String season;
    @NotBlank(message = "Volume must not be blank")
    @Column(name = "volume", nullable = false)
    private String volume;

    @NotBlank(message = "Date production must not be blank")
    @Column(name = "date_production", nullable = false)
    private String dateProduction;
}
