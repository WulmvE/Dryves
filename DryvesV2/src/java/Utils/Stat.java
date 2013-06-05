/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Patrick
 */
public class Stat {

    private String statLabel;
    private int statValue;

    public Stat() {
    }

    public Stat(String statLabel, int statValue) {
        this.statLabel = statLabel;
        this.statValue = statValue;
    }

    public String getStatLabel() {
        return statLabel;
    }

    public void setStatLabel(String statLabel) {
        this.statLabel = statLabel;
    }

    public int getStatValue() {
        return statValue;
    }

    public void setStatValue(int statValue) {
        this.statValue = statValue;
    }
}
