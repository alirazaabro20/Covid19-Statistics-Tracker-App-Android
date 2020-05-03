package model;

public class Country  {



    String modelCountry;
    String modelCases;
    String modelDeaths;
    String modelTodayCases;
    String modelTodayDeaths;
    String modelTodayRecovered;
    String modelCritical;



    String flagImageUrl;



    public Country(String modelCountry, String modelCases, String modelDeaths, String modelTodayRecovered, String flagUrl) {
        this.modelCountry = modelCountry;
        this.modelCases = modelCases;
        this.modelDeaths = modelDeaths;
        this.modelTodayCases = modelTodayCases;
        this.modelTodayDeaths = modelTodayDeaths;
        this.modelTodayRecovered = modelTodayRecovered;
        this.modelCritical = modelCritical;
        this.flagImageUrl=flagUrl;
    }

    public String getModelCountry() {
        return modelCountry;
    }

    public String getModelCases() {
        return modelCases;
    }

    public String getModelDeaths() {
        return modelDeaths;
    }

    public String getModelTodayRecovered() {
        return modelTodayRecovered;
    }

    public String getFlagImageUrl() {
        return flagImageUrl;
    }


}
