package by.epam.litvinko.beautysalon.model.dao;

public class ColumnName {

    //Users Table
    public static final String USERS_ID = "id";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_FIRST_NAME = "first_name";
    public static final String USERS_LAST_NAME = "last_name";
    public static final String USERS_ACTIVE = "is_active";
    public static final String USERS_DATA_JOINED = "data_joined";
    public static final String USERS_PHOTO = "photo";

    //Client Table
    public static final String CLIENT_ID = "id";
    public static final String CLIENT_USERS_ID = "user_id";
    public static final String CLIENT_PHONE = "phone";
    public static final String CLIENT_DATE_OF_BIRTHDAY = "date_of_birthday";
    public static final String CLIENT_IS_REGULAR = "is_regular";
    public static final String CLIENT_MONEY = "money";

    //Master Table
    public static final String MASTER_ID = "id";
    public static final String MASTER_USERS_ID = "user_id";
    public static final String MASTER_DESCRIPTION = "description";

    //Category Table
    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_NAME = "name";

    //Salon Service Table
    public static final String SERVICE_ID = "id";
    public static final String SERVICE_CATEGORY_ID = "category_id";
    public static final String SERVICE_NAME = "name";
    public static final String SERVICE_DESCRIPTION = "description";
    public static final String SERVICE_PRICE = "price";
    public static final String SERVICE_TIME = "service_time";
    public static final String SERVICE_AVAILABLE = "available";
    public static final String SERVICE_CREATED = "created";
    public static final String SERVICE_UPDATED = "updated";
    public static final String SERVICE_IMAGE = "image";

    //Coupon Table
    public static final String COUPON_ID = "id";
    public static final String COUPON_CODE = "code";
    public static final String COUPON_VALID_FROM = "valid_from";
    public static final String COUPON_VALID_TO = "valid_to";
    public static final String COUPON_DISCOUNT = "discount";
    public static final String COUPON_IS_ACTIVE = "is_active";

    //Order Table
    public static final String ORDER_ID = "id";
    public static final String ORDER_CLIENT_ID = "client_id";
    public static final String ORDER_COUPON_ID = "coupon_id";
    public static final String ORDER_CREATED= "created";
    public static final String ORDER_DATE_OF_SERVICE= "date_of_service";
    public static final String ORDER_IS_PAID = "is_paid";
    public static final String ORDER_IS_ACTIVE = "is_active";

    //Order Item Table
    public static final String ORDER_ITEM_ID = "id";
    public static final String ORDER_ITEM_ORDER_ID = "order_id";
    public static final String ORDER_ITEM_SERVICE_ID = "service_id";
    public static final String ORDER_ITEM_MASTER_ID = "master_id";

    //Service review Table
    public static final String SERVICE_REVIEW_ID = "id";
    public static final String SERVICE_REVIEW_CLIENT_ID = "client_id";
    public static final String SERVICE_REVIEW_SERVICE_ID = "service_id";
    public static final String SERVICE_REVIEW_REVIEW = "review";
    public static final String SERVICE_REVIEW_IS_ACTIVE = "is_active";

    //Role
    public static final String ROLE_ID = "id";
    public static final String ROLE_ROLE = "role";

    //Position
    public static final String POSITION_ID = "id";
    public static final String POSITION_POSITION = "position";

    private ColumnName() {
    }


}
