/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Willem van Ess
 */
public class RatingTranslator {
    private double rating; 

    public RatingTranslator(double rating) {
        this.rating = rating;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    
    public String toStars () {
        return "";
    }
    
}
