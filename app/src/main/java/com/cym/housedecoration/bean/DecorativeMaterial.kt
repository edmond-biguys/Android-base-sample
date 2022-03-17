package com.cym.housedecoration.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class DecorativeMaterial(
    var id: Int = 0,
    var title: String = "",
    var category: String = "", //装修材料、物品的分类
    var unitPrice: BigDecimal = 0.0.toBigDecimal(),  //单价
    var totalPayedPrice: BigDecimal = 0.0.toBigDecimal(), //已支付的金额
    var payedPriceList: List<String> = mutableListOf(), //已支付的过程钱列表
    var totalPrice: BigDecimal = 0.0.toBigDecimal(),

    var processUnitPrice: BigDecimal = 0.0.toBigDecimal(), //加工单价
    var processCount: Double = 0.0, //加工总数
    var processUnit: String = "", //加工内容单位名称，如按米、平方米计价

    var installUnitPrice: BigDecimal = 0.0.toBigDecimal(), //安装单价
    var installCount: Double = 0.0, //安装总数
    var installUnit: String = "", //安装内容单位名称，如按米、平方米计价

    var count: Double = 1.0, //购买数量，按实际单位来
    var unit: String = "", //购买物品计价单位
    var createDate: Date = Date(), //购买日期
    var isAlternative: Boolean = false, //备选
    var desc: String = "",   //商品描述
    var imageIcon: String = "", //购买商品缩略图
    var imageDetailList: List<String> = mutableListOf(), //商品相关图片
): Parcelable {
    private val decimalFormat = DecimalFormat("0.00")
    fun getTotalPriceDisplay(): String {
        return decimalFormat.format(totalPrice)
    }


    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    fun getCreateDateDisplay(): String {
        return dateFormat.format(createDate)
    }

    companion object {
        private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        fun mockData(): List<DecorativeMaterial> {
            val list = mutableListOf<DecorativeMaterial>()

            //2021.11.06 定金16000
            //2022.01.12 20000
            list.add(
                DecorativeMaterial(
                    id = 1,
                    title = "铝合金窗户", category = "窗户",
                    unitPrice = 800.00.toBigDecimal(), totalPayedPrice = 36000.0.toBigDecimal(), totalPrice = 48240.0.toBigDecimal(),
                    count = 60.3, unit = "平方米", createDate = dateFormat.parse("2021-11-06")!!, desc = "铝合金窗户推拉窗（厚10.8），单价800，共60.3平..."))

            list.add(
                DecorativeMaterial(
                    id = 2,
                    title = "卫生间铝合金窗户（内置百叶）", category = "窗户",
                    unitPrice = 1200.00.toBigDecimal(), totalPayedPrice = 0.0.toBigDecimal(), totalPrice = 4200.0.toBigDecimal(),
                    count = 3.5, unit = "平方米", createDate = dateFormat.parse("2021-11-06")!!, desc = "卫生间铝合金窗户推拉窗（内置百叶，厚10.8），单价1200，共3.5平..."))
            list.add(
                DecorativeMaterial(
                    id = 3,
                    title = "铝合金窗户开启扇", category = "窗户",
                    unitPrice = 450.00.toBigDecimal(), totalPayedPrice = 0.0.toBigDecimal(), totalPrice = 2250.0.toBigDecimal(),
                    count = 5.0, unit = "个", createDate = dateFormat.parse("2021-11-06")!!, desc = "铝合金窗户推拉窗，超过赠送部分开启扇，单价450，共5个..."))


            //2022-01-03，定金20000，收款方 欧神诺
            //2022-02-24，过程款10000，收款方 语妍建材经营部
            list.add(
                DecorativeMaterial(
                    id = 4,
                    title = "欧神诺瓷砖", category = "瓷砖",
                    unitPrice = 0.0.toBigDecimal(), totalPayedPrice = 30000.0.toBigDecimal(), totalPrice = 50000.0.toBigDecimal(),
                    count = 0.0, unit = "平方米", createDate = dateFormat.parse("2022-01-03")!!, desc = "包括地砖、墙砖、楼梯砖、卫生间地面、卫生间墙面、电视机背景墙、..."))

            //大门、后门
            list.add(
                DecorativeMaterial(
                    id = 5,
                    title = "不锈钢大门", category = "门",
                    unitPrice = 1500.00.toBigDecimal(), totalPayedPrice = 5000.0.toBigDecimal(), totalPrice = 15600.0.toBigDecimal(),
                    count = 10.4, unit = "平方米", createDate = dateFormat.parse("2022-02-20")!!, desc = "不锈钢大门，单价1500，共10.4平..."))
            return list
        }
    }

}
