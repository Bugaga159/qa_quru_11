package ru.example.hw9.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teacher {
    public String name;
    public String surname;
    @SerializedName("favorite_music")
    @JsonProperty("favorite_music")
    public List<String> favoriteMusic;
    public Address address;
}
