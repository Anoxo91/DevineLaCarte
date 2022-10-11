package org.sio.slam.devine

import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.core.Jeu
import org.sio.slam.devine.core.Paquet
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte
import org.sio.slam.devine.enum.getCouleurCarteFromString
import org.sio.slam.devine.enum.getNomCarteFromString
import org.sio.slam.devine.fabrique.createJeu32Cartes
import org.sio.slam.devine.fabrique.createJeu52Cartes

fun main(args: Array<String>) {

    println("Voulez vous activer l'aide  ?")
    var reponse: String = readLine() + ""
    var aide = false
    do {
        if (reponse.trim().uppercase() != "OUI" && reponse.trim().uppercase() != "NON") {
            println("Je n'ai pas bien compris, veuillez réessayer :")
            reponse = readLine() + ""
        }
        if (reponse.trim().uppercase() == "OUI") {
            aide = true
            println("L'aide est activé")
        }
        if (reponse.trim().uppercase() == "NON") {
            println("L'aide est désactivé")
        }
    } while (reponse.trim().uppercase() != "OUI" && reponse.trim().uppercase() != "NON")

    println("Paquet de 32 cartes ou 52 cartes ?")

    var repNombreCarteJeu: String = readLine() + ""
    var paqueDeCartes = Paquet()
    do {
        if (repNombreCarteJeu.trim() != "32" && repNombreCarteJeu.trim() != "52") {
            println("Je n'ai pas bien compris, veuillez réessayer :")
            repNombreCarteJeu = readLine() + ""
        }
        if (repNombreCarteJeu.trim() == "32") {
            paqueDeCartes = Paquet(createJeu32Cartes())
            println("Creation d'un packet de 32 cartes")
        }
        if (repNombreCarteJeu.trim() == "52") {
            paqueDeCartes = Paquet(createJeu52Cartes())
            println("Creation d'un packet de 52 cartes")
        }
    } while (repNombreCarteJeu.trim() != "32" && repNombreCarteJeu.trim() != "52")

    println(" ==== Instanciation du jeu, début de la partie. ====")
    val jeu = Jeu(avecAide = aide, paquet = paqueDeCartes)
    var nbTry = 0
    var abandonner = false
    do {
        var repeat = 0
        nbTry++
        println("Entrez un nom de carte dans le jeu (exemples : Roi, sept, As...) :")
        // TODO (optionnel) permettre de saisir un chiffre au lieu d'une chaine : 7 au lieu de Sept...
        val nomCarteUserStr: String = readLine() + "" //7
        val nomCarteUser: NomCarte? = getNomCarteFromString(nomCarteUserStr.trim().uppercase())

        println("Entrez un nom de \"couleur\" de carte parmi : Pique, Trefle, Coeur, Carreau : ")
        val couleurCarteUserStr: String = readLine() + ""
        val couleurCarteUser: CouleurCarte? = getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase())

        if (nomCarteUser != null && couleurCarteUser != null) {
            // prise en compte de la carte du joueur
            val carteDuJoueur = Carte(nomCarteUser, couleurCarteUser)

            if (jeu.isMatch(carteDuJoueur)) {
                println("Bravo, vous avez trouvé la bonne carte !")
                repeat++
            } else {
                println("Ce n'est pas la bonne carte !")
                println("votre proposition  : $carteDuJoueur")
                if (aide) {
                    if (carteDuJoueur > jeu.carteADeviner) {
                        println("la carte à deviner est plus petite")
                    } else {
                        println("la carte à deviner est plus grande")
                    }
                }
            }
        } else {
            // utilisateur a mal renseigné sa carte
            val nomCarte = if (nomCarteUserStr == "") "?" else nomCarteUserStr
            val couleurCarte = if (couleurCarteUserStr == "") "?" else couleurCarteUserStr
            nbTry--
            println("Désolé, mauvaise définition de carte ! (${nomCarte} de ${couleurCarte})")
        }
        if (repeat == 0) {
            println("voulez vous abandonner ? ")//${jeu.carteADeviner}
            var rep: String = readLine() + ""

            do {
                if (rep.trim().uppercase() != "OUI" && rep.trim().uppercase() != "NON") {
                    println("Je n'ai pas bien compris, veuillez réessayer :")
                    rep = readLine() + ""
                }
                if (rep.trim().uppercase() == "OUI") {
                    repeat++
                    abandonner=true
                }
                if (rep.trim().uppercase() == "NON") {
                    println("")
                }
            } while (rep.trim().uppercase() != "OUI" && rep.trim().uppercase() != "NON")
        }
    } while (repeat != 1)

    println(" ==== Fin de la partie ====")
    println("La carte a deviner etait ${jeu.carteADeviner}")
    println("Votre stratégie de jeu : ${jeu.strategiePartie(nbTry,abandonner)} ")
    println(" ==== Fin de la partie. ====")
}
