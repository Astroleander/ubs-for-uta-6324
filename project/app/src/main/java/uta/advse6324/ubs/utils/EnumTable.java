package uta.advse6324.ubs.utils;

public class EnumTable {
    public static final int UserLength = 13;

    /**
     * 所有的表名在这里进行声明和引用，防止冲突
     */
    public static final class TABLE_LIST {
        public static String USER = "tbl_user";
        public static String POST = "tbl_post";
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
    }
}
