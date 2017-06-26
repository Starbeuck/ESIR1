#include <cv.h>
// TODO : à compléter

#include "pluginmanager.h"

/* -----------------------------------------------------------
 * Ce programme est un *squelette minimal* à compléter
 * ------------------------------------------------------------*/
extern "C"
{
  int
  main()
  {
    // répertoire des plugins
    // TODO : passer le nom du répertoire en argument d'appel du programme
    //	      s'il n'est pas donné, le demander interactivement
    char repertoire_plugins[1024] = "plugins";

    // créer le gestionnaire de plugins
    // TODO : tester échec/succès
    plugin_manager * manager = make_manager();

    // l'image qui va servir pour les filtres
    cv::Mat image;

    // TODO : boucle infinie

    // découvrir (et enregistrer) les plugins du répertoire
    unsigned int nbp = discover_plugins(manager, repertoire_plugins);

    // afficher la liste des plugins
    display_menu(manager);

    // TODO : interaction utilisateur pour choisir le plugin à appeler
    //	      ou mettre fin au programme

    // accéder au plugin << choisi >> (1 seul plugin pour l'instant)
    plugin_descriptor * le_plugin = get_plugin(manager, 1);
    filter_function     le_filtre = le_plugin->m_filtre;
    printf("\nAppel du plugin : << %s >> ...", le_plugin->m_description);

    // exécuter la fonction du plugin
    // TODO : tester échec/succès
    le_filtre(image);

    // final clean :)
    release_manager(manager);
    return 0;
  }
};
