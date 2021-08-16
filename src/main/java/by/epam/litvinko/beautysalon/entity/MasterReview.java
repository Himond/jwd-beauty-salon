package by.epam.litvinko.beautysalon.entity;

public class MasterReview extends Review{

    private int clientId;
    private int masterId;

    public MasterReview() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
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
        result = 31 * result + clientId;
        result = 31 * result + masterId;
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

        public MasterReview.Builder setID(int id){
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

        public MasterReview.Builder setClientId(int clientId){
            MasterReview.this.clientId = clientId;
            return this;
        }

        public MasterReview.Builder setMasterId(int masterId){
            MasterReview.this.masterId = masterId;
            return this;
        }

        public MasterReview build(){
            return MasterReview.this;
        }

    }

}
