package uta.advse6324.ubs.utils;

public class EnumTable {
    public static final int UserLength = 13;

    /**
     * 所有的表名在这里进行声明和引用，防止冲突
     */
    public static final class TABLE_LIST {
        public static String USER = "tbl_user";
        public static String POST = "tbl_post";
        public static String MERCHANDISE = "tbl_mer";
        public static String TRANSCATION = "tbl_tra";
        public static String Billing = "tbl_Bil";
        public static String CLUB = "tbl_club";
        public static String CLUBMEMBER = "tbl_clubmember";
        public static String MESSAGE = "tbl_message";
    }

    public static final class User {
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String UTAID = "uta_id";
        public static final String LASTNAME = "lastname";
        public static final String FIRSTNAME = "firstname";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String ADDRESS = "adddress";
        public static final String CITY = "city";
        public static final String STATE = "state";
        public static final String ZIPCODE = "zipcode";
        public static final String MEMBER = "member";
        public static final String STATUS = "status";
    }

    public static final class Post {
        public static final String ID = "id";
        public static final String POST_DATE = "date";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String LIKED = "liked";
        public static final String OWNER = "owner";
        public static final String ADVERTISEMENT = "advertisement";
        public static final String CONTACT = "contact";
    }

    public static final class Merchandise{
        public static final String ID= "id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PICTURE = "picture";
        public static final String PRICE = "price";
        public static final String AVAILABLE = "availabe_status";
        public static final String SELL_LEND = "sell_lend";
        public static final String DATE = "date";
        public static final String OWNER_ID = "owner_id";

    }
    public static final class Billing{
        public static final String ID= "id";
        public static final String NAME = "name";
        public static final String ADDRESS = "address";
        public static final String USERID = "USERId";

    }

    public static final class Transcation{
        public static String USERID = "userid";
        public static String MERID = "merid";
        public static String DATE = "date";
        public static String BUY_BORROW = "buy_borrow";
        public static String PAY_STATUS = "pay_status";

    }

    public static final class Club{
        public static String CLUBNAME = "clubname";
        public static String CLUBINTRODUCTION = "clubintroduction";
        public static String CLUBCATEGORY = "clubcategory";
        public static String CLUBMANAGERID = "clubmanagerid";
    }

    public static final class ClubMember{
        public static String CLUBNAME = "clubname";
        public static String USERNAME = "username";
    }
    public static final class Message{
        public static String TIME = "time";
        public static String SEND = "send";
        public static String RECEIVE = "receive";
        public static String CONTENT = "content";
        public static String READSTATUS = "read_status";
    }
}
