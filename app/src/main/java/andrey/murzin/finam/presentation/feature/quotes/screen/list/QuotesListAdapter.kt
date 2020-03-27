package andrey.murzin.finam.presentation.feature.quotes.screen.list

import andrey.murzin.finam.R
import andrey.murzin.finam.presentation.model.QuoteInfoUiModel
import andrey.murzin.finam.presentation.model.SateParamType
import andrey.murzin.finam.presentation.model.getInfo
import andrey.murzin.finam.presentation.model.getPriceInfo
import andrey.murzin.finam.utils.diff.DiffUtilCallback
import andrey.murzin.finam.utils.toNumberWithSymbol
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_quote_info.view.*

class QuotesListAdapter : RecyclerView.Adapter<QuotesListAdapter.ViewHolder>() {

    private val data: MutableList<QuoteInfoUiModel> = mutableListOf()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_quote_info,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addData(data: List<QuoteInfoUiModel>) {
        val diff = DiffUtilCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diff)

        this.data.clear()
        this.data.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(quoteInfoUiModel: QuoteInfoUiModel) {
            with(containerView) {
                tvTicker.text = quoteInfoUiModel.c
                tvInfo.text = quoteInfoUiModel.getInfo()
                tvInfoPrice.text = quoteInfoUiModel.getPriceInfo()
                tvPCP.setData(quoteInfoUiModel.pcp)
                quoteInfoUiModel.pcp?.let {
                    it.state = SateParamType.INIT
                }
            }
        }
    }
}