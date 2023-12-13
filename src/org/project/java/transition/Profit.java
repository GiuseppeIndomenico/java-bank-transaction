package org.project.java.transition;

import java.math.BigDecimal;

public class Profit extends Transition {

    public Profit(String description, BigDecimal money) {
        super(description, money);
    }

    @Override
    public BigDecimal changeCount(BigDecimal finalCount) {
        return finalCount.add(getMoney());
    }
}
