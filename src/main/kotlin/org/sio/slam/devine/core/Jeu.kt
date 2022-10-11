package org.sio.slam.devine.core

import kotlin.math.log2

class Jeu(val avecAide: Boolean, val paquet: Paquet, paramCarteADeviner: Carte? = null) {
    val carteADeviner: Carte
        // le getter par défaut, inutile de le redéclarer (juste pour la démonstration)
        // field est ici synonyme de carteADeviner (implicite backing memory de la propriété)
        // REM : faire référence à carteADeviner au lieu de field entrainerait une récursion incontrôlée
        get() = field
    // set(value) { field = value } <== impossible car la propriété est en lecture seule (val)

    init {
        // si le paramètre paramCarteADeviner du constructeur a comme valeur null
        // alors on demande au paquet de nous fournir une carte à deviner
        // sinon on retient la valeur de carte transmise
        this.carteADeviner = paramCarteADeviner ?: this.paquet.getCarteADeviner()
    }

    /**
     * Permettre de savoir si la proposition de carte EST bien la carte à deviner, ou non
     */
    fun isMatch(carteProposee: Carte): Boolean {
        return this.carteADeviner == carteProposee
    }

    /**
     * Analyse la partie du joueur, a-t-il abandonné la partie,
     *  a-t-il trouvé la carte en un nombre de fois "convenable" ou "inconvenable",
     *  a-t-il eu de la chance ?
     */
    fun strategiePartie(nbTry: Int, abandonner: Boolean): String {
        if (!abandonner) {
            if (avecAide) {
                val logTry = log2(paquet.cartes.size.toDouble())
                if (nbTry.toDouble() >= logTry * 2) {
                    return "Vous pouvez encore vous améliorer. | nombre d'essais : $nbTry"
                } else if (nbTry.toDouble() >= logTry + 1 && nbTry.toDouble() < logTry * 2) {
                    return "Votre Strategies est bonne mais elle peux encore être meilleur. | nombre d'essais : $nbTry  "
                } else if (nbTry == logTry.toInt()) {
                    return "Vous etes un robot !"
                } else if (nbTry.toDouble() < logTry) {
                    return "Vous avez eu de la chance ! :D| nombre d'essais : $nbTry"
                }
            } else {
                val pourcentageDeChance: Double = (nbTry.toDouble() / paquet.cartes.size.toDouble()) * 100.00
                return if (nbTry / paquet.cartes.size <= 0.40) {
                    "bravo la chance est avec vous, vous n'aviez que ${pourcentageDeChance.toInt()}% de chance  | nombre d'essais : $nbTry"
                } else {
                    "décidément vous avez pas beaucoup de chance, vous aviez ${pourcentageDeChance.toInt()}% | nombre d'essais : $nbTry"
                }
            }
            return "il y a un problème de calcul | nombre d'essais : $nbTry"
        } else {
            return "pas de stratégie vous avez abandonné le jeu"
        }

    }
}
