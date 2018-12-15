# hbase 相关知识
```
Here, we insert three values, one at a time. The first insert is at row1, column cf:a, with a value of value1. Columns in HBase are comprised of a column family prefix, cf in this example, followed by a colon and then a column qualifier suffix, a in this case.

put 'test', 'row1', 'cf:a', 'value1'

在这里，我们一次插入三个值。第一个插入位于第1行，列cf：a，值为value1。 HBase中的列由列族前缀组成，在此示例中为cf，后跟冒号，然后是列限定符后缀，在本例中为a。

列族前缀：限定符    value
```