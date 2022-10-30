package com.portfolio.id.bor.Security.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String message;

    public Message() {
    }

    public Message(final String message) {
        this.message = message;
    }
}
