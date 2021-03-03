# TODO LIST

- [x] sprint 1
- - [ ] push sprint 1 docs to github   

- [ ] sprint 2
- - [ ] (optional) extract USER model from DBHelper 

# 项目结构
```
- root
   - project # 项目的实际位置
   - docs    # 在这个文件夹下存放每个 sprint 用到的文档
```

## 项目 Java 包结构
```
- project/app/src/main/java
    - db     # 存放数据库对象
    - pojo   # 存放 DAO 层 POJO 对象
    - ui     
    - utils  # 存放辅助函数
```

# 可能有用的指南
## Q1 如何生成 POJO 类

1. 在空白处点击右键； 
2. 选中Generate.....； 
3. 然后选中Getter and Setter ； 
4. 最后选中所需要添加get和set的成员变量，点击OK就可以了!
5. 再次选中Generate.....； 
6. 然后选中Constructor ； 
7. 最后选中构造函数需要的成员变量，点击OK就可以了!

## Q2 创建一个新的数据库表要在哪里做些什么

1. 在 EnumTable.TABLE_LIST 里添加表名。
2. 在 EnumTable 里为新的表添加字段常量映射。
3. 新建一个类作为表的操作类，这个类继承 DBHelper。
4. 生成默认的构造函数，这个构造函数应该只需要 context 作为参数。
5. 重写 onCreate/onUpgrade 方法 (可以抄 Post.java)
6. 写一个静态的 CREATE 方法用于初始化表
7. 添加你需要的 CRUD 方法
