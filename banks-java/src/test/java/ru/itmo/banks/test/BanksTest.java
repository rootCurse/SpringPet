package ru.itmo.banks.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.banks.BanksException;
import ru.itmo.banks.Person;
import ru.itmo.banks.bank.Bank;
import ru.itmo.banks.bank.CentralBank;
import ru.itmo.banks.card.GeneralCard;
import ru.itmo.banks.card.PercentInfo;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

class BanksTest {

    @Test
    void addBank() {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Assertions.assertEquals(bank.getCreditLimit(), 10000);
        Assertions.assertEquals(bank.getLimitWithoutTrusted(), 10000);
    }

    @Test
    void addPerson() {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.addPerson("Ivan", "Ivanov", bank);
        Assertions.assertEquals(person.get_firstName(), "Ivan");
        Assertions.assertEquals(person.get_secondName(), "Ivanov");
        person.set_address("Nevskiy");
        person.set_passport(123);
        Assertions.assertEquals(person.get_passport(), 123);
        Assertions.assertEquals(person.get_address(), "Nevskiy");
    }

    @Test
    void addDebitCard() throws BanksException {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.addPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        GeneralCard debitCard = centralBank.addDebitCard(person.get_personId(), 10000, bank);
        Assertions.assertEquals(debitCard.getPersonId(), person.get_personId());
        Assertions.assertEquals(debitCard.getCash(), 10000);
    }

    @Test
    void replenishment() throws BanksException {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.addPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeReplenishment = 100000;
        GeneralCard debitCard = centralBank.addDebitCard(person.get_personId(), cashBeforeReplenishment, bank);
        double cashForReplenishment = 10000;
        centralBank.replenishment(debitCard.getCardId(), cashForReplenishment);
        Assertions.assertEquals(debitCard.getCash(), cashBeforeReplenishment + cashForReplenishment);
    }

    @Test
    void withdrawal() throws BanksException {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.addPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeWithdrawal = 100000;
        GeneralCard debitCard = centralBank.addDebitCard(person.get_personId(), cashBeforeWithdrawal, bank);
        double cashForWithdrawal = 10000;
        centralBank.withdrawal(debitCard.getCardId(), cashForWithdrawal);
        Assertions.assertEquals(debitCard.getCash(), cashBeforeWithdrawal - cashForWithdrawal);
    }

    @Test
    void transfer() throws BanksException {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.addPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeTransfer = 100000;
        GeneralCard debitCard1 = centralBank.addDebitCard(person.get_personId(), cashBeforeTransfer, bank);
        GeneralCard debitCard2 = centralBank.addDebitCard(person.get_personId(), cashBeforeTransfer, bank);
        double cashForTransfer = 10000;
        centralBank.transfer(debitCard1.getCardId(), debitCard2.getCardId(), cashForTransfer);
        Assertions.assertEquals(debitCard1.getCash(), cashBeforeTransfer - cashForTransfer);
        Assertions.assertEquals(debitCard2.getCash(), cashBeforeTransfer + cashForTransfer);
    }

    @Test
    void changeDate() throws BanksException {
        Map<Double, Double> percents = new HashMap<Double, Double>() {
        };
        percents.put(100.0, 0.01);
        CentralBank centralBank = CentralBank.getInstance();
        Bank bank = centralBank.addBank(10000, 10000, new PercentInfo(0.1, 0.15, percents));
        Person person = centralBank.addPerson("Ivan", "Ivanov", bank);
        person.set_address("Nevskiy");
        person.set_passport(123);
        double cashBeforeChangeDate = 100000;
        GeneralCard debitCard = centralBank.addDebitCard(person.get_personId(), cashBeforeChangeDate, bank);
        GregorianCalendar newDate = new GregorianCalendar(2025, 1, 26);
        centralBank.changeDate(newDate);
        Assertions.assertEquals(debitCard.getDate(), newDate);
        Assertions.assertNotEquals(debitCard.getCash(), cashBeforeChangeDate);
    }
}