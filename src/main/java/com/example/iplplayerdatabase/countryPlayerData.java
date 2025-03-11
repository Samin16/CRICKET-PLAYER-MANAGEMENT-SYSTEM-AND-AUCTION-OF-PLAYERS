package com.example.iplplayerdatabase;




import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class countryPlayerData {
    private final SimpleStringProperty countryName;
    private final SimpleIntegerProperty playerCount;

    public countryPlayerData(String countryName, int playerCount) {
        this.countryName = new SimpleStringProperty(countryName);
        this.playerCount = new SimpleIntegerProperty(playerCount);
    }

    public String getCountryName() {
        return countryName.get();
    }

    public SimpleStringProperty countryNameProperty() {
        return countryName;
    }

    public int getPlayerCount() {
        return playerCount.get();
    }

    public SimpleIntegerProperty playerCountProperty() {
        return playerCount;
    }
}
