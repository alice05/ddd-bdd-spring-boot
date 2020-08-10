package com.santoshkc.leaser.domain.aggregates.freelancer;

class CommunicationChannel {
    private final String value;

    CommunicationChannel(String value) {
        this.value = value;
    }

    ContactType getValue() {
        return ContactType.valueOf(value);
    }
}
