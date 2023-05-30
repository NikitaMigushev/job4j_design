package ru.job4j.ood.srp;

public interface Logistics {
    void scheduleInbound();
    void processInbound(Shipping shipping);
    void scheduleOutbound();
    void processOutbound(Shipping shipping);
}
