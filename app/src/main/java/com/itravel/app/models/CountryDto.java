package com.itravel.app.models;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class CountryDto {
    @SerializedName("name")
    public Name name;

    @SerializedName("flags")
    public Flags flags;

    @SerializedName("region")
    public String region;

    public static class Name {
        @SerializedName("common")
        public String common;
    }

    public static class Flags {
        @SerializedName("png")
        public String png;
        @SerializedName("alt")
        public String alt;
    }
}
