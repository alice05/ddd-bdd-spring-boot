package com.santoshkc.leaser.domain.aggregates.freelancer;

class Address {
    //#region private final variables
    private final String zipcode;
    private final String city;
    //#endregion

    // region Constructor
    Address(String zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }
    // endregion

    // region getters (optional: can be used lombok)
    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }
    //endregion
}
