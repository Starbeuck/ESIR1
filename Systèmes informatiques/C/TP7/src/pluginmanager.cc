#include "pluginmanager.h"
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
// À COMPLÉTER
#include <dirent.h>
extern "C"
{

  //------------------------------------------------------------
  // les constantes
  //------------------------------------------------------------
  // suffixe des bibliothèques de plugin
  char plugin_suffix[] = "_plugin.so";
  // nom de la fonction d'initialisation d'un plugin
  char initfunc_name[] = "plugin_init";

  //------------------------------------------------------------
  // les types (À COMPLÉTER)
  //------------------------------------------------------------

  // type des éléments de la liste des plugins
  typedef
  struct chainon{
    plugin_descriptor m_plugin;
    struct chainon * m_next;
  } chainon;

  // le manager
  typedef
  struct plugin_manager{
    chainon * m_first;
    unsigned int m_size;
  } plugin_manager ;

  // type de la fonction d'initialisation du plugin
  typedef
  void (* init_func)(plugin_manager *, libhandle);

  //------------------------------------------------------------
  // fonctions destinées à l'application
  //------------------------------------------------------------

  // initialiser un gestionnaire
  plugin_manager *
  make_manager()
  { //contient une liste simplement chainée de plugin_manager
    plugin_manager* tmp = (plugin_manager*) malloc(sizeof(plugin_manager));
    tmp->m_first = NULL;
    //tmp->m_first->m_next = NULL ;
    tmp->m_size = 0;
    return tmp;
  }

  // libérer les ressources du gestionnaire
  void
  release_manager(plugin_manager * pm)
  {/*
    //on prend le premier chainon
    chainon * tmp = pm->m_first;

    //tant qu'on a pas trouvé le dernier
    while(tmp!=NULL){
      // on incremente/décrémente
      chainon * kill = tmp;
      tmp = tmp->m_next;
      pm->m_size--;

      //on supprime le contenu du chainon & le chainon
      free(kill->m_plugin);
      free(kill);
    }

    // on supprime le dernier chainon
    free(pm);
    pm->m_size--;*/

    //penser à dlclose
  }

  // découvrir les plugins ; résultat = nombre de plugins
  unsigned int
  discover_plugins(plugin_manager * pm, const char dirname[])
  {
    // TODO
    //on ouvre le rep
    DIR * desc=opendir(dirname);;
    struct dirent* fichlu = NULL;

    //tq c'est pas la fin du rep
    while((fichlu=readdir(desc))!=NULL){

      char chemin[64];
      //on regarde si ce qu'on trouve possède le suffixe recherche
      if(strstr(fichlu->d_name,plugin_suffix)!=NULL){

        strcpy(chemin, dirname);
	      strcat(chemin, "/");
        strcat(chemin,fichlu->d_name);


        //si c'est le cas, on fait la routine
        void * handle = dlopen(chemin, RTLD_LAZY);
        if (handle == NULL) {
      // erreur : bibliothèque non trouvée
        fprintf(stderr, "%s\n", dlerror());
        exit(EXIT_FAILURE);
      }
      dlerror();    /* Clear any existing error */

      // on stocke la fonction dans le plugin
      init_func root =  (init_func)dlsym(handle, initfunc_name);

      char * error = dlerror();
      if ((error) != NULL)  {
      // erreur : fonction non trouvée
        fprintf(stderr, "%s\n", error);
        exit(EXIT_FAILURE);
      }

      //appel de la fonction
      root(pm,handle);
      }
    }

    return pm->m_size;

  }

  // chercher et renvoyer le plugin de numéro donné ; NULL si absent
  // TODO : préciser numéro 1er plugin (0 ou 1)
  plugin_descriptor *
  get_plugin(plugin_manager * pm, unsigned int plugin_number)
  {
    // TODO
    return &(pm->m_first->m_plugin);
  }

  // afficher le menu des plugins disponibles
  void
  display_menu(plugin_manager * pm)
  {
    // TODO
      printf("\nListe des plugins : << %s >> ...\n", pm->m_first->m_plugin.m_name);
  }

  //------------------------------------------------------------
  // fonction destinée aux plugins
  //------------------------------------------------------------

  // enregistrer un plugin (si absent)
  void
  register_plugin(plugin_manager * pm,
		  const char filter_name[],
		  const char filter_description[],
		  filter_function the_filter,
		  libhandle the_lib
		  )
  {
    // TODO

    //creation descripteur
    plugin_descriptor toto;
    toto.m_name = filter_name;
    toto.m_description = filter_description;
    toto.m_filtre = the_filter;
    toto.m_lib = the_lib;

    //création chainon
    chainon* ch_toto = (chainon*)malloc(sizeof(chainon));
    //pas caca !
    ch_toto->m_plugin = toto;
    ch_toto->m_next = NULL;

    //ajout chaine à plugin_manager
    // on vérifie qu'il est pas déjà dedans
    chainon* tmp = pm->m_first; //core segmentation
    bool notExist = true;

    while(notExist && tmp->m_next!=NULL){
      if(tmp->m_plugin.m_name==ch_toto->m_plugin.m_name){
        notExist=false;
      } else {
        tmp=tmp->m_next;
      }
    }

    pm->m_first=ch_toto;
    pm->m_first->m_next=tmp;

    pm->m_size++;
  }
};
