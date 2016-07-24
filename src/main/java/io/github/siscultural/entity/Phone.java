/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class Phone {
    
    private String areaCode;
    private String phoneNumber;

    public Phone() {
    }

    public Phone(String areaCode, String phoneNumber) {
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
