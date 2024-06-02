package com.capgemini.wsb.fitnesstracker.mail.api;

public record RequestEmailDto(String toAddress, String subject, Long userId) {

}