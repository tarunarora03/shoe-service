CREATE TABLE SHOE_BRAND_DTLS (ID SERIAL, SHOE_BRAND_NAME text);

CREATE TABLE SHOE_DETAILS (ID SERIAL, SHOE_SUB_CAT_ID int, SHOE_BRAND_ID int, SHOE_SIZE text, TRUE_SIZE_COUNT int, TRUE_SIZE_AVG DECIMAL);

CREATE TABLE SHOE_TRUE_SIZE_DETAILS (ID SERIAL, SHOE_BRAND_ID int, SHOE_SIZE text, TRUE_SIZE int);
