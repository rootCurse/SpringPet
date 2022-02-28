package ru.itmo.banks.card;

import java.util.Map;

public class PercentInfo {
    private Map<Double, Double> _percentForDeposit;
    private double _percentForDebet;
    private double _percentForCredit;

    public PercentInfo(double percentForDebet, double percentForCredit, Map<Double, Double> percentForDeposit) {
        _percentForDeposit = percentForDeposit;
        _percentForCredit = percentForCredit;
        _percentForDebet = percentForDebet;
    }

    public double getPercentForDebet() {
        return _percentForDebet;
    }

    public void setPercentForDebet(double _percentForDebet) {
        this._percentForDebet = _percentForDebet;
    }

    public double getPercentForCredit() {
        return _percentForCredit;
    }

    public void setPercentForCredit(double _percentForCredit) {
        this._percentForCredit = _percentForCredit;
    }

    public void setPercentForDeposit(Map<Double, Double> _percentForDeposit) {
        this._percentForDeposit = _percentForDeposit;
    }

    public double getPercentForDeposit(double startCash) {
        Double result = 0.0;
        for (Double cash : _percentForDeposit.keySet()) {
            if (startCash > cash)
                result = _percentForDeposit.get(cash);
        }
        return result;
    }
}
