package com.example.mateusz.astroweather2.yahoo.data;

import org.json.JSONObject;

public class Channel implements JSONPopulator{
    private Item item;
    private Units units;
    private Atmosphere atmosphere;
    private Wind wind;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public Wind getWind() {
        return wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @Override
    public void populate(JSONObject data) {
        this.units = new Units();
        this.units.populate(data.optJSONObject("units"));

        this.item = new Item();
        this.item.populate(data.optJSONObject("item"));

        this.atmosphere = new Atmosphere();
        this.atmosphere.populate(data.optJSONObject(("atmosphere")));

        this.wind = new Wind();
        this.wind.populate(data.optJSONObject("wind"));

        this.location = new Location();
        this.location.populate(data.optJSONObject("location"));
    }

     public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }
}
