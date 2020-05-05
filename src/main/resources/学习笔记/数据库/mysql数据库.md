## 常用数据库

```
SQL	Structured/Standard Query Language
常用的关系型数据库：
· Oracle
` MySQL 大多企业使用的数据库
` MarriaDB
` SQL Server 微软
` Access(offive)
` DB2(IBM)
` Informix
` Sybase(power design)
` SQLite(Android)手机通讯录的存放地
` OceanBase 阿里的数据库， 抢票软件的数据库就用的这个
```

**小知识：MariaDB和MySQL的关系**

```
常用的非关系型数据库
Memcached
Redis (String, Set, Zset, List, Hash)
MongoDB (ORM, Herbinate & Mybaitis)
Cassandra（Facebook）
Hbase （Apache, Hadoop database）
MemcachedDB（Sina）
```

**易混淆术语：**

```sql
--数据库系统：
是指在计算机系统中引入数据库后的系统，一般由数据库、数据库管理系统、应用系统、数据库管理员（DBA）构成。 

--数据库管理系统（DBMS）：
是一种操纵和管理数据库的大型软件，用于建立、使用和维护数据库（如：MySQL）。

--数据库：
数据库是按照数据结构来组织、存储和管理数据的仓库。
```



## 数据库操作

```
db_name 指代某个数据库的名字
```



### 0.登录数据库

```
mysql -h 主机名 -u 用户名 –p

-h：该参数用于指定客户端的主机名（host），即哪台机器要登录 MySQL Server，如果是当前机器该参数可以省略;
-u：用户名（user）。
-p：密码（password），如果密码为空，该参数可以省略
刚装的 MySQL，它有一个管理员账号 root，密码默认为空。所以我们可以用下面命令登录：
mysql -u root
```



### **1. 新建数据库**

```sql
--创建数据库，不要有中文
create database  + (name);

//注意可以添加指定的描述语句
create database + (name) + if not exists (name);

//可以在创建数据库时指定编码方法，但默认UTF8
create database db_name set utf8;

//创建一个使用gbk字符集的数据库
create database db_name character set gbk;

--创建数据库
CREATE DATABASE [IF NOT EXISTS] db_name
    [create_specification [, create_specification] ...];
 
create_specification:
    [DEFAULT] CHARACTER SET charset_name	--可选项，用于指定字符集
  | [DEFAULT] COLLATE collation_name		--指定数据库字符集的比较方式
```

### **2.删除数据库**

```sql
drop database <数据库名>;

mysql> drop database RUNOOB;
```

### **3.修改数据库**

```sql
//修改默认字符集
alter database (name) character set utf8;
```

### **4.查看数据库**

```sql
//展示该账户所有数据库
Show Databases;

//查看数据库的创建信息
show create database (name);
```

**--数据库组成**

```sql
DDL：数据定义语言
DML：数据操作语言
DQL: 数据查询语言
DCL：数据控制语言
TPL：事务处理语言
```



## 表的操作

### **1.创建表**

```sql
--创建表：
CREATE TABLE table_name
(
	field1  datatype,
	field2  datatype,
	field3  datatype
)[CHARACTER SET 字符集 COLLATE 校对规则]
field：指定列名　datatype：指定列类型
--注意：创建表前，要先使用USE db_name语句使用库。

--实例：
create table graduate_stu (
    id int primary key auto_increment,
    name varchar(30),
    gender varchar(1),
    enrollment_date date,
    tutor_id int,
    constraint stu_tutor_fk foreign key (tutor_id) references tutor(id)  
);
```



```sql
--创建表
create table name();
--如果创建的表时用某一个mysql关键字的，可以加上·name·,这是位于键盘上1旁边的字符，在终端上显示为单引号，但并不是键盘上的单引号
```

### **2.删除表**

```sql
drop table tb_name;
mysql> drop table dept;
```

### **3.修改表**

```sql
--alter table ... add ... 追加列
alter table name add column (第一项),(第二项),...;
alter TABLE `schedule_job_log` ADD COLUMN `function` text COMMENT '函数';
--alter table ... modify column ... 修改某一列的参数类型
alter table name modift column col_name longtest;
alter table student modify column chinese int;
--alter table ... change ... 修改某一列的名称
alter table stuff change oldName newName text;
--修改字符编码方式character set
alter table name character set utf8;
--修改表名rename
rename table name to newName;
--设置表的默认值
alter table student alter column chinese set DEFAULT 0;
```

### **4.查询表**

```sql
--查看表的结构
show create table + tb_name;
desc + tb_name;
describe tb_name;
--查询表的内容
select ... from tb_name;

--查看表各列的详细结构
show create table info;
```



## 段的操作

```
tb_name 表示某一个表的名字		table_name
col_name 表示列的名字	column_name
```

### 1.**增加内容**

```sql
insert into tb_name ... values ...;

实例：
--1.完全写法，values前面的项和后面的顺序要对应
insert into student (id, name, chinese,english,math)values (1001,'张三', 80, 80, 80);
--2.省略前面的列名称，则每一项都要写，且要按照创建表时的顺序写
insert into tutor values(1, '王导师', '讲师', '信息安全');
```

### 2.**删除内容**

```sql
-- 以行为单位，删除行
delete from tb_name where ...;
//如果不添加where语句会删掉每一行

--删除所有的数据
truncate tb_name;

--注意这两个操作只会删除数据，但表依然存在

--删除某一列，不能有约束
alter table [表名] drop column [字段名]
```

### 3.**修改内容**

```sql
--添加限定条件where
update tb_name set col_name = value ,field2=new-value2 where ...;
```

### **4.查询内容**

```sql
关键字 select
--1.查询表中所有数据
select * from tb_name;
--2.查询固定的列内容
select col_name from tb_name;
--3.配合where语句，限制结果的显示范围，注意 <=>可以搜索到一个值是否等于null，也可以使用is null，这个更加直观
，也是推荐的，还有is not null
--4.条件语句...between ... and ...搜索到在什么之间的
--5.in
select * from tb_name where class in ('一班', '二班');
--6.模糊搜索： like + 正则表达式（通配符）
select * from tb_name where name like '黄%'
% 匹配任意数量的
_ 匹配一个字符
--7.查询某一列所有不重复的数据 distinct
select distinct class from tb_name;
--8.数据库量太大，怎么限制只显示部分结果？//只适用于mysql中。
limit offset , nums;
offset偏移量，跳过前面的多少条数据，显示后续的nums条数据；
第二种写法：
LIMIT nums OFFSET offset;
--9.对结果排序   order  asc升序，desc降序
select * from tb_name order by math asc;
--10.多项排序，如语文降序，数学升序，外语降序
select * from tb_name order by chinese desc,math asc, english desc;  
//这是有优先级，先就语文降序排，然后局部如果语文成绩相同在依据数学升序排，然后还有相同在依据英语降序排
```



### 5.多表结合查询

```sql
名词解释：
DQL数据查询语言：  Double Query Languare 双表查询语句

--select语句的书写与执行顺序
(5)SELECT column_name/...
(1)FROM tb_name,...
(2)[WHERE]
(3)[GROUP BY ...]
(4)[HAVING...]
(6)[ORDER BY...]
```

连接查询：

```sql
--内连接的隐式调用：
select t1.*, t2.* from tb_name1 t1, tb_name2 t2 where t1.id = t2.id;

--1.交叉连接(基本不用，有很多无意义的数据)
	关键词：cross join或者用隐式调用
	显示调用：select * from tb_name1 cross join tb_name2;
	隐式调用：select * from tb_name1 , tb_name2;
    采用笛卡尔乘积的方式，将第一张表的每一行和第二张表的每一行都直接连接，如果第一张表有5行，第二张表有7行，
    最后就会有35行的信息
--2.内连接:
	关键词：inner join或者join或者用隐式调用
	格式：tb_name1 join tb_name2 on ...;
    	显式调用：select * from tb_name1 tb1 inner join tb_name2 tb2 on tb1.name = tb2.name；
    	隐式调用：select t1.*, t2.* from tb_name1 t1, tb_name2 t2 where t1.id = t2.id;
    	此处给两者设置了别名tb1和tb2,然后用了对应的条件语句，只有符合的才会显示
    解释：join关键字前后连接两个需要连接的表，然后on之后跟着连接规则，比如表1中的ID等于表2中的ID之类的，
    	也就是根据两者之间的共同点拼接，比如学生的班级列和班级这个表格中就班级这项是共同点，就可以根据这项拼接

--3.左外连接：
	关键词：left outer join 或者left join
    格式：同内连接一致，只是把关键词join换成left join
    解释：会先依据内连接的规则拼接出结果，然后会将关键词左边的表中剩下的未选中的行也打印出来，缺少的值用null填充进去。right outer join 或者right join
--4.右外连接：和左外连接相似，如果对换左右表的位置二者结果就一样了

--5.on和where
	select * from tb_name1 tb1 inner join tb_name2 tb2 on tb1.name = tb2.name where ...;
	对于连接查询，这两个条件语句的执行时间是不同的，on语句是在拼接两个表的时候用于判断，而where语句是在表拼接结束后，再在结果上进行二次的条件判断。
```



### 6.子查询

```
也叫嵌套查询，在select语句中再嵌套一个select语句。
```



### 7.报表查询

```sql
--目的：对数据进行分组统计
--语法格式：
	select...from...[where][group by...[having...]] [order by...];
	--注意：其中group by子句指定按照哪些字段分组，having子句设定分组查找条件
```

### 8.联合查询 UNION:

```sql
可以将两条查询语句合并，然后降重，例如左外连接查询union右外连接查询,左外连接查询和右外连接查询重复的部分是内连
接查询，使用union后结果中就不会显示双份的内连接查询的结果
```

### 9.分组查询

```sql
--关键字group by表时按照什么分组
--count可以统计个数
--sum(...)  max(...)  min(...)
--group_concat可以合并各组某一列的所有成员，显示在一行
select professional_title, group_concat(name), count(professional_title) from tutor group by professional_title;
/*
+--------------------+--------------------+---------------------------+
| professional_title | group_concat(name) | count(professional_title) |
+--------------------+--------------------+---------------------------+
| 副教授             | 李导师             |                         1 |
| 博士               | 张导师             |                         1 |
| 教授               | 赵导师,吴导师      |                         2 |
| 讲师               | 王导师             |                         1 |
+--------------------+--------------------+---------------------------+
*/
```

### 10.模糊查询

```sql
--关键字:like
具体的查询要配合通配符
--1.	%	可以匹配任意的数量
	select * from tb_name where name like "a%";
	则只要是名字以a开头的都会找出来
	
--2.	_	只能匹配一个字符
	select * from tb_name where name like 'a_';
	则只有名字以a开头,且只有两个字母的才会找出来

```



## 约束

```sql
主键名：如果自己不设置，系统会给一个默认的主键名
--查询主键名

```



### 定义约束

```sql
--1.定义主键约束
  primary key:不允许为空，不允许重复
	删除主键	   alter table tablename drop primary key ;
	定义主键自动增长	auto_increment
--2.定义唯一约束	unique 可以为null，但只要不为null的值就必须是唯一的不重复的
--3.定义非空约束	not null
--4.定义外键约束	一般写在创建表格的语句最后面
	create table tb_name(
        变量1，
        变量2，
        变量3，
        ...
        变量n，
        constraint constraint_FK_name foreign key (ordersid) references orders(id)
	);
	
	--实例
	create table graduate_stu (
    id int primary key auto_increment,
    name varchar(30),
    gender varchar(1),
    enrollment_date date,
    tutor_id int,
    constraint stu_tutor_fk foreign key (tutor_id) references tutor(id)  
);


```

### 后期添加键约束

```sql
alter create table tb_name add constraint constraint_FK_name foreign key (ordersid) references orders(id);
--解释：constraint 约束 
constraint_FK_name 给这个约束起一个名字，一般是表一的名字_表二的名字_FK,FK表式foreign key,这一段可以不写，系统就会给个默认的
foreign key	表时这是一个外键类型
(ordersid)这是本表需要添加外键的列名，括号不可省略
references 参考、引用，表明后面那个就是外表目标,注意是由s的
orders(id)：orders是表二的名字，id就是关联到的表二中的某一列的列名

--添加主键约束
alter table 表名
add constraint [主键名] primary key (主键)
--实例
Alter table tb add primary key(id);	//非自动增长的


---添加唯一约束
alter table 表名
add constraint 约束名 unique (字段)

alter table coolq_auth
add constraint  unique (`qq`)

---添加默认约束
alter table 表名
add constraint 约束名 default ('默认内容') for 字段

--添加检查check约束,要求字段只能在1到100之间
alter table 表名
add constraint 约束名 check (字段 between 1 and 100 )

---添加外键约束(主表stuInfo和从表stuMarks建立关系,关联字段为stuNo)
alter table 从表
add constraint 约束名
foreign key(关联字段) references 主表(关联字段)
```

### 删除约束

```sql
alter table tb_name drop constraint 约束名

sp_helpconstraint 表名 找到数据表中的所有列的约束

--删除自增长的主键id
先删除自增长再删除主键
Alter table tb_name drop primary key;//删除主建
```

外键约束

```
限定某一列的值只能是某个其他表中的某一列的成员之一，例如一个商品表，一个商品分类，再填写商品的具体类别时必须是已有的商品分类中的某一个，而不可以自己新建一个表了
FOREIGN KEY (Id) REFERENCES Persons(P_Id)
创建一个外键的列ID,这个ID是引用Persons表中的P_ID的，那么创建之后，对于ID这一列的值，必须是在person这个表的P_ID中出现过才可以用
实例
create table class (
	class_name varchar(20),
	class_id int
)

create student (
	name varchar(20),
	id int primary key,
	foreign key class_id_fk preferences class(class_id)	#那么此处就会添加一列叫class_id_fk的列，这一列的所有成员的值必须是在class表中的class_id中出现过才可以用
)

假设class 表中有
class_name 		class_id
一班				10001
二班				10002
三班				10003

student:
name	id		class_id_fk
张三	   1		10001
李四	   2		10003
王五	   3		10003
赵六	   4		10008

那么赵六就会无法创建，应为在class表格中没有一个class_id是10008，可选项只有10001、10002、10003
```

## 数据类型

### **1.整型**

注意：这里以MySQL为例，不同的DBMS的都支持数值类型，字符串类型以及日期类型，但他们的实现可能不一样。

| **数据类型** | **占用字节** | **说明**       |
| ------------ | ------------ | -------------- |
| TINYINT      | 1            | 很小的整数     |
| SMALLINT     | 2            | 小的整数       |
| MEDIUMINT    | 3            | 中等大小的整数 |
| INT          | 4            | 普通大小的整数 |
| BIGINT       | 8            | 大整数         |



### **2.浮点型**

| **类型名称** | **占用字节** | **说明**     |
| ------------ | ------------ | ------------ |
| FLOAT(M,D)   | 4            | 单精度浮点数 |
| DOUBLE(M,D)  | 8            | 双精度浮点数 |
| DECIMAL(M,D) | M+2          | 定点数       |

其中 M 称为精度，表示总共的位数; 

​           D 称为标度，表示小数的位数。

DECIMAL 类型不同于 FLOAT & DOUBLE，DECIMAL 实际是以字符串存放的，它的存储空间并不固定，而是由精度 M 决定的。

最后一项的长度并不确定，会根据运算的需求自动调整，可以保障结果不会受到精度的影响



### **3.日期和时间：**

| **类型名称** | **日期格式**             | **占用字节** |
| ------------ | ------------------------ | ------------ |
| YEAR         | YYYY           (2018)    | 1            |
| TIME         | HH:MM:SS    (10:20:00)   | 3            |
| DATE         | YYYY-MM-DD   (2018-7-23) | 3            |
| DATETIME     | YYYY-MM-DD   HH:MM:SS    | 8            |
| TIMESTAMP    | YYYY-MM-DD   HH:MM:SS    | 4            |

DATETIME 和 TIMESTAMP 虽然显示的格式是一样的，但是它们有很大的区别：

1.DATETIME 的系统默认值是 NULL, 而 TIMESTAMP 的系统默认值是 当前时间 NOW();

2.DATETIME 存储的时间与时区无关，而TIMESTAMP 与时区有关



### **4.字符串类型**

| **类型名称** | **占用字节**                                       | **说明**             |
| ------------ | -------------------------------------------------- | -------------------- |
| CHAR(M)      | M,   1 <= M <= 255                                 | 固定长度字符串       |
| VARCHAR(M)   | L+1,   L   <=M, 1 <=M <=255                        | 变长字符串           |
| TINYTEXT     | L+1,   L < 2^8                                     | 非常小的文本字符串   |
| TEXT         | L+2,   L < 2^16                                    | 小的文本字符串       |
| MEDIUMTEXT   | L+3,   L < 2^24                                    | 中等大小的文本字符串 |
| LONGTEXT     | L+4,   L < 2^32                                    | 大的文本字符串       |
| ENUM         | 1 或者   2个字节，取决于枚举的数目，最大   65535个 | 枚举类型             |
| SET          | 1,2,3,4或8个字节                                   | 集合类型             |

ENUM 类型总有一个默认值，当ENUM列声明为NULL，则默认值为NULL。如果ENUM列被声明为 NOT NULL，则其默认值为列表的第一个元素。

使用中建议使用VARCHAR而不是CHAR



### **5.常见运算符**

| **运算符**    | **作用**       | **运算符**    | **作用**     |
| ------------- | -------------- | ------------- | ------------ |
| =             | 等于           | <=>           | 安全的等于   |
| <>   (!=)     | 不等于         | <=            | 小于等于     |
| >=            | 大于等于       | >             | 大于         |
| IS   NULL     | 是否为NULL     | IS   NOT NULL | 是否不为NULL |
| BETWEEN   AND | 是否在闭区间内 | IN            | 是否在列表内 |
| NOT   IN      | 是否不在列表内 | LIKE          | 通配符匹配   |



```
逻辑运算符
      NOT(!)       AND(&&)      OR(||)     
位操作运算符
      &     |     ~     ^     <<     >>
```



## 三范式

```sql
1.第一范式（1NF）：（每列保持原子性,列不可拆分）
        如果数据库中的所有字段都是不可分割的原子值，则说明该数据库满足第一范式，用户的收货地址
        例如：地址  中国安徽合肥长安校区
        就可以拆分为    国家：中国  省份：安徽省    市级：合肥市    详细地址：长安小区
        这样有利于后期查表，但也有缺点：过于琐碎，当需要输出是也变得麻烦
        create table student1(
            id int primary key,
            name varchar(20),
            address varchar(30)
        )
        --student2就较为符合第一范式
        create table student2(
            id int primary key，
            name varchar(20)，
            country varchar(5)，
            province varchar(5),
            city varchar(5),
            detail_address varchar(10)
        )
2.第二范式（2NF）：（记录的唯一性）
        除主键外的每一列都必须完全依赖于主键 。表：学号、课程号、姓名、学分;
        学分依赖课程，姓名依赖学号
        --个人理解：保证一个表中只有一个主键或者联合主键，然后其他列成员是根据主键唯一确定的(主键是不重复的)
        	 不满足就可以通过拆表完成
3.第三范式（3NF）：（字段不要冗余）
        表: 学号, 姓名, 年龄, 学院名称, 学院电话，不要存在传递依赖
        /*
        先要满足第二范式，然后除开主键所在列，其他列之间不能存在依赖关系
        例如订单序列里有产品和客户两大类，主键是id
        然后客户下面还有电话、姓名等，此处就不能把客户的相关信息都放到订单那个表中
        而应当在建一个客户表，将客户相关信息同客户id关联起来
        */
```



## 数据备份

```
数据库备份：cmd命令下
mysqldump -u root -p db_name [tb_name1 tb_name2]>test.sql
备份多个数据库
mysqldump -u username -p --databases db_name1 db_name2 > Backup.sql

数据库恢复：
	创建一个空的数据库
	进入这个数据库	use db_name;
	导入数据库：source ...sql
```



## 问题总结

```sql
--问题1：Access denied for user 'root'@'localhost' (using password:YES)
解决方法：https://blog.csdn.net/yaoqiwaimai/article/details/77727474
在这个网址，要注意在mysql中为了安全，已把user表中的passward字段改为了authentication_String,所以使用网址
中的语句时要注意替换。
具体步骤
1.打开MySQL目录下的my.ini文件，在文件的最后添加一行“skip-grant-tables”，保存并关闭文件。（WIN7默认安装，
my.ini在C:\ProgramData\MySQL\MySQL Server 5.6
2.重启MySQL服务。
3.通过命令行进入MySQL的BIN目录，输入“mysql -u root -p”(不输入密码)，回车即可进入数据库。（WIN7默认安装，
BIN目录为：C:\Program Files\MySQL\MySQL Server 5.6\bin）
4.执行“use mysql;”，使用mysql数据库。
5.执行“update user set password=PASSWORD("123456") where user='root';”（修改root的密码）	//这是5.7
版本之前的
对于5.7版本的是	update user set authentication_string=PASSWORD("123456") where user='root';
6.刷新flush privileges;
7.退出，然后删除第一步加入的东西。

--问题2：查找前几位或者后几位
可以先用order asc或者desc排序，然后用limit offset, nums;选择只显示某几位，如只显示第一位
select * from tb_name order by column desc limit 0, 1;
```



```sql
1.编码问题
去如下地址查看如何修改数据库、数据表、表字段(列)的编码格式，以及设置默认的编码方式：
https://blog.csdn.net/sun8112133/article/details/79919145
https://www.cnblogs.com/geaozhang/p/6724393.html

--查看数据库的编码
show variables like 'character%';

--查看某个具体的库或表的编码方式：
show create database DB_name;
show create table tb_name;

--修改数据库和表的默认字符集,主要i数据库中utf8只是实际的utf8的一个子集，推荐用 utf8mb4
alter database db_name default character set utf8mb4;//修改数据库的字符集
alter table tb_name default character set utf8mb4;//修改表的字符集
--将某个表转换为某个字符集
ALTER TABLE tb_name CONVERT TO CHARACTER SET charset_name;
```

2.新建表的某一项列名或表名和关键字重复****

```sql
可以用``将该项包进去，这个反引号是数字键1左边的那个，如下面的 desc 
create table goods (
    id int primary key auto_increment,
    `desc` varchar(50),
    img varchar(100),
    name varchar(20),
    typeId int,
    unitPrice int,
    stockNum int
);
```

3.条件判断某项是否为null

```mysql
select * from tb_name where col_name is null;
select * from tb_name where col_name is not null;
```

4.SSL连接问题

```java
Tue Sep 24 20:23:59 CST 2019 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.

如上报错可以在url的末尾添加
useSSL=true
url="jdbc:mysql://localhost:3306/db_name?useUnicode=true&characterEncoding=utf-8&useSSL=true"

```

