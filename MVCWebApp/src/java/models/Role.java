/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Bjorn
 */
public class Role implements Serializable {

    private long roleNumber;
    private String name;

    public Role(long ruleNumber, String name) {
        if (ruleNumber < 1) {
            throw new IllegalArgumentException(
                    "Customer number may not be negative, value = " + ruleNumber);
        }
        if (name == null) {
            throw new NullPointerException("Customer name may not be empty");
        }

        this.roleNumber = ruleNumber;
        this.name = name;
    }

 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRoleNumber() {
        return roleNumber;
    }

    public void setRoleNumber(long ruleNumber) {
        this.roleNumber = ruleNumber;
    }
}