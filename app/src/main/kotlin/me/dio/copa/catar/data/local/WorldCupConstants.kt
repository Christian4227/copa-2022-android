package me.dio.copa.catar.data.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import me.dio.copa.catar.R

data class Country(
    @StringRes val name: Int,
    @DrawableRes val flagRes: Int
)

object WorldCupData {
    val countries = listOf(
        Country(R.string.text_germany, R.drawable.deu),
        Country(R.string.text_saudi_arabia, R.drawable.sau),
        Country(R.string.text_argentina, R.drawable.arg),
        Country(R.string.text_australia, R.drawable.aus),
        Country(R.string.text_belgium, R.drawable.bel),
        Country(R.string.text_brazil, R.drawable.bra),
        Country(R.string.text_cameroon, R.drawable.cmr),
        Country(R.string.text_canada, R.drawable.can),
        Country(R.string.text_qatar, R.drawable.qat),
        Country(R.string.text_south_korea, R.drawable.kor),
        Country(R.string.text_costa_rica, R.drawable.cri),
        Country(R.string.text_croatia, R.drawable.hrv),
        Country(R.string.text_denmark, R.drawable.dnk),
        Country(R.string.text_ecuador, R.drawable.ecu),
        Country(R.string.text_spain, R.drawable.esp),
        Country(R.string.text_united_states, R.drawable.usa),
        Country(R.string.text_france, R.drawable.fra),
        Country(R.string.text_ghana, R.drawable.gha),
        Country(R.string.text_netherlands, R.drawable.nld),
        Country(R.string.text_england, R.drawable.gbr), // Usando gbr para Inglaterra
        Country(R.string.text_iran, R.drawable.irn),
        Country(R.string.text_japan, R.drawable.jpn),
        Country(R.string.text_morocco, R.drawable.mar),
        Country(R.string.text_mexico, R.drawable.mex),
        Country(R.string.text_wales, R.drawable.gbr), // Usando gbr como base para Gales
        Country(R.string.text_poland, R.drawable.pol),
        Country(R.string.text_portugal, R.drawable.prt),
        Country(R.string.text_senegal, R.drawable.sen),
        Country(R.string.text_serbia, R.drawable.srb),
        Country(R.string.text_switzerland, R.drawable.che),
        Country(R.string.text_tunisia, R.drawable.tun),
        Country(R.string.text_uruguay, R.drawable.ury)
    )
}
