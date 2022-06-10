# ddd-it-proj1<br>
sample development of ec site(only japanese)<br>

### アプリの概要<br>
ECサイト
### 制作の背景・理由<br>

### 技術スタック<br>
開発環境： IntelliJ<br>
使用言語： Java SE<br>
フレームワーク：SpirngBoot　<br>
DB：　　Mysql,MyBatis<br>
サーバーサイド側：Thymeleaf, JUnit5<br>
インフラ構築：AWS:EC2,RDS


### 開発及び本番環境の構成<br>
#### 開発環境
あああ

#### 本番環境
zaa

### どんな機能を実装したか<br>
スタッフ機能 </br>
商品登録機能　</br>
ショッピングカート機能 </br>

### こだわったポイント等<br>
JUnitを使ってテストコード導入 </br>
サーバーを構築した。</br>
助長性を持たせて稼働率をあげた

### SQL文メモ
CREATE TABLE `db_name`.`m_product` (
`code` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NULL,
`price` INT NULL,
`gazou` VARCHAR(45) NULL,
PRIMARY KEY (`code`));
