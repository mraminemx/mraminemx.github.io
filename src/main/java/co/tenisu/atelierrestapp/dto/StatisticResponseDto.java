package co.tenisu.atelierrestapp.dto;

import lombok.Data;

@Data
public class StatisticResponseDto {

    private String countryWithHighestWinRatio;
    private double averageIMC;
    private double medianHeight;

    public StatisticResponseDto(String countryWithHighestWinRatio, Double averageIMC, Double medianHeight) {
        this.countryWithHighestWinRatio = countryWithHighestWinRatio;
        this.averageIMC = averageIMC;
        this.medianHeight = medianHeight;
    }
}
