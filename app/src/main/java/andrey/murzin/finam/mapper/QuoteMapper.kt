package andrey.murzin.finam.mapper

import andrey.murzin.finam.data.model.QuoteInfoModel
import andrey.murzin.finam.domain.entity.QuoteInfoEntity
import andrey.murzin.finam.presentation.model.ParamItem
import andrey.murzin.finam.presentation.model.QuoteInfoUiModel

fun QuoteInfoModel.toEntity(): QuoteInfoEntity =
    QuoteInfoEntity(
        c,
        pcp ,
        ltr,
        name,
        ltp,
        chg
    )

fun List<QuoteInfoModel>.toEntity() =
    map { it.toEntity() }

fun QuoteInfoEntity.toUiModel(): QuoteInfoUiModel =
    QuoteInfoUiModel(
        c,
        ParamItem(pcp),
        ltr,
        name,
        ltp,
        chg
    )

fun List<QuoteInfoEntity>.toUiModel() =
    map { it.toUiModel() }