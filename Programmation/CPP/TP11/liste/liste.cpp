/*
 * liste.cpp
 *
 */

#include "liste.hh"
#include <cassert>
#include <iostream>
#include <string>
#include "copier.h"

int main()
{
	Liste<int> d;

	d.push_front( 15);
	d.push_front(3);
	d.push_front(6);

	//if(d.size()==3){
		//std::cout<<"true";}

	//std::cout<<d<<std::endl;
//	Liste <int>:: iterator it = d.begin();
	//Liste <int>:: iterator fin=d.end();
	//d.erase(it);
	/*if (d.size()==2){
		std::cout<<"true";
	}else{std::cout<<"false";}
*/
//	std::cout<<d<<std::endl;


	//find(it,fin,6);
	//d.push_front(6);
	d.push_front(12);
	d.push_front(4);

	std::cout<<d<<std::endl;
	//trieList(d);

	/*std::cout<<(*it)<<std::endl;
	++it;
	std::cout<<(*it)<<std::endl;
	d.insert(it,2);*/
	//std::cout<<d<<std::endl;
	Liste<int> li2= trieList(d);
	std::cout<<li2;

/*	Liste<int> blop;
	blop.push_front(10);
	blop.push_front(8);
	blop.push_front(5);
	std::cout<<blop<<std::endl;
*/

	/*Liste <int>:: iterator fini=rechercheVal(blop,6);
	std::cout<<(*fini)<<std::endl;
	if ((*fini)<6){
		std::cout<<"true";
	}else{std::cout<<"false";}
	Liste<int>:: iterator prout=blop.begin();
	std::cout<<(*prout)<<std::endl;

	++prout;
	std::cout<<(*prout)<<std::endl;
	*/
}
