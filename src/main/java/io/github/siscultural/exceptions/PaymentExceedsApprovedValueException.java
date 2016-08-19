/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.exceptions;

/**
 *
 * @author Natarajan Rodrigues
 */
public class PaymentExceedsApprovedValueException extends Exception {

    /**
     * Creates a new instance of <code>ExceedsPaymentValue</code> without detail
     * message.
     */
    public PaymentExceedsApprovedValueException() {
    }

    /**
     * Constructs an instance of <code>ExceedsPaymentValue</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PaymentExceedsApprovedValueException(String msg) {
        super(msg);
    }
}
