package com.adeesha.cw1.bank.util;

import java.math.BigDecimal;

public final class Constants {
    public static final int DEFAULT_SCALE = 8;
    public static final int REGULAR_CUSTOMER_PRIORITY = 5;
    public static final int VIP_CUSTOMER_PRIORITY = 10;
    public static final int BANK_MANAGER_PRIORITY = 5;
    public static final BigDecimal INCOME_TAX_RATE = BigDecimal.valueOf(18);
    public static final BigDecimal OVERDRAFT_INTEREST_RATE = BigDecimal.valueOf(24);
    public static final String DECIMAL_FORMAT_PATTERN = "#0.00";
}
