package epam.by.litvinko.beautysalon.entity;

public class MasterReview extends Review{

    private long clientId;
    private long masterId;

    public MasterReview() {
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MasterReview that = (MasterReview) o;

        if (clientId != that.clientId) return false;
        return masterId == that.masterId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + (int) (masterId ^ (masterId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MasterReview{");
        sb.append("Id=").append(getId());
        sb.append(", clientId=").append(clientId);
        sb.append(", masterId=").append(masterId);
        sb.append(", review=").append(getReview());
        sb.append(", isActive=").append(isActive());
        sb.append('}');
        return sb.toString();
    }

    public static MasterReview.Builder newBuilder(){
        return new MasterReview().new Builder();
    }

    public class Builder{

        public Builder() {
        }

        public MasterReview.Builder setID(long id){
            MasterReview.super.setId(id);
            return this;
        }

        public MasterReview.Builder setReview(String review){
            MasterReview.this.setReview(review);
            return this;
        }

        public MasterReview.Builder setActive(boolean isActive){
            MasterReview.this.setActive(isActive);
            return this;
        }

        public MasterReview.Builder setClientId(long clientId){
            MasterReview.this.clientId = clientId;
            return this;
        }

        public MasterReview.Builder setMasterId(long masterId){
            MasterReview.this.masterId = masterId;
            return this;
        }

        public MasterReview build(){
            return MasterReview.this;
        }

    }

}
