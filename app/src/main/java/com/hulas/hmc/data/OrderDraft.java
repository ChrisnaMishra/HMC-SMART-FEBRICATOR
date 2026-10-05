package com.hulas.hmc.data;

public class OrderDraft {
    public String grade = "202";
    public String type = "Round";
    public String size = "1";
    public boolean heavy;
    public double quantity;
    public double totalKg;
    public double totalBeforeVat;
    public double totalWithVat;

    public void calculate() {
        double kgPerPipe = PipeWeightData.weight(type, size, heavy);
        totalKg = kgPerPipe * quantity;
        double kgRate = PipeWeightData.rate(grade) + PipeWeightData.surcharge(type, size);
        totalBeforeVat = totalKg * kgRate;
        totalWithVat = totalBeforeVat * (1 + PipeWeightData.VAT);
    }
}
