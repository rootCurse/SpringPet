package ru.itmo.banks.cards;
import javafx.util.Pair;

import java.util.List;

public class PercentInfo {
    private List<Pair<Double, Double>> _percentForDeposit;
    private double _percentForDebet;
    private double _percentForCredit;

    public PercentInfo(double percentForDebet, double percentForCredit, List<Pair<Double, Double>> percentForDeposit)
    {
        _percentForDeposit = percentForDeposit;
        _percentForCredit = percentForCredit;
        _percentForDebet = percentForDebet;
    }

    public double get_percentForDebet() {
        return _percentForDebet;
    }

    public void set_percentForDebet(double _percentForDebet) {
        this._percentForDebet = _percentForDebet;
    }

    public double get_percentForCredit() {
        return _percentForCredit;
    }

    public void set_percentForCredit(double _percentForCredit) {
        this._percentForCredit = _percentForCredit;
    }

    public void set_percentForDeposit(List<Pair<Double, Double>> _percentForDeposit) {
        this._percentForDeposit = _percentForDeposit;
    }
    public double GetPercentForDeposit(double startCash)
    {
        if(startCash > _percentForDeposit.get(0).getKey())
            return startCash > _percentForDeposit.get(_percentForDeposit.size()).getKey() ? _percentForDeposit.get(_percentForDeposit.size()).getValue() : _percentForDeposit.get(2).getValue();
        return _percentForDeposit.get(0).getValue();
    }
}
