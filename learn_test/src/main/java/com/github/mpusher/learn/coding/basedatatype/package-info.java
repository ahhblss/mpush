/**
 * Created by Administrator on 2017/7/5.
 */
package com.github.mpusher.learn.coding.basedatatype;

/*
If you are converting from a byte b to a char and you don't want sign extension,you
must use a bit mask to surpress it.This is a common idom，so no comment is necessary.
---// char c=(char)(b&0xff)
在java中，byte到char的转换不是直接进行的。
而是byte符号扩展转换到int，然后再从int转换到char。
byte b=-100;
b在内存中是以补码的形式存贮的:
1001 1100
如果执行char c=(char)b;
如3楼企鹅先生所说：b要先变为int，这时增加的位全要用b的符号位填充（这就是符号扩展），变为：
1111 1111 1111 1111 1111 1111 1001 1100
下步是强制类型转换，只保留了最低的两个字节：1111 1111 1001 1100。
如果执行char c=(char)(b&0xff),同样b要转为int ,同时符号位扩展：
1111 1111 1111 1111 1111 1111 1001 1100
再与0xff想与,
1111 1111 1111 1111 1111 1111 1001 1100
&0000 0000 0000 0000 0000 0000 1111 1111
-----------------------------------------
0000 0000 0000 0000 0000 0000 1001 1100
再强转为char,得: 0000 0000 1001 1100。这是一个正数。
*/
