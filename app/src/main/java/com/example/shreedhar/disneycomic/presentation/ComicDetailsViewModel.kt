package com.example.shreedhar.disneycomic.presentation

import com.example.shreedhar.disneycomic.base.BaseViewState
import com.example.shreedhar.disneycomic.base.MviViewModel
import com.example.shreedhar.disneycomic.domain.usecase.GetComicDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    private val getComicDetails: GetComicDetails,
) : MviViewModel<BaseViewState<ComicDetailViewState>, ComicDetailEvent>() {
    override fun onTriggerEvent(eventType: ComicDetailEvent) {
        when (eventType) {
            is ComicDetailEvent.LoadDetail -> onLoadDetail(eventType.comicId)
        }
    }

    private fun onLoadDetail(comicId: Int) = safeLaunch {
        execute(getComicDetails(comicId)) { comicDetails ->
            setState(BaseViewState.Data(ComicDetailViewState(comicDto = comicDetails)))
        }
    }

}