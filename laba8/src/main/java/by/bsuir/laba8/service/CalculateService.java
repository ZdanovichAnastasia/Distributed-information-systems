package by.bsuir.laba8.service;

import by.bsuir.laba8.service.models.ICalculateService;

public class CalculateService implements ICalculateService {
    public Double calculatePercent(double number, double percent) {
        return (number*100)/percent;
    }
}
