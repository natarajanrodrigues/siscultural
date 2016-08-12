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
public class NotEnoughBalanceRubricException extends Exception {

    /**
     * Creates a new instance of <code>WithoutBalanceRubricException</code>
     * without detail message.
     */
    public NotEnoughBalanceRubricException() {
    }

    /**
     * Constructs an instance of <code>WithoutBalanceRubricException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NotEnoughBalanceRubricException(String msg) {
        super(msg);
    }
}
