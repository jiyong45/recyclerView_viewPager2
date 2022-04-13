package com.example.recyclerview_viewpager2


import com.google.gson.annotations.SerializedName

data class JsonData(
    @SerializedName("favoriteBrandM")
    var favoriteBrandM: FavoriteBrandM?
) {
    data class FavoriteBrandM(
        @SerializedName("moreUrl")
        var moreUrl: String?,
        @SerializedName("tabList")
        var tabList: List<Tab?>?,
        @SerializedName("title")
        var title: String?
    ) {
        data class Tab(
            @SerializedName("list")
            var list: List<Product>?,
            @SerializedName("moreUrl")
            var moreUrl: String?,
            @SerializedName("tabTitle")
            var tabTitle: String?
        ) {
            data class Product(
                @SerializedName("brandNm")
                var brandNm: String?,
                @SerializedName("goodsNm")
                var goodsNm: String?,
                @SerializedName("goodsNo")
                var goodsNo: String?,
                @SerializedName("imgPath")
                var imgPath: String?,
                @SerializedName("likeYn")
                var likeYn: Boolean?
            )
        }
    }
}