package com.example.myapplication.ui.carddetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentCardDetailBinding
import com.example.myapplication.model.data.card.Card
import com.example.myapplication.model.data.card.User
import com.example.myapplication.ui.adapter.CardAdapter
import com.example.myapplication.ui.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_card_detail.*

class CardDetailFragment : BaseFragment(), CardDetailView {
    companion object {
        const val CARD_ID = "id"
    }
    private lateinit var cardAdapter: CardAdapter
    private lateinit var userAdapter: UserAdapter

    private lateinit var presenter: CardDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = CardDetailPresenter()
        presenter.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentCardDetailBinding>(inflater, R.layout.fragment_card_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter()
        val recyclerUsers = layout_user.findViewById<RecyclerView>(R.id.recycler_users)
        recyclerUsers.adapter = userAdapter
        recyclerUsers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        cardAdapter = CardAdapter(getBaseActivity())
        val recyclerCards = layout_recommend_card.findViewById<RecyclerView>(R.id.recycler_cards)
        recyclerCards.adapter = cardAdapter
        recyclerCards.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val cardId = arguments?.getInt(CardDetailFragment.CARD_ID) ?: -1
        if (cardId != -1) presenter.getCardDetail(cardId)
    }

    override fun setCard(card: Card) {
        val cardImage = layout_card.findViewById<ImageView>(R.id.image_card)
        val cardDescription = layout_card.findViewById<TextView>(R.id.card_description)
        Glide.with(cardImage.context).load(card.imgUrl).apply(RequestOptions.centerCropTransform()).into(cardImage)
        cardDescription.text = card.description

        cardImage.setOnClickListener {
            val fragment = CardDetailFragment()
            val bundle = Bundle()
            bundle.putInt(CardDetailFragment.CARD_ID, card.id)
            fragment.arguments = bundle
            getBaseActivity()?.addFragment(R.id.container, fragment)
        }
    }

    override fun setUser(user: User) {
        userAdapter.addItem(user)
    }

    override fun setRecommendCards(cards: ArrayList<Card>) {
        cardAdapter.addItems(cards)
    }
}