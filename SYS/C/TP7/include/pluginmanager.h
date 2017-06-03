#ifndef __PLUGINMANAGER__H
#define __PLUGINMANAGER__H

#include <cv.h>

extern "C" {

  //------------------------------------------------------------
  // les types
  //------------------------------------------------------------

  // fonction de réalisation du plugin
  // résultat : code erreur (0 pas d'erreur, non nul si erreur)
  typedef
  int (* filter_function)(cv::Mat & src);

  // type opaque d'une bibliothèque (voir dlopen)
  typedef
  void * libhandle;

  // descripteur de plugin
  typedef 
  struct {
    const char *    m_name;	   // nom du filtre
    const char *    m_description; // description de l'effet du filtre
    filter_function m_filtre;	   // fonction de réalisation du filtre
    libhandle	    m_lib;	   // la bibliothèque qui fournit le plugin
  } plugin_descriptor;

  // le type du gestionnaire
  typedef
  struct plugin_manager
  plugin_manager;
  // struct plugin_manager est une structure dont le contenu est à définir
  // dans pluginmanager.cc et qui n'est pas dévoilé
  // pour plus de détails sur ce typedef, voir
  //https://en.wikipedia.org/wiki/Struct_(C_programming_language)

  //------------------------------------------------------------
  // fonctions destinées à l'application
  //------------------------------------------------------------

  /**
   * initialiser un gestionnaire
   * @return manager initialisé
   */
  plugin_manager * make_manager();

  /**
   * libérer les ressources du gestionnaire
   * @param pm : le gestionnaire
   */
  void
  release_manager(plugin_manager * pm);

  /**
   * découvrir les plugins
   * @param dirname : répertoire où chercher les plugins
   * @param pm : le gestionnaire
   * @return nombre de plugins
   */
  unsigned int
  discover_plugins(plugin_manager * pm, const char dirname[]);

  /**
   * chercher et renvoyer le plugin de numéro donné
   * @param pm : le gestionnaire
   * @param plugin_number : numéro du plugin
   * @return le descripteur du plugin trouvé ; NULL si absent
   */
  plugin_descriptor *
  get_plugin(plugin_manager * pm, unsigned int plugin_number);

  /**
   * afficher le menu des plugins disponibles
   * @param pm : le gestionnaire
   */
  void
  display_menu(plugin_manager * pm);

  //------------------------------------------------------------
  // fonction destinée aux plugins
  //------------------------------------------------------------

  /**
   * enregistrer un nouveau plugin
   * @param pm : le gestionnaire
   * @param filter_name : nom du filtre
   * @param filter_description : description de son effet
   * @param the_filter : la fonction de filtrage
   * @param the_lib : la bibliothèque qui fournit le plugin
   */
  void
  register_plugin(plugin_manager * pm,
		  const char filter_name[],
		  const char filter_description[],
		  filter_function the_filter,
		  libhandle the_lib
		  );

};
#endif // __PLUGINMANAGER__H
