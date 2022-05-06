USE companydata;
CREATE TABLE loginuser(
    accountId VARCHAR(255) NOT NULL COMMENT "アカウント",
    password CHAR(6) NOT NULL COMMENT "パスワード",
	PRIMARY KEY (accountId)
);


CREATE TABLE nationality
(
        nationalityCd CHAR(3) NOT NULL COMMENT '国籍コード', -- nationalityCdカラムを設定
	nationalityName VARCHAR(255) COMMENT '国籍',          -- nationalityNameカラムを設定
	PRIMARY KEY( nationalityCd )
);

CREATE TABLE gender(
    genderCd CHAR(2) NOT NULL COMMENT '性別コード',
    genderName VARCHAR(255) NOT NULL COMMENT '性別',
    PRIMARY KEY( genderCd )
	);
    
CREATE
   TABLE empdata(
        -- 社員番号
        empCd CHAR(6) NOT NULL COMMENT '社員番号',
        -- 姓名
        name VARCHAR(255) NOT NULL COMMENT '姓名',
        -- 生年月日
        birthday DATE NOT NULL COMMENT '生年月日',
        -- 国籍コード
        nationalityCd CHAR(3) NOT NULL COMMENT '国籍コード',
        -- 性別コード
        genderCd CHAR(2) NOT NULL COMMENT '性別コード',
        PRIMARY KEY (empCd),
        FOREIGN KEY (nationalityCd) REFERENCES nationality(nationalityCd),
        FOREIGN KEY (genderCd) REFERENCES gender(genderCd)
    )