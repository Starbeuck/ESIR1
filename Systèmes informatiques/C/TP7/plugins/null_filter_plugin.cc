#include <cv.h>
#include <stdio.h>
#include "pluginmanager.h"

extern "C"
{

  // filtre sans effet
  int
  null_filter(cv::Mat &)
  {
    printf("\nnull_filter : bravo, vous avez appliqué votre premier filtre...\n");
    return 0;
  }

  // enregistrer le plugin
  void
  plugin_init(plugin_manager * pm, libhandle handle)
  {
    register_plugin(pm,
		    "null_filter",
		    "Exemple de filtre basique",
		    null_filter,
		    handle
		    );
  }
};
