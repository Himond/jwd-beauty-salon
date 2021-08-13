package epam.by.litvinko.beautysalon.entity;

import java.time.LocalDateTime;

public abstract class Review extends Entity{

    private String review;
    private boolean isActive;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Review review1 = (Review) o;

        if (isActive != review1.isActive) return false;
        return review != null ? review.equals(review1.review) : review1.review == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }


}
