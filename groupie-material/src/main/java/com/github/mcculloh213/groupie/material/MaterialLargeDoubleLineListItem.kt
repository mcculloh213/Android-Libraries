package com.github.mcculloh213.groupie.material

import com.github.mcculloh213.groupie.data.DataContainer
import com.github.mcculloh213.groupie.data.atomicData
import com.github.mcculloh213.groupie.data.simpleData

data class MyData(val data: Int = 0)
class Test : MaterialSmallSingleLineListItem(""), DataContainer<MyData> by atomicData(MyData(5))
class Test2(data: MyData) : MaterialSmallSingleLineListItem(""), DataContainer<MyData> by simpleData(data)
class Test3(data: MyData) : MaterialSmallSingleLineListItem("") {
    val container: DataContainer<MyData> = atomicData(data)
}
