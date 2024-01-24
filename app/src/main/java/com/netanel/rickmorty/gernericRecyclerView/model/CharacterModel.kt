package com.netanel.rickmorty.gernericRecyclerView.model

import com.netanel.rickmorty.R
import com.netanel.rickmorty.domain.model.character.Character
import com.netanel.rickmorty.gernericRecyclerView.Model


/**
 * Created by netanelamar on 10/01/2024.
 * NetanelCA2@gmail.com
 */
data class CharacterModel(val character: Character) : Model {

    val getSpeciesImg: Int
        get() {
            return when (character.species) {
                Character.Species.Alien -> {
                    R.drawable.ic_alien
                }

                Character.Species.Human -> {
                    if (character.gender == Character.Gender.Male) R.drawable.ic_human_male else R.drawable.ic_human_female
                }

                Character.Species.unknown -> {
                    R.drawable.ic_question_mark
                }
            }
        }


    val getBorderColor: Int
        get() {
            return when (character.status) {
                Character.Status.Alive -> {
                    R.color.green
                }

                Character.Status.Dead -> {
                    R.color.red
                }

                Character.Status.unknown -> {
                    R.color.grey
                }
            }
        }

    val getGenderImg: Int
        get() {
            return when (character.gender) {
                Character.Gender.Male -> {
                    R.drawable.ic_male
                }

                Character.Gender.Female -> {
                    R.drawable.ic_female
                }

                Character.Gender.unknown -> {
                    R.drawable.ic_question_mark
                }
            }
        }

    interface ClickListener {
        // TODO: Continue from here 23.01.24
        fun onClick(character: Character)
    }

    override fun getViewType(): Int = R.layout.item_character_model

}