# 图数据库neo4j 相关知识点
## 语法

### 创建节点和关系，Person标签，这里的ee只是一个变量
    MATCH (ee:Person) WHERE ee.name = "Emil"
    CREATE (js:Person { name: "Johan", from: "Sweden", learn: "surfing" }),
    (ir:Person { name: "Ian", from: "England", title: "author" }),
    (rvb:Person { name: "Rik", from: "Belgium", pet: "Orval" }),
    (ally:Person { name: "Allison", from: "California", hobby: "surfing" }),
    (ee)-[:KNOWS {since: 2001}]->(js),(ee)-[:KNOWS {rating: 5}]->(ir),
    (js)-[:KNOWS]->(ir),(js)-[:KNOWS]->(rvb),
    (ir)-[:KNOWS]->(js),(ir)-[:KNOWS]->(ally),
    (rvb)-[:KNOWS]->(ally)

### 查询关系为KNOWS的信息

    MATCH p=()-[r:KNOWS]->() RETURN p LIMIT 25

    "p"                                                                                             │
    ╞════════════════════════════════════════════════════════════════════════════════════════════════╡
    │[{"name":"ldy","from":"china","age":22},{"since":2001},{"name":"Johan","learn":"surfing","from":│
    │"Sweden"}]                                                                                      │
    ├────────────────────────────────────────────────────────────────────────────────────────────────┤
    │[{"name":"Ian","title":"author","from":"England"},{},{"name":"Johan","learn":"surfing","from":"S│
    │weden"}]          

    解释：
    1.这里的p 为匹配的结果 赋值语句
    2.返回的为 节点 关系 节点  关系可能没有属性，所以就是{}

## 查询所熟悉的人

    MATCH (ee:Person)-[:KNOWS]-(friends)
    WHERE ee.name = "ldy" RETURN ee, friends

    ╒══════════════════════════════════════╤══════════════════════════════════════════════════╕
    │"ee"                                  │"friends"                                         │
    ╞══════════════════════════════════════╪══════════════════════════════════════════════════╡
    │{"name":"ldy","from":"china","age":22}│{"name":"Ian","title":"author","from":"England"}  │
    ├──────────────────────────────────────┼──────────────────────────────────────────────────┤
    │{"name":"ldy","from":"china","age":22}│{"name":"Johan","learn":"surfing","from":"Sweden"}│
    └──────────────────────────────────────┴──────────────────────────────────────────────────┘
    解释：
    1.这里的friends 为别名，注意这里不能用赋值语句，返回两列数据


## 推荐
    查询爱好相同的人
    MATCH (js:Person)-[:KNOWS]-()-[:KNOWS]-(surfer)
    WHERE js.name = "Johan" AND surfer.hobby = "surfing"
    RETURN DISTINCT surfer

    Pattern matching can be used to make recommendations. Johan is learning to surf, so he may want to find a new friend who already does:
    解释：
    ()empty parenthesis to ignore these nodes
    DISTINCTbecause more than one path will match the pattern
    surferwill contain Allison, a friend of a friend who surfs

