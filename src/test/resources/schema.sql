
/*スタッフテーブル*/
CREATE TABLE m_staff
(
    id INT PRIMARY KEY
    , name VARCHAR(45)
    , password VARCHAR(45)
);

/*商品テーブル*/
CREATE TABLE m_product
(
    code INT PRIMARY KEY
    , name VARCHAR(45)
    , price INT
    , gazou VARCHAR(45)
);