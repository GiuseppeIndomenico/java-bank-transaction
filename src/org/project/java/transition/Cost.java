package org.project.java.transition;

import java.math.BigDecimal;

public class Cost extends Transition {

    public Cost(String description, BigDecimal money) {
        super(description, money);
    }

    @Override
    public BigDecimal changeCount(BigDecimal finalCount) {
        return finalCount.subtract(getMoney());
    }

    @Override
    public String toString() {
        return "description=" + getDescription() + ", money= -" + formatMoney() + " € ] \n";
    }
}
