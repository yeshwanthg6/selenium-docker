package org.learn.pages.flightreservation.model;

public record FlightReservationTestData(String firstName,
                                        String lastName,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String zip,
                                        String noOfPassengers,
                                        String totalExpectedPrice) {

}
