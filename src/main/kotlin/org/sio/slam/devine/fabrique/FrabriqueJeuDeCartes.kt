package org.sio.slam.devine.fabrique

import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte


/**
 * Création d'un paquet de 52 cartes
 * TODO Création d'un paquet de 52 cartes à implémenter (de 2 à AS)
 */
fun createJeu52Cartes(): List<Carte> {
    val list52Cartes = mutableListOf<Carte>()
    for (nomCarte in NomCarte.values()) {
        for (couleurCarte in CouleurCarte.values()) {
            list52Cartes.add(Carte(nomCarte, couleurCarte))
        }
    }
    return list52Cartes
}

fun createJeu32Cartes(): List<Carte> {
    val list32Cartes = mutableListOf<Carte>()
    for (nomCarte in NomCarte.values()) {
        for (couleurCarte in CouleurCarte.values()) {
            if (nomCarte.points >= 7) {
                list32Cartes.add(Carte(nomCarte, couleurCarte))
            }
        }
    }
    return list32Cartes
}



