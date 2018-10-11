package com.kodilla.kodillam1p5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KodillaM1p5Application {

    public static void main(String[] args) {
        SpringApplication.run(KodillaM1p5Application.class, args);

        FixedSalaryEmployee employee1 = new FixedSalaryEmployee(2000);
        HourlySalaryEmployee employee2 = new HourlySalaryEmployee(40, 19);
        PayForItemEmployee employee3 = new PayForItemEmployee(1320, 3.31);
        FixedSalaryWithPremiumEmployee employee4 = new FixedSalaryWithPremiumEmployee(2500, 0.75);

        SalaryPayoutProcessor processor = new SalaryPayoutProcessor(employee1);
        processor.processPayout();

        processor = new SalaryPayoutProcessor(employee2);
        processor.processPayout();

        processor = new SalaryPayoutProcessor(employee3);
        processor.processPayout();

        processor = new SalaryPayoutProcessor(employee4);
        processor.processPayout();
    }
}

interface Employee {

    double calculateSalary();
}

class FixedSalaryEmployee implements Employee {

    private double salary;

    public FixedSalaryEmployee(double salary) {
        this.salary = salary;
    }

    public double calculateSalary() {
        return this.salary;
    }
}

class HourlySalaryEmployee implements Employee {

    private double hours;
    private double hourlyPay;

    public HourlySalaryEmployee(double hours, double hourlyPay) {
        this.hours = hours;
        this.hourlyPay = hourlyPay;
    }

    public double calculateSalary() {
        return this.hourlyPay * this.hours;
    }
}

class PayForItemEmployee implements Employee {

    private int quantity;
    private double price;

    public PayForItemEmployee(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public double calculateSalary() {
        return this.quantity * price;
    }
}

class FixedSalaryWithPremiumEmployee implements Employee {

    private double salary;
    private double premium;

    public FixedSalaryWithPremiumEmployee(double salary, double premium) {
        this.salary = salary;
        this.premium = premium;
    }

    public double calculateSalary() {
        return salary * (1 + premium);
    }
}

// Payout
abstract class SalaryPayout {

    private Employee employee;

    public SalaryPayout(Employee employee) {
        this.employee = employee;
    }

    protected abstract void payout();

    public void processPayout() {
        System.out.println("Creating payout for: " + this.employee.calculateSalary() + " PLN");
        this.payout();
        System.out.println("Payout has been completed!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}

class SalaryPayoutProcessor extends SalaryPayout {

    public SalaryPayoutProcessor(Employee employee) {
        super(employee);
    }

    protected void payout() {
        System.out.println("Sending money to employee");
    }

}
