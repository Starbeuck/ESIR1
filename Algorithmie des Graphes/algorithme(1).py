#!/usr/bin/env python
import networkx as nx
import matplotlib.pyplot as plt


liste_processus=[]
liste_ressources=[]

# lance la cration d'un processus si il n'est pas deja existant
def creation_processus(nomprocessus, G): 
    # on verifie l'existence
    if nomprocessus in liste_processus :
        print ("ce processus existe deja")
    else:
        # on l ajoute au graph et a la liste des processus
        G.add_node (nomprocessus)
        print ("processus "+ nomprocessus+" ajoute")
        liste_processus.append(nomprocessus)

    return G.nodes()

# on initialise la liste des ressources disponibles
# une ressource ne peut etre rajoutee e ncours de route    
def initialisation_ressources(liste,G):
    liste_ressources=liste
    for nomress in liste_ressources:
        
        G.add_node(nomress)
    print(" les ressources " +str(liste_ressources) +" ont ete ajoutees")
    return liste_ressources


# detruire un processus    
def destruction_processus(nomprocessus, G):
    if nomprocessus in liste_processus: # si le processus existe 
        map_attributes=nx.get_edge_attributes(G,"weight")
        liste_a_supprimer=[9]
        liste_ressource_utilise=[]
        i=0
        j=0
        # on regarde si le processus est libre ou non 
        if (G.degree(nomprocessus) !=0):
            #si il n est pas libre on supprimes tous les arcs relies au processus 
            for noeud in map_attributes:
                # si le processus est en attente dune ressource
                if (noeud[0]==nomprocessus):
                    # on sauvegarde le poids de larc et la ressource visee
                    liste_a_supprimer[i]= noeud
                    i=i+1
                    ressource= noeud[1]
                    poids=map_attributes[noeud]
                    # on va reduire le poids des arcs superieurs a larc quon va supprimer
                    for arc in map_attributes:
                        if arc[1]== ressource and map_attributes[arc]>poids : 
                             map_attributes[arc]= map_attributes[arc]-1
              
       # si le processus utilisait la ressource on sauvegarde la ressource
                if noeud[1]== nomprocessus:
                  liste_ressource_utilise[j]=noeud[0]
                  j=j+1
                    
      
        map_attributes=nx.set_edge_attributes(G,"weight",map_attributes)
        # on supprime tous les arcs a supprimer
        for arc in liste_a_supprimer:
            G.remove_edge(arc[0],arc[1])
         # on libere les ressources utilisees par le processus   
        for ressource in liste_ressource_utilise:
            liberation_ressource(G,ressource,nomprocessus)
            
        liste_processus.remove(nomprocessus)#on supprime le 
        G.remove_node(nomprocessus)# on enleve le processus du graphe
        print ("processus supprime")
    else:# si absent de 
        print ("ce processus n existe pas")
    return G.nodes()
    

 
# demande de ressource(s) par un processus    
def demande_ressource(nomprocessus, liste_ressources_demande, G):
    # on verifie l existence du processus
    if nomprocessus not in liste_processus:
        creation_processus(nomprocessus,G)
    else:
        if G.out_degree (nomprocessus)==0 and G.in_degree(nomprocessus)>=0:
         for ressource in liste_ressources_demande:   # pour chaque ressource demandee, si elle existe
             if ressource in liste_ressources_demande:
                 # on gere l'ajout de la demande d'une ressource
                demande1_ressource (G, nomprocessus, ressource)
             else:
                print ("la ressource "+ressource+ " n'existe pas")
                
         #print ("les ressources ont ete demandees")  
         

# on part du principe que la ressource existe on va gerer la demande de processus soit par 
# l allocation de la ressource au processus 
# soit par l ajout dans la file d'attente de la ressoruce 
def demande1_ressource(G,nomprocessus, nomressource) :
    # on regarde si la ressource est libre
    if  G.degree(nomressource) == 0 : 
    # si la ressource est libre on cree un arc sortant de la ressource, sans poids 
        G.add_edge(nomressource, nomprocessus )
    else:
        #sinon on regarde combien de processus sont en attente
        degre_entrant= G.in_degree(nomressource)
        G.add_edge(nomprocessus,nomressource,weight= degre_entrant+1)
    return G.edges(nomprocessus)

# lorsqu un processus utilisant une ressource libere celle ci         
def liberation_ressource (G,nomressource, nomprocessus):
    
	#si la ressource est allouee
    if G.out_degree (nomressource)==1 :
		G.remove_edge(nomressource,nomprocessus)
		# si aucun processus n'est en attente de la ressource
		if G.in_degree(nomressource)> 0:
			#on trouve le processus de poids 1 entrant 
			#on parcours tous les noeuds des arcs de la ressource 
			# si un de poids =1 on change son noeud, pour les autre on diminue le poids
		
			map_attributes=nx.get_edge_attributes(G,"weight")
                for poids in map_attributes:
                    if poids[1] == nomressource :
                          if map_attributes[poids]==1:
                          # on recupere la cle couple
                           couple=poids
                         
                       
                      #   del map_attributes[couple]
                          else :
                              map_attributes[poids]= map_attributes[poids]-1
              
                map_attributes=nx.set_edge_attributes(G,"weight",map_attributes)
                G.remove_edge(couple[0],couple[1])
                G.add_edge(couple[1],couple[0])                

def file_dattente_ressource (G,nomressource):
    # le dictionnaire est range par valeurs croissante puisque chaque nouvelle valeur rentree est un processus en bout de file 
    map_attributes=nx.get_edge_attributes(G,"weight")
    for lien in map_attributes:
        if lien[1]== nomressource:
            blop= "processus "+str(lien[0])+": " +str(map_attributes[lien]) + " ieme sur la liste d'attente"
            print(blop)

def file_dattente_processus (G):
    # le dictionnaire est range par valeurs croissante puisque chaque nouvelle valeur rentree est un processus en bout de file 
    map_attributes=nx.get_edge_attributes(G,"weight")
    for ressource in liste_ressources:
        if G.out_degree(ressource)==1 and G.in_degree(ressource)>0:
            s="processus en attentes de la ressource" + ressource+ ": "
            t="utilisee par le processus : "
            for arc in map_attributes:  
                if arc[0]== ressource:
                    t=t+ str(arc[1])
                if arc[1]== ressource:
                    s=s+ str(arc[0])+" "
            
            
def processus_actif(G):
    s="liste des processus actifs : "
    n=""
    lister=[]
    for proc in liste_processus:
        if G.out_degree (proc)==0 and G.in_degree(proc)>=0: 
            n=n+ proc
            lister.append(proc)
    if n=="":
        print("il n'y a aucun processus actif")
    else:
        print(s+n) 
    return lister


# trouver les cfc
def compo_fortement_connexe(G):
    print ("liste des composantes fortement connexes : ")
    #initialisation des variables
    date=0
    sommet=G.nodes()
    debut=dict()
    cfc=dict()
    pile=[]
    nombrecfc=0
    # initialisation du dictionnaire ayant pour cle un noeud 
    #et pour valeur la date de debut (au depart 0)
    for neu in sommet: 
        debut[neu]=0
        cfc[neu]=0
  
   
    # recursivite
    for node in sommet:
        if debut[node]==0:
            tarjan(node, debut,date, pile,cfc,nombrecfc,G)
    #return cfc

def tarjan(node, debut,date,pile,cfc,nombrecfc,G): 
     date= date+1
     debut[node]=date
     mini=debut[node]
     pile.append(node)
     for succ in G.successors(node):
         if debut[succ]==0:
             mini= min(mini,tarjan(succ,debut,date,pile,cfc,nombrecfc,G))
         else:
             if cfc[succ]==0:
                 mini=min(mini,debut[succ])
     if mini==debut[node]:
        nombrecfc=nombrecfc+1
        n=[]
        k=pile.pop()
        cfc[k]=nombrecfc
       # s=""
        if k!=node:
            n.append(k)
            while True:
                k=pile.pop()
                n.append(k)
           # s=str(k)+" " +s
                cfc[k]=nombrecfc
                if k==node:
                    break
           # s=n+" "+s
            print(n)
            #print (cfc)
     return mini
             
    
    
       

def main ():
   G=nx.DiGraph()
   #nitialisation_ressources(["r1","r2","r3"],G)
   blop=open("test2.txt","r")
   exec(blop)
   #load_from_file('data.txt')
   #creation_processus("p2", G)
   #creation_processus("p3", G)
  # print G.nodes()
   #demande_ressource("p1", ["r1","r2"], G)      
   #demande_ressource("p2", ["r1","r2"], G) 
   #demande_ressource("p3", ["r1","r2"], G) 
   #liberation_ressource (G,"r1", "p1") 
  # processus_actif(G)
  # file_dattente_ressource (G,"r2")
 #  file_dattente_processus (G,"p3")
 
 
   
   
   
   pos = nx.spring_layout(G)   # positions for all nodes
   nx.draw_networkx_nodes(G,pos,
                       nodelist=G.nodes(),
                       node_color='#ff33cc',
                       node_size=900,
               alpha=0.8)


# edges
   nx.draw_networkx_edges(G,pos)
   nx.draw_networkx_edges(G,pos,
                       edgelist=G.edges(),
                       width=5,alpha=0.5,edge_color='k')


# labels
   labels = {}    
   for node in G.nodes():
        labels[node] = str(node)

   nx.draw_networkx_labels(G,pos,labels,font_size=16)
   edge_labels = nx.get_edge_attributes(G,"weight")
   nx.draw_networkx_edge_labels(G, pos, edge_labels, label_pos=0.5, font_size=10, font_color='k', font_family='sans-serif')
         
         
         
   plt.axis('off')
   plt.show() # display

if __name__ == "__main__":
    main()         
