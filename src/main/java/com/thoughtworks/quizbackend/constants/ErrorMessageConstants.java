package com.thoughtworks.quizbackend.constants;

public class ErrorMessageConstants {
    public static final String USER_NAME_EMPTY_ERROR = "username can not be empty";
    public static final String USER_AGE_EMPTY_ERROR = "user age can not be empty";
    public static final String AVATAR_LINK_EMPTY_ERROR = "user avatar link can not be empty";
    public static final String USER_NAME_LENGTH_ERROR = "username length must between 1 and 128 bytes";
    public static final String USER_AGE_VALUE_ERROR = "user age must greater than 16";
    public static final String USER_AVATAR_LENGTH_ERROR = "user avatar link length should  between 8 and 512 bytes";
    public static final String USER_DESCRIPTION_LENGTH_ERROR = "user description length should  between 0 and 1024 bytes";
    public static final int USER_MIN_AGE = 17;
    public static final int USERNAME_MIN_LENGTH = 1;
    public static final int USERNAME_MAX_LENGTH = 128;
    public static final int USER_AVATAR_LINK_MIN_LENGTH = 8;
    public static final int USER_AVATAR_LINK_MAX_LENGTH = 512;
    public static final int USER_DESCRIPTION_MIN_LENGTH = 0;
    public static final int USER_DESCRIPTION_MAX_LENGTH = 1024;

    public static final String YEAR_EMPTY_ERROR = "year can not be empty";
    public static final String TITLE_EMPTY_ERROR = "title can not be empty";
    public static final String TITLE_LENGTH_ERROR = "education title length should  between 1 and 256 bytes";
    public static final String DESCRIPTION_EMPTY_ERROR = "description can not be empty";
    public static final String EDUCATION_DESCRIPTION_LENGTH_ERROR = "education description length should  between 1 and 4096 bytes\"";
    public static final int TITLE_MIN_LENGTH = 1;
    public static final int TITLE_MAX_LENGTH = 256;
    public static final int EDUCATION_DESCRIPTION_MIN_LENGTH = 1;
    public static final int EDUCATION_DESCRIPTION_MAX_LENGTH = 4096;

    public static final String GET_USER_BY_WRONG_ID_ERROR = "Cannot find basic info for user with id ";
}
