# fichiers d'entête openCV pour compilation
OCV_INC  = `pkg-config --cflags opencv`

# bibliothèques openCV pour édition de liens
OCV_LIBS = `pkg-config --libs opencv`

# compilateur C++
CXX	 = g++

# options de compilation
CXXFLAGS += -Wall -ggdb $(OCV_INC) -I../include -fPIC

# options d'édition de liens
LDFLAGS	 += -shared

# tous les fichiers source C++ du répertoire courant
MY_SRC1	 = $(wildcard *.cc)
MY_SRC2	 = $(wildcard *.cpp)
MY_LIBS	 = $(patsubst %.cc,%.so,$(MY_SRC1)) $(patsubst %.cpp,%.so,$(MY_SRC2))

all : $(MY_LIBS)

# produire une bibliothèque dynamique à partir d'un objet
# un plugin a besoin des bibliothèques opencv
%.so : %.o
	$(CXX) $(LDFLAGS) -o $@ $< $(OCV_LIBS)

clean : 
	-@rm *~ *.so
