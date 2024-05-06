package com.example.pokelib;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon
{
    public class Sprites
    {
        @SerializedName("front_default")
        private String front_default;

        public String GetFrontDefault() { return front_default; }
    }

    public static class Stat
    {
        @SerializedName("base_stat")
        private int base_stat;
        @SerializedName("stat")
        private StatDetails stat;

        public int getBase_stat() { return base_stat; }
        public StatDetails getStat() { return stat; }
    }

    public static class StatDetails
    {
        @SerializedName("name")
        private String name;

        public String getName() { return name; }
    }

    @SerializedName("sprites")
    private Sprites _sprites;
    @SerializedName("name")
    private String _name;
    @SerializedName("weight")
    private int _weight;
    @SerializedName("height")
    private int _height;
    @SerializedName("stats")
    private List<Stat> _stats;

    public Sprites GetSprites() { return _sprites; }
    public String GetName() { return _name; }
    public int GetWeight() { return _weight;}
    public int GetHeight() { return _height; }
    public List<Stat> GetStats() { return _stats; }
}