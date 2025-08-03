package com.somnathdey.wealth.domain.model

import com.google.gson.annotations.SerializedName
import com.somnathdey.wealth.data.remote.dto.Links
import com.somnathdey.wealth.data.remote.dto.Tag
import com.somnathdey.wealth.data.remote.dto.Team

data class CoinDetails(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<Tag>,
    val team: List<Team>,
    val links: Links,
    val logo: String,
    val author: String?,
    val orgStructure: String,
    val hashAlgorithm: String,
)
