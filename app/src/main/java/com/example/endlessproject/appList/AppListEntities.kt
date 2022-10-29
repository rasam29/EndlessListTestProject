package com.example.endlessproject.appList

import com.google.gson.annotations.SerializedName

data class EndlessListResponse(

    @SerializedName("eol") var eol: Boolean? = null,
    @SerializedName("appPlusMetaDataList") var appPlusMetaData: ArrayList<AppPlusMetaData> = arrayListOf(),
    @SerializedName("shareText") var shareText: String? = null,
    @SerializedName("title")   var title: String? = null

)

data class AppPlusMetaData(

    @SerializedName("categorySummary") var categorySummary: CategorySummary? = CategorySummary(),
    @SerializedName("downloadSummary") var downloadSummary: DownloadSummary? = DownloadSummary(),
    @SerializedName("bannerPath") var bannerPath: String? = null,
    @SerializedName("categoryId") var categoryId: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("developerEmail") var developerEmail: String? = null,
    @SerializedName("diffCheckSum") var diffCheckSum: String? = null,
    @SerializedName("diffSize") var diffSize: String? = null,
    @SerializedName("downloadsText") var downloadsText: String? = null,
    @SerializedName("isBookmarked") var isBookmarked: Boolean? = null,
    @SerializedName("isDescriptionRtl") var isDescriptionRtl: Boolean? = null,
    @SerializedName("isVersionDescriptionRtl") var isVersionDescriptionRtl: Boolean? = null,
    @SerializedName("lastUpdate") var lastUpdate: String? = null,
    @SerializedName("mediaNews") var mediaNews: ArrayList<String> = arrayListOf(),
    @SerializedName("moveToTopRecommendation") var moveToTopRecommendation: String? = null,
    @SerializedName("permissions") var permissions: ArrayList<Permissions> = arrayListOf(),
    @SerializedName("purchaseStatus") var purchaseStatus: String? = null,
    @SerializedName("rates") var rates: ArrayList<String> = arrayListOf(),
    @SerializedName("reviews") var reviews: ArrayList<String> = arrayListOf(),
    @SerializedName("screenshots") var screenshots: ArrayList<Screenshots> = arrayListOf(),
    @SerializedName("versionDescription") var versionDescription: String? = null,
    @SerializedName("relatedAccounts") var relatedAccounts: ArrayList<String> = arrayListOf(),
    @SerializedName("isDesTranslatable") var isDesTranslatable: Boolean? = null,
    @SerializedName("isVerDesTranslatable") var isVerDesTranslatable: Boolean? = null,
    @SerializedName("iconHighRes") var iconHighRes: String? = null,
    @SerializedName("appType") var appType: String? = null,
    @SerializedName("hasAd") var hasAd: Boolean? = null,
    @SerializedName("actualSize") var actualSize: String? = null,
    @SerializedName("androidVersion") var androidVersion: String? = null,
    @SerializedName("aparatVideoId") var aparatVideoId: String? = null,
    @SerializedName("categoryName") var categoryName: String? = null,
    @SerializedName("developerId") var developerId: String? = null,
    @SerializedName("developerName") var developerName: String? = null,
    @SerializedName("englishTitle") var englishTitle: String? = null,
    @SerializedName("farsiTitle") var farsiTitle: String? = null,
    @SerializedName("fileSize") var fileSize: Long? = null,
    @SerializedName("hasBanner") var hasBanner: Boolean? = null,
    @SerializedName("hasIAP") var hasIAP: Boolean? = null,
    @SerializedName("hasMainData") var hasMainData: Boolean? = null,
    @SerializedName("hasMediaReview") var hasMediaReview: Boolean? = null,
    @SerializedName("hasPatchData") var hasPatchData: Boolean? = null,
    @SerializedName("iconPath") var iconPath: String? = null,
    @SerializedName("isFree") var isFree: Boolean? = null,
    @SerializedName("isIncompatible") var isIncompatible: Boolean? = null,
    @SerializedName("packageName") var packageName: String? = null,
    @SerializedName("palette") var palette: ArrayList<String> = arrayListOf(),
    @SerializedName("priceInCurrency") var priceInCurrency: PriceInCurrency? = PriceInCurrency(),
    @SerializedName("price") var price: String? = null,
    @SerializedName("rating") var rating: Float? = null,
    @SerializedName("totalRating") var totalRating: Float? = null,
    @SerializedName("realPriceInCurrency") var realPriceInCurrency: RealPriceInCurrency? = RealPriceInCurrency(),
    @SerializedName("supportMoneyBack") var supportMoneyBack: Boolean? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("version") var version: String? = null,
    @SerializedName("versionCode") var versionCode: Int? = null,
    @SerializedName("badge") var badge: Badge? = Badge()

)

data class CategorySummary(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("iconUrl") var iconUrl: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("layoutKey") var layoutKey: String? = null

)

data class DownloadSummary(

    @SerializedName("foregroundColor") var foregroundColor: String? = null,
    @SerializedName("backgroundColor") var backgroundColor: String? = null,
    @SerializedName("unitText") var unitText: String? = null,
    @SerializedName("count") var count: String? = null

)

data class Permissions(

    @SerializedName("title") var title: String? = null

)

data class Screenshots(

    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("thumbnailUrl") var thumbnailUrl: String? = null,
    @SerializedName("mainUrl") var mainUrl: String? = null

)

data class PriceInCurrency(

    @SerializedName("currency") var currency: String? = null,
    @SerializedName("price") var price: Int? = null

)

data class RealPriceInCurrency(

    @SerializedName("currency") var currency: String? = null,
    @SerializedName("price") var price: Int? = null

)

data class Badge(

    @SerializedName("hasBadge") var hasBadge: Boolean? = null

)
