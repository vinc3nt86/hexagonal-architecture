package com.bimbitsoft.hexagonal.application.casa.v1_0_0.core.error;

public interface CasaOnboardingErrorCode {
    enum DUPLICATION {
        HAS_EXISTING_LIVE_PRODUCT("OB4001", ""),
        EMAIL_IS_DUPLICATED("OB4002", ""),
        MOBILE_IS_DUPLICATED("OB4003", "");
        private final Error error;

        DUPLICATION(String value, String message) {
            this.error = new Error(value, message);
        }

        public Error getValue() {
            return error;
        }
    }

    enum SERVER {
        INTERNAL_SERVER_ERROR("OB500");
        private final String code;

        SERVER(String value) {
            this.code = value;
        }
    }

    enum CUSTOMER_PROFILE {
        HAS_EXISTING_CUSTOMER_WITH_SAME_CIN
    }
}
